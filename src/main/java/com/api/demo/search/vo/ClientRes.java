package com.api.demo.search.vo;

import lombok.Data;

import java.util.List;

@Data
public class ClientRes {
    private Meta meta;
    private List<Document> documents;
}
