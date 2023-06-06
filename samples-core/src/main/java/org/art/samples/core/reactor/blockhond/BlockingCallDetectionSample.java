package org.art.samples.core.reactor.blockhond;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

public class BlockingCallDetectionSample {

    @Test
    void shouldThrowExceptionOnBlockingCallExistence() {

        BlockHound.install();

        Mono.delay(Duration.ofSeconds(1))
            .doOnNext(it -> {
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            })
            .block();
    }
}
