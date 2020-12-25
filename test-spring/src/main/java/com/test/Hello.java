package com.test;

import java.util.Map;

public class Hello {

    public Hello() {
        System.out.println("初始化");
    }

    public Hello(String name) {
        this.name = name;
    }
    private String name;

    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
