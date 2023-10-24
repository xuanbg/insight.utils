package com.insight.utils.pdf;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2023/10/24
 * @remark 构建策略接口
 */
public interface PdfCreateStrategy {

    /**
     * 构建方法
     *
     * @param map 导出数据所需参数
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    void execute(Map<String, Object> map) throws DocumentException, IOException;
}
