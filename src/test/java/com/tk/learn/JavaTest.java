package com.tk.learn;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.*;
import java.sql.Driver;
import java.util.*;


public class JavaTest{
    public static void main(String[] args) throws Exception {


        SpelExpressionParser ss = new SpelExpressionParser();
        Expression e = ss.parseExpression("1+2*3");
        Object value = e.getValue();
        System.out.println(value);
        Integer[] aa = {2, 9, 4, 7, 1, 3, 6};
        Arrays.sort( aa,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        System.out.println(Arrays.toString(aa));
    }
}
