package com.insight.utils.pdf;

import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.*;

import java.io.IOException;

/**
 * @author 宣炳刚
 * @date 2023/10/24
 * @remark
 */
public class PdfPageEventListener extends PdfPageEventHelper {

    /**
     * pdf编号（一般左上角都会有类似流水号的编号，可有可无）
     */
    private final String noticeCode;

    /**
     * pdf签字（可有可无，根据实际情况进行赋值）
     */
    private final String signature;

    /**
     * pdf标题字体大小
     */
    private final int headerFontSize;

    /**
     * pdf标题宽度
     */
    private final Float headerWidth;

    /**
     * pdf标题高度
     */
    private final Float headerHeight;

    /**
     * pdf标题模板
     */
    private PdfTemplate headerTemplate;

    /**
     * pdf标题基础字体
     */
    private BaseFont headerBaseFont;

    /**
     * pdf标题字体样式
     */
    private Font headerFont;

    /**
     * 构建器，进行基本信息初始化
     *
     * @param headerFontSize pdf标题字体大小
     * @param headerWidth    pdf标题宽度
     * @param headerHeight   pdf标题高度
     * @param headerBaseFont pdf标题基础字体
     * @param headerFont     pdf标题字体样式
     * @param noticeCode     pdf编号
     * @param signature      pdf签字
     */
    public PdfPageEventListener(int headerFontSize, Float headerWidth, Float headerHeight, BaseFont headerBaseFont, Font headerFont, String noticeCode, String signature) {
        this.headerFontSize = headerFontSize;
        this.headerWidth = headerWidth;
        this.headerHeight = headerHeight;
        this.headerBaseFont = headerBaseFont;
        this.headerFont = headerFont;
        this.noticeCode = noticeCode;
        this.signature = signature;
    }

    /**
     * pdf监视器构建
     *
     * @return Builder
     */
    public static Builder build() {
        return new Builder();
    }

    /**
     * 重写文档初始化的模板
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        headerTemplate = writer.getDirectContent().createTemplate(headerWidth, headerHeight);
    }

    /**
     * 重写每一页结束事件，等所有内容加载完毕后，进行页眉的数据加载和赋值
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            if (headerBaseFont == null) {
                headerBaseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", Boolean.FALSE);
            }
            if (headerFont == null) {
                headerFont = new Font(headerBaseFont, headerFontSize, Font.NORMAL);
            }
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
        //获取文档内容
        PdfContentByte pdfContentByte = writer.getDirectContent();
        float left = document.left();
        float right = document.right();
        float top = document.top();
        float bottom = document.bottom();
        int pageNumber = writer.getPageNumber();
        String noticeCodeText = Util.isEmpty(noticeCode) ? "" : "编号：" + noticeCode;
        String previousHeaderText = "第 " + pageNumber + " 页 /共";
        Phrase headerPhrase = new Phrase(previousHeaderText, headerFont);
        Phrase noticePhrase = new Phrase(noticeCodeText, headerFont);
        Phrase signaturePhrase = new Phrase(signature, headerFont);
        float noticeLen = headerBaseFont.getWidthPoint(noticeCodeText, headerFontSize);
        float headerLen = headerBaseFont.getWidthPoint(previousHeaderText, headerFontSize);
        float signatureLen = headerBaseFont.getWidthPoint(signature, headerFontSize);
        float x0 = left + noticeLen / 2;
        float x1 = right - headerLen / 2 - 20F;
        float x2 = right - 20F;
        float x3 = right - signatureLen / 2;
        float y1 = top + 10F;
        float y2 = bottom - 40F;
        ColumnText.showTextAligned(pdfContentByte, Element.ALIGN_CENTER, noticePhrase, x0, y1, Element.HEADER);
        ColumnText.showTextAligned(pdfContentByte, Element.ALIGN_CENTER, headerPhrase, x1, y1, Element.HEADER);
        ColumnText.showTextAligned(pdfContentByte, Element.ALIGN_CENTER, signaturePhrase, x3, y2, Element.HEADER);
        pdfContentByte.addTemplate(headerTemplate, x2, y1);

        float lineY = top + 6f;

        CMYKColor magentaColor = new CMYKColor(1.f, 1.f, 1.f, 1.f);
        pdfContentByte.setColorStroke(magentaColor);
        pdfContentByte.moveTo(left, lineY);
        pdfContentByte.lineTo(left, lineY);
        pdfContentByte.closePathStroke();
    }

    /**
     * 文档结束后，将总页码放在文档上
     */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        headerTemplate.beginText();
        headerTemplate.setFontAndSize(headerBaseFont, headerFontSize);
        int pageNumber = writer.getPageNumber() - 1;
        String behindHeaderText = " " + pageNumber + " 页";
        headerTemplate.showText(behindHeaderText);
        headerTemplate.endText();
        headerTemplate.closePath();
    }

    /**
     * 自定义一个构建器，设置页面相关信息
     */
    public static class Builder {
        private int headerFontSize;
        private Float headerWidth;
        private Float headerHeight;
        private BaseFont headerBaseFont;
        private Font headerFont;
        private String noticeCode;
        private String signature;

        Builder() {
            headerFontSize = Font.DEFAULTSIZE;
            headerWidth = 50F;
            headerHeight = 50F;
            try {
                headerBaseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", Boolean.FALSE);
            } catch (IOException e) {
                throw new BusinessException(e.getMessage());
            }
            headerFont = new Font(headerBaseFont, headerFontSize, Font.NORMAL);
            noticeCode = "";
            signature = "";
        }

        public Builder setHeaderFontSize(int headerFontSize) {
            this.headerFontSize = headerFontSize;
            return this;
        }

        public Builder setHeaderWidth(Float headerWidth) {
            this.headerWidth = headerWidth;
            return this;

        }

        public Builder setHeaderHeight(Float headerHeight) {
            this.headerHeight = headerHeight;
            return this;
        }

        public Builder setHeaderTemplate(PdfTemplate headerTemplate) {
            return this;
        }

        public Builder setHeaderBaseFont(BaseFont headerBaseFont) {
            this.headerBaseFont = headerBaseFont;
            return this;
        }

        public Builder setHeaderFont(Font headerFont) {
            this.headerFont = headerFont;
            return this;
        }

        public Builder setNoticeCode(String noticeCode) {
            this.noticeCode = noticeCode;
            return this;
        }

        public Builder setSignature(String signature) {
            this.signature = signature;
            return this;
        }

        public PdfPageEventListener build() {
            return new PdfPageEventListener(headerFontSize, headerWidth, headerHeight, headerBaseFont, headerFont, noticeCode, signature);
        }
    }
}
