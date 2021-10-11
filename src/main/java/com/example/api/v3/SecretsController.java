package com.example.api.v3;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Collections;
import java.util.Map;

@Controller("/api/v3")
public class SecretsController {
    @Get("/secret")
    Map<String, Object> index() {
        return Collections.singletonMap("messages", Collections.singletonList("I admire Moriarty"));
    }
}
