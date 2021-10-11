package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterPatternStyle;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * <a href="https://datatracker.ietf.org/doc/html/draft-dalal-deprecation-header-03">The Deprecation HTTP Header Field</a>
 * Adds a Deprecation true Http HEADER to deprecated endpoints
 */
@Filter(patternStyle = FilterPatternStyle.REGEX, patterns = "/api/v(1|2)/.*")
public class DeprecatedApiVersionsFilter implements HttpServerFilter {

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        return Flux.from(chain.proceed(request))
                .map(response -> response.header("Deprecation", "true"));
    }
}
