package com.api.demo.common.component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateClient {

    private static final int CONNECT_TIMEOUT = 2000; // 2 sec
    private static final int READ_TIMEOUT = 4000; // 4 sec

    @Bean(name = "kakaoRestTemplate")
    public RestTemplate kakaoRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // RequestFactory
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        simpleClientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory));

        // MessageConverters
//        restTemplate.setMessageConverters(messageConverters());

        // Error handler
        restTemplate.setErrorHandler(new ErrorHandler());

        return restTemplate;
    }

//    private List<HttpMessageConverter<?>> messageConverters() {
//
//        StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter();
//        stringMessageConverter.setWriteAcceptCharset(false);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
//        jsonMessageConverter.setPrettyPrint(true);
//        jsonMessageConverter.setObjectMapper(objectMapper);
//
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        messageConverters.add(stringMessageConverter);
//        messageConverters.add(jsonMessageConverter);
//
//        return messageConverters;
//    }

}