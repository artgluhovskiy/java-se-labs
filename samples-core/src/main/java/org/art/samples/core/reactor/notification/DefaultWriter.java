package org.art.samples.core.reactor.notification;

import java.util.List;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

@Log4j2
public class DefaultWriter implements Writer {

    @Getter
    private List<Notification> notifications;

    @Override
    public void write(Publisher<Notification> publisher) {
        Flux.from(publisher)
            .subscribe(notification -> {
                log.info("Received notification: {}", notification);
                notifications.add(notification);
            });
    }
}
