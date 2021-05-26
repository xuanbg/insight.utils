package com.insight.utils.pojo;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 选项参数DTO
 */
public class ParameterDto extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 配置KEY
     */
    private String key;

    /**
     * 配置键值
     */
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
