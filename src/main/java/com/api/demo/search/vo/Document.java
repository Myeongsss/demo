package com.api.demo.search.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Document {

    private String contents;
    private Date datetime;
    private String title;
    private String url;

}
