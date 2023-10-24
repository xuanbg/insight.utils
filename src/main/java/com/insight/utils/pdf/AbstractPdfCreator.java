package com.insight.utils.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2023/10/24
 * @remark
 */
public class AbstractPdfCreator implements PdfCreator, PdfCreateStrategy {

    /**
     * 页框大小,采用的类型有 "crop", "trim", "art" and "bleed".，这里采用 "art"
     */
    private static final String BOX_NAME = "art";

    /**
     * 文档类
     */
    protected Document document;

    /**
     * 基础字体
     */
    protected BaseFont baseFont;

    /**
     * 字体样式
     */
    protected Font font;

    /**
     * 字体大小
     */
    protected int fontSize;

    /**
     * 页面框架大小配置
     */
    protected Rectangle pageSize;

    /**
     * 字节输出流
     */
    private ByteArrayOutputStream byteArrayOutputStream;

    /**
     * 构建方法
     *
     * @param map 导出数据所需参数
     * @throws DocumentException 文档操作异常
     */
    @Override
    public void execute(Map<String, Object> map) throws DocumentException {

    }

    /**
     * 初始化
     *
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    @Override
    public PdfCreator init() throws DocumentException, IOException {
        return init(baseFont, fontSize, font, pageSize, null);
    }

    /**
     * 构建pdf
     *
     * @param map 导出数据参数
     * @return ByteArrayOutputStream
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    @Override
    public ByteArrayOutputStream creator(Map<String, Object> map) throws DocumentException, IOException {
        execute(map);
        close();
        return byteArrayOutputStream;
    }

    /**
     * 资源关闭
     *
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    @Override
    public void close() throws DocumentException, IOException {
        document.close();
        byteArrayOutputStream.close();
    }

    /**
     * 自定义初始化监听
     *
     * @param helper 自定义文档操作监听
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    public PdfCreator init(PdfPageEventHelper helper) throws DocumentException, IOException {
        return init(baseFont, fontSize, font, pageSize, helper);
    }

    /**
     * 自定义初始化参数
     *
     * @param baseFont           基础字体
     * @param fontSize           字体大小
     * @param font               字体样式
     * @param rectangle          页面大小设置
     * @param pdfPageEventHelper 文档操作监听
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    public PdfCreator init(BaseFont baseFont, int fontSize, Font font, Rectangle rectangle, PdfPageEventHelper pdfPageEventHelper) throws DocumentException, IOException {
        this.baseFont = null != baseFont ? baseFont : BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", Boolean.FALSE);
        this.fontSize = 0 != fontSize ? fontSize : Header.PARAGRAPH;
        this.font = null != font ? font : new Font(this.baseFont, this.fontSize, Font.NORMAL);
        this.pageSize = null != pageSize ? pageSize : PageSize.A4;

        document = new Document();
        document.setMargins(30, 30, 50, 90);
        byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        writer.setBoxSize(BOX_NAME, this.pageSize);
        if (null != pdfPageEventHelper) {
            writer.setPageEvent(pdfPageEventHelper);
        }
        document.open();
        return this;
    }
}
