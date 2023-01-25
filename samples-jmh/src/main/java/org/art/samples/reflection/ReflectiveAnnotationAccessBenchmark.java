package org.art.samples.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.SneakyThrows;
import org.art.samples.reflection.annotation.UserScope;
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
public class ReflectiveAnnotationAccessBenchmark {

    @State(Scope.Thread)
    public static class GlobalState {

        Map<String, UserMetadata> userMetadata = new HashMap<>();

        Field userField;

        @SneakyThrows
        @Setup(Level.Trial)
        public void setupGlobal() {
            UserMetadata metadata = new UserMetadata("value", true, new Class[]{Integer.class, Double.class});
            userMetadata.put(User.class.getName(), metadata);

            userField = User.class.getDeclaredField("firstName");
        }
    }

    @Benchmark
    public void getAnnotationAttributeViaReflection(GlobalState globalState, Blackhole blackhole) {
        UserScope annotation = globalState.userField.getAnnotation(UserScope.class);
        blackhole.consume(annotation.value());
        blackhole.consume(annotation.required());
        blackhole.consume(annotation.numberClasses());
    }

    @Benchmark
    public void getAnnotationAttributeFromMap(GlobalState globalState, Blackhole blackhole) {
        UserMetadata userMetadata = globalState.userMetadata.get(User.class.getName());
        blackhole.consume(userMetadata.getValue());
        blackhole.consume(userMetadata.isRequired());
        blackhole.consume(userMetadata.getNumberClasses());
    }

    static class User {

        @Getter
        @UserScope(value = "user", required = true, numberClasses = {Integer.class, Double.class})
        private String firstName;
    }

    static class UserMetadata {

        public UserMetadata(String value, boolean required, Class<? extends Number>[] numberClasses) {
            this.value = value;
            this.required = required;
            this.numberClasses = numberClasses;
        }

        private final String value;

        private final boolean required;

        private final Class<? extends Number>[] numberClasses;

        public String getValue() {
            return value;
        }

        public boolean isRequired() {
            return required;
        }

        public Class<? extends Number>[] getNumberClasses() {
            return numberClasses;
        }
    }
}
