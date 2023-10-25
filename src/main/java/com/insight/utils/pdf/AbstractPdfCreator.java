package com.insight.utils.pdf;

import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author 宣炳刚
 * @date 2023/10/24
 * @remark
 */
public class AbstractPdfCreator implements PdfCreator {

    /**
     * 页框大小,采用的类型有 "crop", "trim", "art" and "bleed".，这里采用 "art"
     */
    private static final String BOX_NAME = "art";

    /**
     * 文档类
     */
    protected Document document;

    /**
     * 页面框架大小配置
     */
    protected Rectangle pageSize;

    /**
     * 字节输出流
     */
    private ByteArrayOutputStream byteArrayOutputStream;

    /**
     * 初始化
     *
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     */
    @Override
    public PdfCreator init() throws DocumentException {
        return init(pageSize, null);
    }

    /**
     * 构建pdf
     *
     * @param param 导出数据参数
     * @return ByteArrayOutputStream
     * @throws DocumentException 文档操作异常
     * @throws IOException       IO操作异常
     */
    @Override
    public ByteArrayOutputStream creator(PdfParam param) throws DocumentException, IOException {
        execute(param);
        close();
        return byteArrayOutputStream;
    }

    /**
     * pdf构建执行
     *
     * @param param 导出数据所需参数
     * @throws DocumentException 文档操作异常
     */
    @Override
    public void execute(PdfParam param) throws DocumentException {
        var header = new Paragraph(param.getHeader(), param.getHeaderFont());
        header.setAlignment(Element.ALIGN_CENTER);
        header.setSpacingAfter(10F);
        document.add(header);

        param.getParagraphs().forEach(paragraph -> {
            if (Util.isNotEmpty(paragraph.getName())) {
                var title = new Paragraph(paragraph.getName(), param.getFont());
                title.setAlignment(Element.ALIGN_LEFT);
                document.add(title);
            }

            if (Util.isNotEmpty(paragraph.getContent())) {
                var content = new Paragraph();
                var list = paragraph.getContent();
                for (var str : list) {
                    if (str.matches("^<.*>$")) {
                        try {
                            content.addAll(HtmlParserUtil.html2Elements(str, param.getFont()));
                        } catch (IOException e) {
                            throw new BusinessException(e.getMessage());
                        }
                    } else {
                        content.add(new Chunk(str, param.getFont()));
                    }
                }
                content.setAlignment(Element.ALIGN_LEFT);
                document.add(content);
            }
        });
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
     */
    public PdfCreator init(PdfPageEventHelper helper) throws DocumentException {
        return init(pageSize, helper);
    }

    /**
     * 自定义初始化参数
     *
     * @param rectangle          页面大小设置
     * @param pdfPageEventHelper 文档操作监听
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     */
    public PdfCreator init(Rectangle rectangle, PdfPageEventHelper pdfPageEventHelper) throws DocumentException {
        this.pageSize = null != pageSize ? pageSize : PageSize.A4;

        document = new Document();
        document.setMargins(30, 30, 50, 90);
        byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        writer.setBoxSize(BOX_NAME, this.pageSize);
        if (pdfPageEventHelper != null) {
            writer.setPageEvent(pdfPageEventHelper);
        }

        document.open();
        return this;
    }
}
