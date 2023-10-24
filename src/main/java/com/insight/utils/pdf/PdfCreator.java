package com.insight.utils.pdf;

import com.lowagie.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2023/10/24
 * @remark pdf生成器接口
 */
public interface PdfCreator {

    /**
     * 初始化
     *
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    PdfCreator init() throws DocumentException, IOException;

    /**
     * 构建pdf
     *
     * @param map 导出数据参数
     * @return ByteArrayOutputStream
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    ByteArrayOutputStream creator(Map<String, Object> map) throws DocumentException, IOException;

    /**
     * 资源关闭
     *
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    void close() throws DocumentException, IOException;
}
