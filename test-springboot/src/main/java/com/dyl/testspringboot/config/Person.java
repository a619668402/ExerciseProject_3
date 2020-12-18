package com.dyl.testspringboot.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Validated
public class Person {

//    @NotNull(message = "名字不能为空")
    private String name;

    private int age;

    private List hobby;

    private Map<String, Object> maps;

    private Dog dog;

    public void validate() {

    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Dog {

    private String name;

    private String age;
}