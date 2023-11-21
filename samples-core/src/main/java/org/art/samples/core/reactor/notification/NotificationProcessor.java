package org.art.samples.core.reactor.notification;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Log4j2
@RequiredArgsConstructor
public class NotificationProcessor {

    private final List<InputExtension> inputs;

    private final List<OutputExtension> outputs;

    public void init() {
        Flux<Notification> mergedInputs = Flux.merge(
            inputs.stream()
                .map(InputExtension::getPublisher)
                .collect(Collectors.toList())
        );

        Scheduler notificationScheduler = Schedulers.newBoundedElastic(2, 1, "notificationScheduler");

        Flux<Notification> decoratedInputs = mergedInputs
            .publishOn(notificationScheduler)
            .doOnSubscribe(subscription -> log.info("doOnSubscribe"))
            .doOnNext(notification -> log.info("doOnNext: {}", notification))
            .doOnComplete(() -> log.info("doOnComplete"));

        outputs.forEach(output -> {
            decoratedInputs.subscribe(output.getSubscriber());
        });
    }
}
