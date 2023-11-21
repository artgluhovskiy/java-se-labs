package org.art.samples.core.reactor.base;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.Scannable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Log4j2
public class Samples {

    @Test
    void test1() {
        Flux.range(5, 100).hide()
            .map(Object::toString)
            .doOnSubscribe(l -> log.info("doOnSubscribe mapped"))
            .filter(s -> s.length() == 1)
            .doOnSubscribe(l -> log.info("doOnSubscribe filtered"))
            .doOnRequest(l -> log.info("doOnRequest filtered"))
            .doOnNext(s -> log.info("doOnNext filtered: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete filtered"))
            .doOnCancel(() -> log.info("doOnCancel filtered"))
            .take(3)
            .doOnSubscribe(l -> log.info("doOnSubscribe taken"))
            .doOnRequest(l -> log.info("doOnRequest taken"))
            .doOnNext(s -> log.info("doOnNext taken: {}", s))
            .doOnComplete(() -> log.info("doOnComplete taken"))
            .doOnCancel(() -> log.info("doOnCancel taken"))
            .blockLast();
    }

    @Test
    void test2() {
        Flux.range(0, 3).hide()
            .doOnSubscribe(l -> log.info("doOnSubscribe before"))
            .doOnRequest(l -> log.info("doOnRequest before"))
            .doOnNext(s -> log.info("doOnNext before: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete before"))
            .doOnCancel(() -> log.info("doOnCancel before"))
            .publishOn(Schedulers.parallel())
            .doOnSubscribe(l -> log.info("doOnSubscribe after"))
            .doOnRequest(l -> log.info("doOnRequest after"))
            .doOnNext(s -> log.info("doOnNext after: {}", s))
            .doOnComplete(() -> log.info("doOnComplete after"))
            .doOnCancel(() -> log.info("doOnCancel after"))
            .take(2)
            .doOnSubscribe(l -> log.info("doOnSubscribe taken"))
            .doOnRequest(l -> log.info("doOnRequest taken"))
            .doOnNext(s -> log.info("doOnNext taken: {}", s))
            .doOnComplete(() -> log.info("doOnComplete taken"))
            .doOnCancel(() -> log.info("doOnCancel taken"))
            .blockLast();
    }

    @Test
    void test3() {
        Flux.range(0, 3).hide()
            .doOnSubscribe(l -> log.info("doOnSubscribe before"))
            .doOnRequest(l -> log.info("doOnRequest before"))
            .doOnNext(s -> log.info("doOnNext before: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete before"))
            .doOnCancel(() -> log.info("doOnCancel before"))
            .subscribeOn(Schedulers.parallel())
            .doOnSubscribe(l -> log.info("doOnSubscribe after"))
            .doOnRequest(l -> log.info("doOnRequest after"))
            .doOnNext(s -> log.info("doOnNext after: {}", s))
            .doOnComplete(() -> log.info("doOnComplete after"))
            .doOnCancel(() -> log.info("doOnCancel after"))
            .take(2)
            .doOnSubscribe(l -> log.info("doOnSubscribe taken"))
            .doOnRequest(l -> log.info("doOnRequest taken"))
            .doOnNext(s -> log.info("doOnNext taken: {}", s))
            .doOnComplete(() -> log.info("doOnComplete taken"))
            .doOnCancel(() -> log.info("doOnCancel taken"))
            .blockLast();
    }

    @Test
    void test4() {
        Flux.range(0, 3).hide()
            .doOnSubscribe(l -> log.info("doOnSubscribe before"))
            .doOnRequest(l -> log.info("doOnRequest before"))
            .doOnNext(s -> log.info("doOnNext before: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete before"))
            .doOnCancel(() -> log.info("doOnCancel before"))
            .subscribeOn(Schedulers.parallel())
            .doOnSubscribe(l -> log.info("doOnSubscribe after"))
            .doOnRequest(l -> log.info("doOnRequest after"))
            .doOnNext(s -> log.info("doOnNext after: {}", s))
            .doOnComplete(() -> log.info("doOnComplete after"))
            .doOnCancel(() -> log.info("doOnCancel after"))
            .take(2)
            .publishOn(Schedulers.parallel())
            .doOnSubscribe(l -> log.info("doOnSubscribe taken"))
            .doOnRequest(l -> log.info("doOnRequest taken"))
            .doOnNext(s -> log.info("doOnNext taken: {}", s))
            .doOnComplete(() -> log.info("doOnComplete taken"))
            .doOnCancel(() -> log.info("doOnCancel taken"))
            .blockLast();
    }

