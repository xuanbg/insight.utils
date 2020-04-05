package com.insight.utils.test;

import com.insight.utils.file.FileHelper;
import junit.framework.TestCase;
import org.junit.Test;

public class FileHelperTest extends TestCase {


    @Test
    public void testFileHelper() {
        String cc = FileHelper.getUrlContent("http://www.baidu.com");
        System.out.println(cc);
    }

}
