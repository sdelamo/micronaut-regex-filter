package com.example.api.v2;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Collections;
import java.util.Map;

@Controller("/api/v2")
public class SecretController {
    @Get("/secret")
    Map<String, Object> index() {
        return Collections.singletonMap("messages", Collections.singletonList("I admire Moriarty"));
    }
}
