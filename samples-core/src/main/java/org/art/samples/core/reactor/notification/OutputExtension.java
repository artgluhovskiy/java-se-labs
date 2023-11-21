package org.art.samples.core.reactor.notification;

import org.reactivestreams.Subscriber;

public interface OutputExtension {

    Subscriber<Notification> getSubscriber();
}
