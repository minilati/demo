package com.tk.demo;

import org.junit.jupiter.api.Test;

public class JavaTest {
    @Test
    public void testBytes(){
        byte b = Byte.parseByte("-128");
        System.out.println(b);
    }
}
