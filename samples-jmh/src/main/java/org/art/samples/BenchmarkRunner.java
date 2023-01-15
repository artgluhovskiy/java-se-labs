package org.art.samples;

import lombok.SneakyThrows;
import org.art.samples.methodhandle.VarHandleBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    @SneakyThrows
    public static void main(String[] args) {
        var opt = new OptionsBuilder()
            .include(VarHandleBenchmark.class.getSimpleName())
            .build();

        new Runner(opt).run();
    }
}