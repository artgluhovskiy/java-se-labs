package org.art.samples.core.reactor.notification;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Log4j2
public class NotificationTest {

    @Test
    void test1() {
        var notifications = List.of(
            Notification.builder().id(1L).build(),
            Notification.builder().id(2L).build()
        );
        var reader = new ColdReader(notifications);
        var input = new DefaultInputExtension(reader);

        var subscriber = new DefaultSubscriber("sub_1", 2);
        var output = new DefaultOutputExtension(subscriber);

        var notificationProcessor = new NotificationProcessor(List.of(input), List.of(output));

        notificationProcessor.init();
    }

    @Test
    void test2() {
        var notifications = List.of(
            Notification.builder().id(1L).build(),
            Notification.builder().id(2L).build()
        );

        Flux.fromIterable(notifications)
            .doOnSubscribe(sub -> {
                log.info("doOnSubscribe");
            })
            .subscribe(new DefaultSubscriber("sub", 1));
    }

    @Test
    void test3() {
        Sinks.Many<Notification> processor = Sinks.many().multicast().onBackpressureBuffer();

        processor.tryEmitNext(Notification.builder().id(1L).build());
        processor.tryEmitNext(Notification.builder().id(2L).build());
        processor.tryEmitNext(Notification.builder().id(3L).build());

        processor.asFlux()
            .subscribe(new DefaultSubscriber("sub_1", 3));

        processor.tryEmitNext(Notification.builder().id(4L).build());

        processor.asFlux()
            .subscribe(new DefaultSubscriber("sub_2", 3));

    }

}
