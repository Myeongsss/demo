package com.api.demo.common.core;

import lombok.Data;

@Data
public class Pagination {

    private int page = 1; // 현재 페이지
    private int pageSize = 10; // 한 페이지에 보여줄 항목 수
    private int totalItems; // 전체 항목 수
    private int totalPages; // 전체 페이지 수
    private int startIndex; // 검색 시작 위치
    private int endIndex; // 검색 끝 위치 (최대값)


    public void pageInfo(int page, int size, int total) {

        this.page = page;
        this.pageSize = size;
        this.totalItems = total;

        this.totalPages = (int) Math.ceil((double) totalItems / pageSize); // 전체 페이지 수
        this.startIndex = (page - 1) * pageSize + 1; // 검색 시작 위치
        this.endIndex = Math.min(startIndex + pageSize, totalItems) -1; // 검색 끝 위치 (최대값)

        // 50페이지로 한정
        if(this.totalPages > 50){
            this.totalPages = 50;
            this.totalItems = this.totalPages * this.pageSize;
        }

    }

}
