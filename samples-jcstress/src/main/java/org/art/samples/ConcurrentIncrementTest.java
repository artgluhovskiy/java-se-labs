package org.art.samples;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * How to run:
 * mvn clean install
 * java -jar ./target/jcstress.jar -v -t org.art.samples.ConcurrentIncrementTest
 */
@JCStressTest
@Description("Classic test that demonstrates memory reordering")
@Outcome(id = {"1, 2", "2, 1", "2, 2"}, expect = Expect.ACCEPTABLE, desc = "Both incremented")
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Increment lost")
public class ConcurrentIncrementTest {

    @Actor
    public final void actor1(DataHolder dataHolder, II_Result r) {
        dataHolder.inc();
        r.r1 = dataHolder.counter;
    }

    @Actor
    public final void actor2(DataHolder dataHolder, II_Result r) {
        dataHolder.inc();
        r.r2 = dataHolder.counter;
    }

    @State
    public static class DataHolder {

        private int counter;

        public void inc() {
            counter++;
        }
    }
}
