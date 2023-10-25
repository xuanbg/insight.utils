package com.insight.utils.pdf;

import com.insight.utils.pojo.base.BaseXo;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/10/25
 * @remark
 */
public class PdfParam extends BaseXo {

    /**
     * pdf基础字体
     */
    private BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", Boolean.FALSE);

    /**
     * pdf字体样式
     */
    private Font font;

    /**
     * 字体大小
     */
    private Integer fontSize;

    private String header;

    /**
     * pdf标题基础字体
     */
    private BaseFont headerBaseFont;

    /**
     * pdf标题字体样式
     */
    private Font headerFont;

    /**
     * pdf标题字体大小
     */
    private Integer headerFontSize;

    /**
     * pdf标题宽度
     */
    private Float headerWidth;

    /**
     * pdf标题高度
     */
    private Float headerHeight;

    private List<PdfParagraph> paragraphs = new ArrayList<>();

    public PdfParam() throws IOException {
    }

    public Font getFont() {
        return font == null ? new Font(getBaseFont(), getFontSize()) : font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return fontSize == null ? 14 : fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public BaseFont getHeaderBaseFont() {
        return headerBaseFont == null ? baseFont : headerBaseFont;
    }

    public void setHeaderBaseFont(BaseFont headerBaseFont) {
        this.headerBaseFont = headerBaseFont;
    }

    public Font getHeaderFont() {
        return headerFont == null ? new Font(getHeaderBaseFont(), getHeaderFontSize(), Font.BOLD) : headerFont;
    }

    public void setHeaderFont(Font headerFont) {
        this.headerFont = headerFont;
    }

    public Integer getHeaderFontSize() {
        return headerFontSize == null ? 16 : headerFontSize;
    }

    public void setHeaderFontSize(Integer headerFontSize) {
        this.headerFontSize = headerFontSize;
    }

    public Float getHeaderWidth() {
        return headerWidth;
    }

    public void setHeaderWidth(Float headerWidth) {
        this.headerWidth = headerWidth;
    }

    public Float getHeaderHeight() {
        return headerHeight;
    }

    public void setHeaderHeight(Float headerHeight) {
        this.headerHeight = headerHeight;
    }

    public BaseFont getBaseFont() {
        return baseFont;
    }

    public void setBaseFont(BaseFont baseFont) {
        this.baseFont = baseFont;
    }

    public Font getTitleFont() {
        return new Font(getBaseFont(), getFontSize(), Font.BOLD);
    }

    public List<PdfParagraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<PdfParagraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public void addParagraph(PdfParagraph paragraph) {
        paragraphs.add(paragraph);
    }
}
