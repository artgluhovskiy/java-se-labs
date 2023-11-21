package org.art.samples.core.reactor.notification;

import org.reactivestreams.Publisher;

public interface InputExtension {

    Publisher<Notification> getPublisher();
}
