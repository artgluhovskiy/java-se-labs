package org.art.samples.core.reactor.notification;

import org.reactivestreams.Publisher;

public interface Writer {

    void write(Publisher<Notification> publisher);
}
