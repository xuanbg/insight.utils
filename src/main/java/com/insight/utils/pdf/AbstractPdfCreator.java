package com.insight.utils.pdf;

import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 宣炳刚
 * @date 2023/10/24
 * @remark
 */
public class AbstractPdfCreator implements PdfCreator {
    private static final String BOX_NAME = "art";
    protected Rectangle pageSize = PageSize.A4;
    protected Document document;
    private PdfWriter writer;
    private String path;

    /**
     * 初始化
     *
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     */
    @Override
    public PdfCreator init(String name) throws DocumentException, FileNotFoundException {
        return init(name, pageSize, null);
    }

    /**
     * 自定义初始化监听
     *
     * @param helper 自定义文档操作监听
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     */
    public PdfCreator init(String name, PdfPageEventHelper helper) throws DocumentException, FileNotFoundException {
        return init(name, pageSize, helper);
    }

    /**
     * 自定义初始化参数
     *
     * @param rectangle          页面大小设置
     * @param pdfPageEventHelper 文档操作监听
     * @return PdfCreator
     * @throws DocumentException 文档操作异常
     */
    public PdfCreator init(String name, Rectangle rectangle, PdfPageEventHelper pdfPageEventHelper) throws DocumentException, FileNotFoundException {
        path = "/opt/files/%s.pdf".formatted(name);
        document = new Document();
        document.setMargins(30, 30, 50, 90);
        var stream = new FileOutputStream(path);
        writer = PdfWriter.getInstance(document, stream);
        writer.setBoxSize(BOX_NAME, rectangle);
        if (pdfPageEventHelper != null) {
            writer.setPageEvent(pdfPageEventHelper);
        }

        document.open();
        return this;
    }

    /**
     * 构建pdf
     *
     * @param param 导出数据参数
     * @return ByteArrayOutputStream
     * @throws DocumentException 文档操作异常
     */
    @Override
    public String creator(PdfParam param) throws DocumentException {
        execute(param);
        close();

        return path;
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
                for (var str : paragraph.getContent()) {
                    if (str.matches("^<[\\s\\S]*>$")) {
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
     */
    @Override
    public void close() throws DocumentException {
        document.close();
        writer.close();
    }
}
