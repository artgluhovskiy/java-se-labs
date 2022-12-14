package main.java.org.art.samples.core.reactor.array_publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

import java.util.stream.LongStream;

public class ArrayPublisherTckTest extends PublisherVerification<Long> {

    public ArrayPublisherTckTest() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<Long> createPublisher(long elements) {
        return new ArrayPublisher<>(generate(elements));
    }

    @Override
    public Publisher<Long> createFailedPublisher() {
        return null;
    }

    static Long[] generate(long num) {
        return LongStream.range(0, num >= Integer.MAX_VALUE ? 1000000 : num)
            .boxed()
            .toArray(Long[]::new);
    }
}
