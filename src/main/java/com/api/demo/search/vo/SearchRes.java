package com.api.demo.search.vo;

import com.api.demo.common.core.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class SearchRes {
    private Pagination pagination;
    private List<Document> documents;
}
