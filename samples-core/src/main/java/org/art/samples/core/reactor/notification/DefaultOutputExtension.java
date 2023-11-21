package org.art.samples.core.reactor.notification;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;

@RequiredArgsConstructor
public class DefaultOutputExtension implements OutputExtension {

    private final Subscriber<Notification> subscriber;

    @Override
    public Subscriber<Notification> getSubscriber() {
        return subscriber;
    }
}