    @Test
    void test5() {
        Flux.range(0, 3).hide()
            .doOnSubscribe(l -> log.info("doOnSubscribe before"))
            .doOnRequest(l -> log.info("doOnRequest before"))
            .doOnNext(s -> log.info("doOnNext before: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete before"))
            .doOnCancel(() -> log.info("doOnCancel before"))
            .delayElements(Duration.ofMillis(5))
            .doOnSubscribe(l -> log.info("doOnSubscribe after"))
            .doOnRequest(l -> log.info("doOnRequest after"))
            .doOnNext(s -> log.info("doOnNext after: {}", s))
            .doOnComplete(() -> log.info("doOnComplete after"))
            .doOnCancel(() -> log.info("doOnCancel after"))
            .take(2)
            .doOnSubscribe(l -> log.info("doOnSubscribe taken"))
            .doOnRequest(l -> log.info("doOnRequest taken"))
            .doOnNext(s -> log.info("doOnNext taken: {}", s))
            .doOnComplete(() -> log.info("doOnComplete taken"))
            .doOnCancel(() -> log.info("doOnCancel taken"))
            .blockLast();
    }

    @Test
    void test6() {
        Flux<Integer> parallelThreadFlux = Flux.range(10, 2)
            .publishOn(Schedulers.parallel())
            .doOnSubscribe(l -> log.info("doOnSubscribe parallel"))
            .doOnRequest(l -> log.info("doOnRequest parallel"))
            .doOnNext(s -> log.info("doOnNext parallel: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete parallel"))
            .doOnCancel(() -> log.info("doOnCancel parallel"));

        Flux<Integer> testWorkerThreadFlux = Flux.range(20, 2)
            .doOnSubscribe(l -> log.info("doOnSubscribe testWorker"))
            .doOnRequest(l -> log.info("doOnRequest testWorker"))
            .doOnNext(s -> log.info("doOnNext testWorker: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete testWorker"))
            .doOnCancel(() -> log.info("doOnCancel testWorker"));

        Flux.merge(parallelThreadFlux, testWorkerThreadFlux)
            .doOnSubscribe(l -> log.info("doOnSubscribe merge"))
            .doOnRequest(l -> log.info("doOnRequest merge"))
            .doOnNext(s -> log.info("doOnNext merge: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete merge"))
            .doOnCancel(() -> log.info("doOnCancel merge"))
            .blockLast();
    }

    @Test
    void test7() {
        Flux<String> namesFlux = Flux.just("Carl", "Dan", "Adam", "Brian", "Bruce", "Billy", "Alice");

        Function<GroupedFlux<Character, String>, Mono<String>> countFunction =
            gFlux -> gFlux
                .doOnSubscribe(l -> log.info("doOnSubscribe countFunction"))
                .doOnRequest(l -> log.info("doOnRequest countFunction"))
                .doOnNext(s -> log.info("doOnNext countFunction: {}", s))
                .doOnComplete(() -> log.info(" doOnComplete countFunction"))
                .doOnCancel(() -> log.info("doOnCancel countFunction"))
                .count()
                .map(count -> String.format("%s %s names", count, gFlux.key()));

        namesFlux
            .doOnSubscribe(l -> log.info("doOnSubscribe beforeGroupBy"))
            .doOnRequest(l -> log.info("doOnRequest beforeGroupBy"))
            .doOnNext(s -> log.info("doOnNext beforeGroupBy: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete beforeGroupBy"))
            .doOnCancel(() -> log.info("doOnCancel beforeGroupBy"))
            .groupBy(name -> name.charAt(0))
            .doOnSubscribe(l -> log.info("doOnSubscribe flatMap"))
            .doOnRequest(l -> log.info("doOnRequest flatMap"))
            .doOnNext(s -> log.info("doOnNext flatMap: {}", s.key()))
            .doOnComplete(() -> log.info(" doOnComplete flatMap"))
            .doOnCancel(() -> log.info("doOnCancel flatMap"))
            .flatMap(countFunction)
            .doOnSubscribe(l -> log.info("doOnSubscribe afterGroupBy"))
            .doOnRequest(l -> log.info("doOnRequest afterGroupBy"))
            .doOnNext(s -> log.info("doOnNext afterGroupBy: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete afterGroupBy"))
            .doOnCancel(() -> log.info("doOnCancel afterGroupBy"))
            .blockLast();
    }

