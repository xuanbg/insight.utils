package com.insight.util.test;

import com.insight.util.file.FileHelper;
import junit.framework.TestCase;
import org.junit.Test;

public class FileHelperTest extends TestCase {


    @Test
    public void testFileHelper() {
        String cc = FileHelper.getUrlContent("http://www.baidu.com");
        System.out.println(cc);
    }

}
