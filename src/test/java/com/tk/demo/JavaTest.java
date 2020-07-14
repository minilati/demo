package com.tk.demo;

import org.junit.jupiter.api.Test;

public class JavaTest {
    @Test
    public void testBytes(){
        byte b = Byte.parseByte("-128");
        System.out.println(b);
    }

    @Test
    public void testSub(){
        String s ="01234567";
        System.out.println(s.length());
        System.out.println(s.substring(0,8));
    }
}
