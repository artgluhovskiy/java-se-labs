package org.art.samples.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
@Fork(value = 1)
@State(Scope.Thread)
public class ReflectiveFieldsAccessBenchmark {

    @State(Scope.Thread)
    public static class GlobalState {

        Map<String, FieldMetadata> metadata = new HashMap<>();

        Class<?> userClass = User.class;

        @SneakyThrows
        @Setup(Level.Trial)
        public void setupGlobal() {
            Field idField = User.class.getDeclaredField("id");
            this.metadata.put(idField.getName(), new FieldMetadata(idField));

            Field firstNameField = User.class.getDeclaredField("firstName");
            this.metadata.put(firstNameField.getName(), new FieldMetadata(firstNameField));

            Field lastNameField = User.class.getDeclaredField("lastName");
            this.metadata.put(lastNameField.getName(), new FieldMetadata(lastNameField));
        }
    }

    @Benchmark
    public void getClassFieldsViaReflection(GlobalState globalState, Blackhole blackhole) {
        Field[] fields = globalState.userClass.getDeclaredFields();
        blackhole.consume(fields);
    }

    @Benchmark
    public void getClassFieldFromMap(GlobalState globalState, Blackhole blackhole) {
        Field idField = globalState.metadata.values().iterator().next().getField();
        blackhole.consume(idField);
    }

    @Getter
    static class User {

        private Long id;

        private String firstName;

        private String lastName;
    }

    @Getter
    @RequiredArgsConstructor
    static class FieldMetadata {

        private final Field field;
    }
}
