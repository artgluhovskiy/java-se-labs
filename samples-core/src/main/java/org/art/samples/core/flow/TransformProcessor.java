package main.java.org.art.samples.core.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class TransformProcessor<T, R> extends SubmissionPublisher<R> implements Flow.Processor<T, R> {

    private Function<T, R> function;
    private Flow.Subscription subscription;

    public TransformProcessor(Function<T, R> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Subscriber subscribed!");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("Got: " + item);
        System.out.println("Subscriber 'onNext()' method thread: " + Thread.currentThread().getName());
        submit(function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Error!");
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
        close();
    }
}
