package com.insight.utils.test;

public class ExcelClass {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String toString() {
        return "ExcelClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
