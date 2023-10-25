package com.insight.utils.pdf;

import com.insight.utils.pojo.base.BaseXo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/10/25
 * @remark 段落数据类
 */
public class PdfParagraph extends BaseXo {

    /**
     * 段落名称
     */
    private String name;

    /**
     * 段落内容
     */
    private List<String> content = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public void addContent(String str) {
        content.add(str);
    }
}
