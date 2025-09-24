package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.DataBase;
import com.insight.utils.pojo.paper.AttachFile;

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

    @JsonIgnore
    public Integer getResourceType() {
        if (type == null || type < 1 || type > 8) {
            throw new BusinessException("不支持的模块类型");
        }

        return switch (type) {
            case 3 -> 7;
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
}
