package com.insight.util.test;

import com.insight.util.ReplyHelper;
import junit.framework.TestCase;
import org.junit.Test;

public class ReplyHelperTest extends TestCase {
    @Test
    public void testReplyHelper() {


        System.out.println("success-----" + ReplyHelper.success().getCode());
        System.out.println("success-----" + ReplyHelper.success("成功啦！").getMessage());
    }
}
