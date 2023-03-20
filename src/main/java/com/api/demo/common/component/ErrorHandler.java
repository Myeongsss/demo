package com.api.demo.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus httpStatusCode = response.getStatusCode();

        boolean httpStatusIs200OK = httpStatusCode == HttpStatus.OK;

        boolean hasError = !(httpStatusIs200OK);
        if (hasError) {
            log.error("# Wrong response : {}", httpStatusCode);
        } else {
            log.debug("# Right response : {}", httpStatusCode);
        }
        return hasError;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String message = "Client Call Fail. " + errorBody(response.getBody());
        throw new RuntimeException(message);
    }

    private String errorBody(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (
                OutputStreamWriter writer = new OutputStreamWriter(out);
                InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)
        ) {
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n' || ch == '\r') {
                    continue;
                }
                writer.write(ch);
            }
        }

        return out.toString();
    }
}
