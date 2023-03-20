package com.api.demo.search.controller;

import com.api.demo.search.entity.Search;
import com.api.demo.search.service.SearchService;
import com.api.demo.search.vo.SearchReq;
import com.api.demo.search.vo.SearchRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/blog/v1", method = RequestMethod.POST)
    public SearchRes searchBlog(@RequestBody SearchReq req) {

        log.debug("SearchReq={}", req);

        SearchRes res = searchService.search(req);

        return res;
    }

    @RequestMapping(value = "/popular/keyword/v1", method = RequestMethod.POST)
    public List<Search> popularKeyword() {

        List<Search> list = searchService.popularKeyword();

        return list;
    }

}
