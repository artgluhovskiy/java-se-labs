package org.art.samples.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
@Fork(value = 1)
@State(Scope.Thread)
public class VarHandleBenchmark {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

    @State(Scope.Thread)
    public static class GlobalState {

        VarHandle firstNameVH;

        MethodHandle firstNameMH;

        Field firstNameField;

        User instance;

        String valueToSet;

        @SneakyThrows
        @Setup(Level.Trial)
        public void setupGlobal() {
            firstNameVH = MethodHandles
                .privateLookupIn(User.class, MethodHandles.lookup())
                .findVarHandle(User.class, "firstName", String.class);

            firstNameMH = MethodHandles.lookup().findSetter(User.class, "firstName", String.class);

            firstNameField = User.class.getDeclaredField("firstName");
            firstNameField.setAccessible(true);

            instance = new User();

            valueToSet = "Some Random Name " + TLR.nextInt();
        }
    }

    @Benchmark
    public void setFieldValueViaVarHandle(GlobalState globalState) {
        globalState.firstNameVH.set(globalState.instance, globalState.valueToSet);
    }

    @Benchmark
    @SneakyThrows
    public void setFieldValueViaMethodHandle(GlobalState globalState) {
        globalState.firstNameMH.invoke(globalState.instance, globalState.valueToSet);
    }

    @Benchmark
    @SneakyThrows
    public void setFieldValueViaReflection(GlobalState globalState) {
        globalState.firstNameField.set(globalState.instance, globalState.valueToSet);
    }

    static class User {

        @Getter
        private String firstName;
    }
}
