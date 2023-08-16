package com.spring_oauth.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class YoutubeConfig {
    static private final String YOUTUBE_V3_API = "https://www.googleapis.com/youtube/v3";
    private static final String clientRegistrationId = "google";

    @Bean
    WebClient getWebClient(OAuth2AuthorizedClientManager oauthClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction filterFunc = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                oauthClientManager);
        filterFunc.setDefaultClientRegistrationId(clientRegistrationId);
        return WebClient.builder()
                .baseUrl(YOUTUBE_V3_API)
                .apply(filterFunc.oauth2Configuration())
                .build();
    }

    @Bean
    HttpServiceProxyFactory proxyFactory(WebClient oauth2WebClient) {
        return HttpServiceProxyFactory.builder() //
                .clientAdapter(WebClientAdapter.forClient(oauth2WebClient)) //
                .build();
    }

    @Bean
    Youtube client(HttpServiceProxyFactory factory) {
        return factory.createClient(Youtube.class);
    }
}
