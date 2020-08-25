package yu.reactive.tech;


//generic imports to help with simpler IDEs (ie tech.io)

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

    // Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(u ->
                new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

//========================================================================================

    // Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(u -> new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

//========================================================================================

    // Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(u -> asyncCapitalizeUser(u));
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}

