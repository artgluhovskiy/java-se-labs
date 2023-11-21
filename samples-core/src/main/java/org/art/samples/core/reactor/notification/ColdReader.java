package org.art.samples.core.reactor.notification;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

@Log4j2
@RequiredArgsConstructor
public class ColdReader implements Reader {

    private final List<Notification> notifications;

    @Override
    public Publisher<Notification> read() {
        return Flux.fromIterable(notifications)
            .doOnSubscribe(subscription -> log.info("doOnSubscribe"))
            .doOnNext(notification -> log.info("doOnNext: {}", notification))
            .doOnComplete(() -> log.info("doOnComplete"))
            .doOnCancel(() -> log.info("doOnCancel"));
    }
}
