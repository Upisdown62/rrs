package com.jyujyu.review.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestRequestApi {

    @GetMapping("/test/param")
    public String requestParam(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age){
        return "Hello, Request Param, I am " + name + ", " + age;
    }

    @GetMapping("/test/path/{name}/{age}")
    public String requestPathVariable(
            @PathVariable("name") String name,
            @PathVariable("age") Integer age
    ){
        return "Hello, Request Path Variable, I am " + name + ", " + age;
    }

    @PostMapping("/test/body")
    public String requestBody(
            @RequestBody TestRequestBody request
    ){
        return "Hello, Request Body, I am " + request.name + ", " + request.age;
    }

    public static class TestRequestBody{
        String name;
        Integer age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public TestRequestBody() {

        }
    }
}
