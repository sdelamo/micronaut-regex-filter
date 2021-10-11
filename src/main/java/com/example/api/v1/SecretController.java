package com.example.api.v1;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.util.Collections;
import java.util.List;

@Controller("/api/v1")
class SecretController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/secret")
    List<String> index() {
        return Collections.singletonList("I admire Moriarty");
    }
}
