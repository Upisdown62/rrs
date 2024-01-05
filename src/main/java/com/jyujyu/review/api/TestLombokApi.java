package com.jyujyu.review.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLombokApi {

    @GetMapping("/test/lombok")
    public TestLombokResponse testLombok(){
        return new TestLombokResponse("muscat", 10);
    }

    @Getter
    @AllArgsConstructor
    public static class TestLombokResponse{
        String name;
        Integer age;
    }
}
