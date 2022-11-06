package org.art.java_core.reactor.array_publisher;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayPublisherTest {

    @Test
    void signalsShouldBeEmittedInTheRightOrder() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<Long> collected = new ArrayList<>();
        List<Integer> order = new ArrayList<>();
        long toRequest = 5L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                order.add(0);
                s.request(toRequest);
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
                if (!order.contains(1)) {
                    order.add(1);
                }
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                order.add(2);
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);

        assertEquals(order, Arrays.asList(0, 1, 2));
        assertEquals(collected, Arrays.asList(array));
    }

    @Test
    void mustSupportBackpressureControl() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        List<Long> collected = new ArrayList<>();
        long toRequest = 5L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);
        Subscription[] subscription = new Subscription[1];

        publisher.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                subscription[0] = s;
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });

        assertEquals(collected, Collections.emptyList());

        subscription[0].request(1);
        assertEquals(collected, Arrays.asList(0L));

        subscription[0].request(1);
        assertEquals(collected, Arrays.asList(0L, 1L));

        subscription[0].request(2);
        assertEquals(collected, Arrays.asList(0L, 1L, 2L, 3L));

        subscription[0].request(20);

        latch.await(1, TimeUnit.SECONDS);

        assertEquals(collected, Arrays.asList(array));
    }

    @Test
    void mustSendNpeNormally() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Long[] array = new Long[]{null};
        AtomicReference<Throwable> error = new AtomicReference<>();
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(4);
            }

            @Override
            public void onNext(Long aLong) {
            }

            @Override
            public void onError(Throwable t) {
                error.set(t);
                latch.countDown();
            }

            @Override
            public void onComplete() {
            }
        });

        latch.await(1, TimeUnit.SECONDS);

        assertTrue(error.get() instanceof NullPointerException);
    }

    @Test
    void shouldNotDieInStackOverflow() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        List<Long> collected = new ArrayList<>();
        long toRequest = 5L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<>() {
            Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(1);
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
                s.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });

        latch.await(5, TimeUnit.SECONDS);

        assertEquals(collected, Arrays.asList(array));
    }

    @Test
    void shouldBePossibleToCancelSubscription() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        List<Long> collected = new ArrayList<>();
        long toRequest = 1000L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.cancel();
                s.request(toRequest);
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);

        assertEquals(collected, Collections.emptyList());
    }

    @Test
    void multithreadingTest() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        List<Long> collected = new ArrayList<>();
        final long n = 5000L;
        Long[] array = generate(n);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<>() {
            private Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                for (int i = 0; i < n; i++) {
                    ForkJoinPool.commonPool().execute(() -> s.request(1));
                }
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });

        latch.await(5, TimeUnit.SECONDS);

        assertEquals(Arrays.asList(array).size(), collected.size());
    }

    static Long[] generate(long num) {
        return LongStream.range(0, num >= Integer.MAX_VALUE ? 1000000 : num)
            .boxed()
            .toArray(Long[]::new);
    }
}
