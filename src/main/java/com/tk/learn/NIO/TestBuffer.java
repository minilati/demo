package com.tk.learn.NIO;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 一.缓冲区(Buffer)：在Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 二.缓冲区存取数据的两个核心方法
 *      put():存入数据到缓冲区中
 *      get():获取缓冲区的数据
 *
 * 三.缓冲区中的四个核心属性
 * capacity:容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变
 * limit:界限，表示缓冲区中可以操作数据的大小。(limit 后数据不能进行读写)
 * position:位置，表示缓冲区中正在操作数据的位置。
 * make:标记，表示记录当前position的位置。可以通过reset()恢复到mark的位置
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 */
public class TestBuffer {

    @Test
    public void test1(){
        String str = "mocha";

        //1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("--------allocate()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2.利用put() 存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("--------put()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3.flip() 切换读取数据模式
        //将limit指针指向当前position，将position指针指向0。
        buf.flip();

        System.out.println("--------flip()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4.利用get() 获取缓冲区的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);

        System.out.println("--------get()---------");
        System.out.println(new String(dst,0,dst.length));
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5.rewind():可重复读
        buf.rewind();
        System.out.println("--------rewind()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6.clear():清空缓冲区.但是缓冲区中的数据依然存在，但是处于"被遗忘"状态
        buf.clear();
        System.out.println("--------clear()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char) buf.get());
    }

    @Test
    public void test2(){
        String str = "mocha";

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst,0,2);
        System.out.println(new String(dst,0,2));
        System.out.println(buf.position());

        //mark():标记
        buf.mark();

        buf.get(dst,2,2);
        System.out.println(new String(dst,2,2));
        System.out.println(buf.position());

        //reset():恢复到mark的位置
        buf.reset();
        System.out.println(buf.position());

        //判断缓冲区是否还有剩余数据
        if (buf.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());
        }
    }

    //字符集Charset
    @Test
    public void test6() throws Exception {
        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("张三");
        cBuf.flip();
        //编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for (int i = 0; i < bBuf.limit(); i++) {
            System.out.println(bBuf.get());
        }
        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("*************************");
        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }
}
