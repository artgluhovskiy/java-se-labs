package main.java.org.art.samples.core.reactor.array_publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;

public class ArrayPublisher<T> implements Publisher<T> {

    private final T[] array;

    public ArrayPublisher(T[] array) {
        this.array = array;
    }

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onSubscribe(new Subscription() {
            int index;
            volatile boolean canceled;
            final AtomicLong requested = new AtomicLong();

            @Override
            public void request(long n) {
                long initialRequested;

                for (; ; ) {
                    initialRequested = requested.get();

                    if (initialRequested == Long.MAX_VALUE) {
                        return;
                    }

                    n = initialRequested + n;

                    if (n <= 0) {
                        n = Long.MAX_VALUE;
                    }

                    if (requested.compareAndSet(initialRequested, n)) {
                        break;
                    }
                }

                if (initialRequested > 0) {
                    return;
                }

                int sent = 0;

                while (true) {
                    for (; sent < n && index < array.length; sent++, index++) {
                        if (canceled) {
                            return;
                        }

                        T element = array[index];

                        if (element == null) {
                            subscriber.onError(new NullPointerException());
                            return;
                        }

                        subscriber.onNext(element);
                    }
                    if (index == array.length) {
                        subscriber.onComplete();
                        return;
                    }

                    n = requested.get();

                    if (n == sent) {
                        n = requested.addAndGet(-sent);
                        if (n == 0) {
                            return;
                        }
                        sent = 0;
                    }
                }
            }

            @Override
            public void cancel() {
                canceled = true;
            }
        });
    }
}
