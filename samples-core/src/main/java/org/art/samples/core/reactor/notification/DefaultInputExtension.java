package org.art.samples.core.reactor.notification;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@RequiredArgsConstructor
public class DefaultInputExtension implements InputExtension {

    private final Reader reader;

    @Override
    public Publisher<Notification> getPublisher() {
        return reader.read();
    }
}
