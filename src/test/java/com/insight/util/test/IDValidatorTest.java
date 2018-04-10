package com.insight.util.test;

import com.insight.util.IDValidator;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

public class IDValidatorTest extends TestCase {


    @Test
    public void testValidHelper() throws IOException {
        System.out.println(IDValidator.isValidIdcard("371322xxxsw"));
        System.out.println(IDValidator.isValidIdcard("371322xxxsw"));
    }

}
