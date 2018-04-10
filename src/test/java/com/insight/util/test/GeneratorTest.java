package com.insight.util.test;

import com.insight.util.Generator;
import junit.framework.TestCase;
import org.junit.Test;

public class GeneratorTest extends TestCase {


    @Test
    public void testGenerator() {
        String uuid = Generator.randomAlphanumeric(10);
        System.out.println(uuid);

    }

}
