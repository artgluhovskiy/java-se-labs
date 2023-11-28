package org.art.samples;

import lombok.SneakyThrows;
import org.art.samples.collections.ListBenchmark;
import org.art.samples.reflection.ReflectiveAnnotationAccessBenchmark;
import org.art.samples.reflection.ReflectiveFieldsAccessBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    @SneakyThrows
    public static void main(String[] args) {
        var opt = new OptionsBuilder()
//            .include(VarHandleBenchmark.class.getSimpleName())
//            .include(ReflectiveAnnotationAccessBenchmark.class.getSimpleName())
//            .include(ReflectiveFieldsAccessBenchmark.class.getSimpleName())
            .include(ListBenchmark.class.getSimpleName())
            .build();

        new Runner(opt).run();
    }
}
