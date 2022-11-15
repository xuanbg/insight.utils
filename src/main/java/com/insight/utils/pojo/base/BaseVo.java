package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2022/2/25
 * @remark 基础VO
 */
public class BaseVo extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