    @Test
    @DisplayName("Broken groupBy/flatMap sample")
    void test8() {
        int prefetch = 1;
        int concurrency = 3;

        Flux<String> namesFlux = Flux.just("Carl", "Dan", "Adam", "Brian", "Bruce", "Billy", "Alice");

        Function<GroupedFlux<Character, String>, Mono<String>> countFunction =
            gFlux -> gFlux
                .doOnSubscribe(l -> log.info("doOnSubscribe countFunction"))
                .doOnRequest(l -> log.info("doOnRequest countFunction: {}", l))
                .doOnNext(s -> log.info("doOnNext countFunction: {}", s))
                .doOnComplete(() -> log.info(" doOnComplete countFunction"))
                .doOnCancel(() -> log.info("doOnCancel countFunction"))
                .count()
                .map(count -> String.format("%s %s names", count, gFlux.key()));

        namesFlux
            .doOnSubscribe(l -> log.info("doOnSubscribe beforeGroupBy"))
            .doOnRequest(l -> log.info("doOnRequest beforeGroupBy: {}", l))
            .doOnNext(s -> log.info("doOnNext beforeGroupBy: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete beforeGroupBy"))
            .doOnCancel(() -> log.info("doOnCancel beforeGroupBy"))
            .groupBy(name -> name.charAt(0), prefetch)
            .doOnSubscribe(l -> log.info("doOnSubscribe flatMap"))
            .doOnRequest(l -> log.info("doOnRequest flatMap"))
            .doOnNext(s -> log.info("doOnNext flatMap: {}", s.key()))
            .doOnComplete(() -> log.info(" doOnComplete flatMap"))
            .doOnCancel(() -> log.info("doOnCancel flatMap"))
            .flatMap(countFunction, concurrency)
            .doOnSubscribe(l -> log.info("doOnSubscribe afterGroupBy"))
            .doOnRequest(l -> log.info("doOnRequest afterGroupBy"))
            .doOnNext(s -> log.info("doOnNext afterGroupBy: {}", s))
            .doOnComplete(() -> log.info(" doOnComplete afterGroupBy"))
            .doOnCancel(() -> log.info("doOnCancel afterGroupBy"))
            .blockLast();
    }

    @Test
    void test9() {
        Long result = Mono.just(List.of(1, 2))
            .doOnSubscribe(l -> log.info("doOnSubscribe first"))
            .doOnRequest(l -> log.info("doOnRequest first: {}", l))
            .doOnNext(s -> log.info("doOnNext first: {}", s))
            .doOnCancel(() -> log.info("doOnCancel first"))
            .flatMap(list -> Flux.fromIterable(list)
                .parallel()
                .runOn(Schedulers.parallel())
//                .delayElements(Duration.ofMillis(5000))
                .doOnNext(value -> {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                    }
                })
                .map(val -> val * 10)
                .doOnSubscribe(l -> log.info("doOnSubscribe second"))
                .doOnRequest(l -> log.info("doOnRequest second: {}", l))
                .doOnNext(s -> log.info("doOnNext second: {}", s))
                .doOnCancel(() -> log.info("doOnCancel second"))
                .sequential()
                .count())
            .timeout(Duration.ofSeconds(10))
            .doOnSubscribe(l -> log.info("doOnSubscribe third"))
            .doOnRequest(l -> log.info("doOnRequest third: {}", l))
            .doOnNext(s -> log.info("doOnNext third: {}", s))
            .doOnCancel(() -> log.info("doOnCancel third"))
            .doOnError(th -> log.info("doOnError third", th))
            .block();

