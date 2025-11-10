package com.insight.utils.xkw;

import com.insight.utils.pojo.problem.Group;
import com.insight.utils.pojo.problem.Problem;
import com.xkw.xop.qbmsdk.QuestionParser;
import com.xkw.xop.qbmsdk.Setting;
import com.xkw.xop.qbmsdk.model.answer.An;
import com.xkw.xop.qbmsdk.model.answer.AnSq;
import com.xkw.xop.qbmsdk.model.explanation.ExplanationSeg;
import com.xkw.xop.qbmsdk.model.stem.Stem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * @author 宣炳刚
 * @date 2025/9/23
 * @remark
 */
public class GroupDto extends Group {

    /**
     * 章节ID
     */
    private Long unitId;

    /**
     * 试题组类型ID
     */
    private Long typeId;

    /**
     * 试题组难度系数
     */
    private BigDecimal difficulty;

    /**
     * 试题组能力等级
     */
    private Integer level;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 构造方法
     *
     * @param question 试题
     */
    public GroupDto(Question question) {
        if (question == null) {
            return;
        }

         typeId = question.getTypeId();
         difficulty = question.getDifficulty();
         level = 5 - difficulty.multiply(new BigDecimal(4)).setScale(0, RoundingMode.HALF_UP).intValue();

        setXkwId(question.getId());
        var parser = new QuestionParser(new Setting());
        var data = parser.splitQuestion(question.getStem(), question.getAnswer(), question.getExplanation());
        var stems = data.getStem();
        if (stems.getType().equals("复合题")) {
            setCaption(stems.getHtml());
            var sqs= stems.getSqs();
            for (var i = 0; i < sqs.size(); i++) {
                var stem = sqs.get(i);

                var anSqs = data.getAnswer().getAnSqs();
                var answer = anSqs.size() > i ? anSqs.get(i) : null;

                var expSqs = data.getExplanation().getExplanationSegs();
                var analyze = expSqs.size() > i ? expSqs.get(i) : null;

                var problem = createProblem(stem, answer, analyze);
                addProblem(problem);
            }
        } else {
            var answer = data.getAnswer().getAnSqs().get(0);
            var analyze = data.getExplanation().getExplanationSegs().isEmpty() ? null : data.getExplanation().getExplanationSegs().get(0);
            var problem = createProblem(stems, answer, analyze);
            addProblem(problem);
        }
    }

    /**
     * 创建试题
     *
     * @param stem     题干
     * @param answer   答案
     * @param analyze  解析
     * @return 试题
     */
    private Problem createProblem(Stem stem, AnSq answer, ExplanationSeg analyze) {
        var problem = new Problem();
        problem.setQuestion(stem.getHtml());
        if (stem.getType().equals("选择题")) {
            var map = new HashMap<String, String>();
            var options = stem.getOg().getOgOps();
            for (var option : options) {
                map.put(option.getIndex(), option.getHtml());
            }
            problem.setOption(map);
        }

        problem.setAnswer(answer == null ? null : answer.getAns().stream().map(An::getHtml).toList());
        problem.setAnalyze(analyze == null ? null : analyze.getHtml());
        problem.setBaseTime(BigDecimal.TEN.divide(difficulty, 2, RoundingMode.HALF_UP).intValue() * 10);
        problem.setDifficulty(difficulty);
        problem.setLevel(level);
        return problem;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
