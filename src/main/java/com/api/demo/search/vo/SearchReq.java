package com.api.demo.search.vo;

import lombok.Data;

@Data
public class SearchReq {

    private String query;
    private String sort;
    private int page;
    private int size;

}
