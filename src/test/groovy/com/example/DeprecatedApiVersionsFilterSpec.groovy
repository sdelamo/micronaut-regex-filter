package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import spock.lang.Unroll

@MicronautTest
class DeprecatedApiVersionsFilterSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient httpClient

    @Unroll("request to v#version includes an HTTP Header Deprecation true")
    void "request to deprecated endpoints include a Deprecation header"(String version) {
        given:
        URI uri = UriBuilder.of('/api').path("v" + version).path("secret").build()
        HttpRequest<?> request = HttpRequest.GET(uri)

        when:
        HttpResponse<?> response = httpClient.toBlocking().exchange(request)

        then:
        noExceptionThrown()
        response.headers.contains("Deprecation")
        "true" == response.header("Deprecation")

        where:
        version << ['1', '2']
    }

    void "request to v3 does not include an HTTP Header Deprecation true"(String version) {
        given:
        URI uri = UriBuilder.of('/api').path("v" + version).path("secret").build()
        HttpRequest<?> request = HttpRequest.GET(uri)

        when:
        HttpResponse<?> response = httpClient.toBlocking().exchange(request)

        then:
        noExceptionThrown()
        !response.headers.contains("Deprecation")

        where:
        version << ['3']
    }
}