        log.info(result);
    }

    @Test
    @DisplayName("Turning cold publisher into a hot publisher - share()")
    @SneakyThrows
    void test10() {
        Flux<String> netFlux = Flux.fromStream(Samples::getVideo)
            .delayElements(Duration.ofSeconds(2))
            .share();

        // First Subscriber
        netFlux.subscribe(part -> log.info("Subscriber 1: " + part));

        // wait 5 seconds before next Subscriber joins
        Thread.sleep(5000);

        // Seconds Subscriber
        netFlux.subscribe(part -> log.info("Subscriber 2: " + part));

        Thread.sleep(60000);
    }

    @Test
    @DisplayName("Turning cold publisher into a hot publisher - refCount()")
    @SneakyThrows
    void test11() {
        Flux<String> netFlux = Flux.fromStream(Samples::getVideo)
            .delayElements(Duration.ofSeconds(2))
            .publish()
            .refCount(2);

        // First Subscriber
        netFlux.subscribe(part -> log.info("Subscriber 1: " + part));

        // wait 5 seconds before next Subscriber joins
        Thread.sleep(5000);

        // Seconds Subscriber
        netFlux.subscribe(part -> log.info("Subscriber 2: " + part));

        Thread.sleep(60000);
    }

    @Test
    @DisplayName("Turning cold publisher into a hot publisher - cache()")
    @SneakyThrows
    void test12() {
        Flux<String> netFlux = Flux.fromStream(Samples::getVideo)
            .delayElements(Duration.ofSeconds(2))
            .cache();

        // First Subscriber
        netFlux.subscribe(part -> log.info("Subscriber 1: " + part));

        // wait 5 seconds before next Subscriber joins
        Thread.sleep(5000);

        // Seconds Subscriber
        netFlux.subscribe(part -> log.info("Subscriber 2: " + part));

        Thread.sleep(60000);
    }

    @Test
    @DisplayName("Pass context with value to the upstream")
    void test13() {
        Long result = Mono.just("start")
            .doOnSubscribe(l -> log.info("doOnSubscribe first"))
            .doOnRequest(l -> log.info("doOnRequest first: {}", l))
            .doOnNext(s -> log.info("doOnNext first: {}", s))
            .doOnCancel(() -> log.info("doOnCancel first"))
            .flatMap(value ->
                Mono.deferContextual(ctx ->
                    Mono.just(Integer.class.cast(ctx.get("range_value")))
                        .flatMap(rangeValue -> Flux.range(1, rangeValue)
                            .parallel()
                            .runOn(Schedulers.parallel())
                            .map(val -> val * 10)
                            .doOnSubscribe(l -> log.info("doOnSubscribe second"))
                            .doOnRequest(l -> log.info("doOnRequest second: {}", l))
                            .doOnNext(s -> log.info("doOnNext second: {}", s))
                            .doOnCancel(() -> log.info("doOnCancel second"))
                            .sequential()
                            .count())))
            .doOnSubscribe(l -> log.info("doOnSubscribe third"))
            .doOnRequest(l -> log.info("doOnRequest third: {}", l))
            .doOnNext(s -> log.info("doOnNext third: {}", s))
            .doOnCancel(() -> log.info("doOnCancel third"))
            .doOnError(th -> log.info("doOnError third", th))
            .contextWrite(ctx -> ctx.put("range_value", 2))
            .block();

        log.info(result);
    }

    @Test
    @DisplayName("concurrent flatMap sample")
    void test14() {
        Flux.just(1, 3)
            .doOnSubscribe(l -> log.info("doOnSubscribe first"))
            .doOnRequest(l -> log.info("doOnRequest first: {}", l))
            .doOnNext(s -> log.info("doOnNext first: {}", s))
            .doOnCancel(() -> log.info("doOnCancel first"))
            .flatMap(value ->
                Mono.just(value)
                    .publishOn(Schedulers.parallel())
                    .map(val -> val * 10)
                    .doOnSubscribe(l -> log.info("doOnSubscribe second"))
                    .doOnRequest(l -> log.info("doOnRequest second: {}", l))
                    .doOnNext(s -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        log.info("doOnNext second: {}", s);
                    })
                    .doOnCancel(() -> log.info("doOnCancel second")))
            .doOnSubscribe(l -> log.info("doOnSubscribe third"))
            .doOnRequest(l -> log.info("doOnRequest third: {}", l))
            .doOnNext(s -> log.info("doOnNext third: {}", s))
            .doOnCancel(() -> log.info("doOnCancel third"))
            .doOnError(th -> log.info("doOnError third", th))
            .blockLast();
    }

    @Test
    @DisplayName("Defining current buffer size")
    @SneakyThrows
    void test15() {
        AtomicReference<Scannable> scannableRef = new AtomicReference<>();

        Sinks.Many<Object> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(16);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                int counter = 0;
                while (counter < 100) {
                    try {
                        log.info("Emmiting next: {}", counter);
                        sink.tryEmitNext(counter);
                        counter++;
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            });


//        Flux<String> netFlux = Flux.fromStream(Samples::getVideo)
//        Flux<Long> netFlux = Flux.interval(Duration.ofMillis(10))
//            .onBackpressureBuffer(64, val -> {
//                log.info("Buffer overflow!");
//            })
        sink.asFlux()
//            .delayElements(Duration.ofSeconds(1))
            .doOnNext(val -> log.info("Emitting value: {}", val))
            .doOnSubscribe(sub -> {
                Scannable scannable = Scannable.from(sub);
                scannableRef.set(scannable);
            })
//            .share();
//            .publish()
//            .refCount(1);

//        netFlux
//            .publishOn(Schedulers.parallel())
//            .doOnNext(val -> {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            })
            .delayElements(Duration.ofSeconds(2))
            .doOnRequest(requested -> log.info("Requested: {}", requested))
            .subscribe(sub -> log.info("Subscriber 1: " + sub));

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
                log.info("Buffer size: {}", scannableRef.get().scan(Scannable.Attr.BUFFERED));
            });

