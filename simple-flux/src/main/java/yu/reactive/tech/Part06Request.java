package yu.reactive.tech;

//generic imports to help with simpler IDEs (ie tech.io)


import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

//========================================================================================

    // Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.withVirtualTime(() -> flux)
                .expectSubscription()
                .thenRequest(Long.MAX_VALUE)
                .expectNextCount(4)
                .expectComplete();
    }

//========================================================================================

    // Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
        return null;
    }

//========================================================================================

    // Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return null;
    }

}
