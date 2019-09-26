package com.insight.util.pojo;

/**
 * @author 宣炳刚
 * @date 2019/09/17
 * @remark redis锁参数
 */
public class LockParam {
    private final String key;
    private String value;

    /**
     * 读取Key
     *
     * @return Key
     */
    public String getKey() {
        return key;
    }

    /**
     * 读取Value
     *
     * @return Value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置Value
     *
     * @param value Value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 构造方法
     *
     * @param key Key\
     */
    public LockParam(String key) {
        this.key = key;
        this.value = key;
    }
}