//        Thread.sleep(10000);

        // Seconds Subscriber
//        netFlux
//            .publishOn(Schedulers.parallel())
//            .doOnNext(val -> {
//                try {
//                    Thread.sleep(2500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            })
//            .subscribe(sub -> log.info("Subscriber 2: " + sub));

        Thread.sleep(15000);
    }

    @Test
    void test16() {
        Flux.interval(Duration.ofMillis(1))
            .onBackpressureBuffer(10)
            .doOnNext(val -> log.info("On next: {}", val))
            .log()
            .concatMap(x -> Mono.delay(Duration.ofMillis(100)))
            .blockLast();
    }

    private static Stream<String> getVideo() {
        log.info("Request for the video streaming received.");
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }

    @Test
    void testWithParallelProcessingAndDelay() {
        List<Payload> payloads = List.of(
            Payload.builder().value("val_1").delay(2000L).build(),  // The first delay affects the bufferTimeout time (!)
            Payload.builder().value("val_2").delay(4000L).build(),
            Payload.builder().value("val_3").delay(15000L).build(),
            Payload.builder().value("val_4").delay(8000L).build(),
            Payload.builder().value("val_5").delay(10000L).build()
        );

        Long commonTimeout = 12000L;

        Scheduler scheduler = Schedulers.newParallel("parallel", 100);

        Flux.fromIterable(payloads)
            .doOnSubscribe(sub -> log.info("Subscribed to the source"))
            .parallel(payloads.size())
            .runOn(scheduler)
            .flatMap(payload ->
                Mono.defer(() ->
                        Mono.just(payload.getValue() + "_delayed")
                            .delayElement(Duration.ofMillis(payload.getDelay())))
                    .doOnNext(val -> log.info("Mapped payload after delay: {}", val)))
            .sequential()
            .bufferTimeout(payloads.size(), Duration.ofMillis(commonTimeout))
            .next()
            .doOnNext(result -> log.info("Result after buffer timeout: {}", result))
            .timeout(Duration.ofMillis(commonTimeout + 50L))
            .doOnError(error -> log.error("Error retrieved after second common timeout: {}", error.getMessage()))
            .doOnNext(resulted -> log.info("Result: {}", resulted))
            .block();
    }

    @Test
    void testWithParallelProcessingAndDelayExtended() {
        List<Payload> payloads = List.of(
            Payload.builder().value("val_1").delay(2000L).build(),
            Payload.builder().value("val_2").delay(4000L).build(),
            Payload.builder().value("val_3").delay(15000L).build(),
            Payload.builder().value("val_4").delay(8000L).build(),
            Payload.builder().value("val_5").delay(10000L).build()
        );

        Long commonTimeout = 12000L;

        Scheduler scheduler = Schedulers.newParallel("parallel", 100);

        Flux.fromIterable(payloads)
            .doOnSubscribe(sub -> log.info("Subscribed to the source"))
            .parallel(payloads.size())
            .runOn(scheduler)
            .flatMap(payload ->
                Mono.defer(() ->
                        Mono.just(payload.getValue() + "_delayed")
                            .doOnSubscribe(val -> log.info("Subscribed to value before delay: {}", payload.getValue()))
                            .delayElement(Duration.ofMillis(payload.getDelay())))
                    .doOnNext(val -> log.info("Mapped payload after delay: {}", val)))
            .sequential()
            .doOnSubscribe(val -> log.info("Subscribed before bufferTimeout"))
            .bufferTimeout(payloads.size(), Duration.ofMillis(commonTimeout))
            .doOnSubscribe(val -> log.info("Subscribed after bufferTimeout"))
            .next()
            .doOnNext(result -> log.info("Result after buffer timeout: {}", result))
            .timeout(Duration.ofMillis(commonTimeout + 50L))
            .doOnError(error -> log.error("Error retrieved after second common timeout: {}", error.getMessage()))
            .doOnNext(resulted -> log.info("Result: {}", resulted))
            .block();
    }

    @Getter
    @Builder
    static class Payload {

        String value;

        Long delay;
    }

}
