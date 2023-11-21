package org.art.samples.core.reactor.notification;

import org.reactivestreams.Publisher;

public interface Reader {

    Publisher<Notification> read();
}
