package org.art.samples.core.reactor.notification;

import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Log4j2
@RequiredArgsConstructor
public class DefaultSubscriber implements Subscriber<Notification> {

    private final String name;

    private final int request;

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription s) {
        log.info("{}, onSubscribe", name);
        subscription = s;
        subscription.request(request);
    }

    @Override
    @SneakyThrows
    public void onNext(Notification notification) {
        TimeUnit.SECONDS.sleep(1);
        log.info("{}, onNext: {}", name, notification);
    }

    @Override
    public void onError(Throwable t) {
        log.info("{}, onError: {}", name, t);
    }

    @Override
    public void onComplete() {
        log.info("{}, onComplete", name);
    }
}
