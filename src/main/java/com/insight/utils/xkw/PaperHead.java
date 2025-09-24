package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class PaperHead extends BaseXo {

    /**
     * 主标题
     */
    @JsonAlias("main_title")
    private String mainTitle;

    /**
     * 副标题
     */
    @JsonAlias("sub_title")
    private String subTitle;

    /**
     * 考试须知
     */
    private String notice;

    /**
     * 试卷信息
     */
    @JsonAlias("test_info")
    private String testInfo;

    /**
     * 学生填写部分
     */
    @JsonAlias("student_input")
    private String studentInput;

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getTestInfo() {
        return testInfo;
    }

    public void setTestInfo(String testInfo) {
        this.testInfo = testInfo;
    }

    public String getStudentInput() {
        return studentInput;
    }

    public void setStudentInput(String studentInput) {
        this.studentInput = studentInput;
    }
}
