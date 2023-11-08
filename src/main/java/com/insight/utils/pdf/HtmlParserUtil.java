package com.insight.utils.pdf;

import com.insight.utils.Util;
import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * @author 宣炳刚
 * @date 2023/10/25
 * @remark html片段解析工具
 */
public class HtmlParserUtil {

    /**
     * 实现对html片段解析
     *
     * @param html html代码
     * @param font 字体
     * @return List<Element>
     * @throws IOException IO操作异常
     */
    public static List<Element> html2Elements(String html, Font font) throws IOException {
        if (Util.isEmpty(html)) {
            return null;
        }

        List<Element> elements = HTMLWorker.parseToList(new StringReader(html), null);
        if (Util.isEmpty(elements)) {
            return null;
        }

        // 解析数据获取具体的element
        var chunks = new ArrayList<Element>();
        for (var element : elements) {
            if (element instanceof PdfPTable pdfPTable) {
                pdfPTable.getRows().stream().flatMap(row -> Arrays.stream(row.getCells()))
                        .forEach(cell -> setFont(font, cell.getCompositeElements()));
            } else {
                setFont(font, element.getChunks());
            }
        }

        return elements;
    }

    /**
     * 重新定义字体
     *
     * @param baseFont 标准字体
     * @param elements pdf元素
     */
    private static void setFont(Font baseFont, List<Element> elements) {
        elements.stream().filter(element -> element instanceof Chunk).map(element -> (Chunk) element).forEach(chunk -> {
            var chunkFont = chunk.getFont();
            var font = new Font(baseFont.getBaseFont(), chunkFont.getSize(), chunkFont.getStyle(), chunkFont.getColor());
            chunk.setFont(font);
        });
    }
}
