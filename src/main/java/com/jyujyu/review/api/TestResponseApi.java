package com.jyujyu.review.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResponseApi {

    @GetMapping("/test/response/string")
    public String stringResponse(){
        return "Thi is String";
    }

    @GetMapping("/test/response/json")
    public TestReponseBody jsonResponse(){
        return new TestReponseBody("must", 1);
    }

    public static class TestReponseBody {
        String name;
        Integer age;

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public TestReponseBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
