package com.insight.util.pojo;

/**
 * @author duxl
 * @date 2017/11/17
 * @remark redis锁参数
 */
public class Param {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Param(String key){
        this.key = key;
        this.value = key;
    }
}
