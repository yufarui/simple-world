package yu.reactive.tech;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @date: 2020/7/9 15:02
 * @author: farui.yu
 */
public class TestForReactive {

    @Test
    public void countEach100ms() {
        Part01Flux part01Flux = new Part01Flux();
        Flux<Long> flux = part01Flux.counter();
        StepVerifier.create(flux)
                .expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
                .expectComplete()
                .verify();
    }

    @Test
    public void merge() {
        Flux.range(5, 3)
                .subscribe(System.out::println);


        Part05Merge part05Merge = new Part05Merge();
        Flux<User> f1 = Flux.just(new User("1"), new User("2"));
        Flux<User> f2 = Flux.just(new User("3"), new User("4"));
        part05Merge.mergeFluxWithInterleave(f2, f1)
                .subscribe(System.out::println);
    }
}
