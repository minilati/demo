package com.tk.learn.NIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一.通道（Channel）：用于源节点与目标节点得连接。在JAVA NIO中负责缓冲区数据的传输。Channel本身不存储数据，因此需要配合缓冲区进行传输.
 * 二.通道的主要实现类
 * java.nio.channels.Channel 接口：
 * //操作本地文件
 * --FileChannel
 * //网络数据传输
 * --SocketChannel
 * --ServerSocketChannel
 * --DatagramChannel
 * 三.获取通道
 * 1.java 针对支持通道的类提供了getChannel()方法
 * 本地IO：
 * FileInputStream/FileOutPutStream
 * RandomAccessFile
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2.在JDK 1.7 中的NIO.2 针对各个通道提供了静态方法 open()
 * 3.在JDK 1.7 中的NIO.2 的Files 工具类的newByteChannel()
 * <p>
 * 四.通道之间的数据传输
 * transferFrom()
 * transferTo()
 */
public class TestChannel {
    //1.利用通道完成文件的复制(非直接缓冲区)
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileInputStream fos = null;
        //获取通道
        FileChannel inchannel = null;
        FileChannel outchannel = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileInputStream("2.jpg");

            inchannel = fis.getChannel();
            outchannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区中
            while (inchannel.read(buffer) != -1) {
                //切换读取数据的模式
                buffer.flip();
                outchannel.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outchannel.close();
                inchannel.close();
                fos.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(start - end);
    }

    //2.使用直接缓冲区完成文件的复制(内存映射文件)
    @Test
    public void test2() throws Exception {
        long start = System.currentTimeMillis();

        FileChannel inchannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outchannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        //内存映射文件
        MappedByteBuffer inMappedBuf = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, inchannel.size());
        MappedByteBuffer outMappedBuf = outchannel.map(FileChannel.MapMode.READ_WRITE, 0, inchannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inchannel.close();
        outchannel.close();
        long end = System.currentTimeMillis();
        System.out.println(start - end);
    }

    //通道之间的数据传输(非直接缓冲区)
    @Test
    public void test3() throws Exception {
        FileChannel inchannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outchannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        //inchannel.transferTo(0,inchannel.size(),outchannel);
        outchannel.transferFrom(inchannel, 0, inchannel.size());

        inchannel.close();
        outchannel.close();
    }
}
