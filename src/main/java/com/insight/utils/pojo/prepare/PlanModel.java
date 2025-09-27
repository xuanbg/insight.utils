package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.DataBase;
import com.insight.utils.pojo.basedata.AttachFile;

import java.util.List;
import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2024/12/5
 * @remark 学案内容模块实体类
 */
public class PlanModel extends BaseXo {

    /**
     * 序号
     */
    private Integer serial;

    /**
     * 类型: 1.目标, 2.学案, 3.练习, 4.上课, 5.网课, 6.总结, 7.测验, 8.资料
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 资源
     */
    private DataBase resource;

    /**
     * 数据
     */
    private Resource data;

    /**
     * 默认构造函数
     */
    public PlanModel() {
    }

    /**
     * 构造函数
     *
     * @param type  类型
     * @param name  名称
     */
    public PlanModel(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataBase getResource() {
        return resource;
    }

    public void setResource(DataBase resource) {
        this.resource = resource;
    }

    public Resource getData() {
        return data;
    }

    public void setData(Resource data) {
        this.data = data;
    }

    /**
     * 是否有资源
     *
     * @return 是否有资源
     */
    @JsonIgnore
    public Boolean hasResource() {
        return resource != null;
    }

    /**
     * 获取资源ID
     *
     * @return 资源ID
     */
    @JsonIgnore
    public Long getResourceId() {
        return resource == null ? null : resource.getId();
    }

    /**
     * 获取资源类型
     *
     * @return 资源类型
     */
    @JsonIgnore
    public Integer getResourceType() {
        if (type == null || type < 1 || type > 8) {
            throw new BusinessException("不支持的模块类型");
        }

        return switch (type) {
            case 3 -> 5;
            case 4 -> 3;
            case 5 -> 4;
            case 6 -> 1;
            case 7 -> 6;
            case 8 -> {
                var content = Json.toBean(data.getContent(), AttachFile.class);
                if (content == null || content.getResourceType() == null) {
                    throw new BusinessException("不支持的资源类型");
                }

                yield content.getResourceType();
            }
            default -> type;
        };
    }

    /**
     * 获取模型类型
     *
     * @param type 模型类型
     * @return 模型类型
     */
    @JsonIgnore
    public Integer getModelType(Integer type) {
        return switch (this.type) {
            case 1, 6 -> type == 1 ? resource == null ? this.type : 0 : 0;
            case 2 -> type == 2 ? resource == null ? this.type : 20 - this.type : (List.of(3, 4, 9).contains(type) ? (resource == null ? 0 : 20 - this.type) : 0);
            case 3 -> type == 5 || type == 7 ? resource == null ? this.type : 0 : 0;
            case 4 -> type == 3 ? resource == null ? this.type : 20 - this.type : (List.of(2, 4, 9).contains(type) ? (resource == null ? 0 : 20 - this.type) : 0);
            case 5 -> type == 4 ? resource == null ? this.type : 20 - this.type : (List.of(2, 3, 9).contains(type) ? (resource == null ? 0 : 20 - this.type) : 0);
            case 7 -> type == 6 ? resource == null ? this.type : 0 : 0;
            case 8 -> type == 2 || type == 3 ? (resource == null ? this.type : 20 - this.type) : List.of(4, 9).contains(type) ? (resource == null ? 0 : 20 - this.type) : 0;
            default -> throw new BusinessException("为定义的模块类型");
        };
    }

    /**
     * 是否为空总结
     *
     * @return 是否为空总结
     */
    @JsonIgnore
    public Boolean isEmptySumUp() {
        return type == 6 && resource == null;
    }

    /**
     * 类型是否相等
     *
     * @param type 类型
     * @return 是否相等
     */
    @JsonIgnore
    public Boolean typeEquals(Integer type) {
        return Objects.equals(this.type, type);
    }

    /**
     * 清空数据
     */
    @JsonIgnore
    public void clearData() {
        if (data != null) {
            resource = data.convert(DataBase.class);
            data = null;
        }
    }
}
