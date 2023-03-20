package com.api.demo.common.component;

import com.api.demo.common.properties.CoreProperties;
import com.api.demo.search.vo.SearchRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Slf4j
@Component
public class Client {

    @Autowired
    @Qualifier("kakaoRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private CoreProperties properties;


    public <T> T post(String path, Class<T> responseType, Object body) {

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(path).build();

        HttpEntity<?> entity = new HttpEntity<>(body, makeHeaders());

        return restTemplate.postForEntity(uriComponents.toUri(), entity, responseType).getBody();

    }

    private HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();

//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", properties.getKakaoAuthKey());

        return headers;
    }

}
