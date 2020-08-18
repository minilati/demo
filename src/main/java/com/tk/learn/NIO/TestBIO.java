package com.tk.learn.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBIO {
    public static void main(String[] args) throws Exception {

        ClientThread ct = new ClientThread();
        ServerThread st = new ServerThread();


        st.start();
        Thread.sleep(1000);
        ct.start();

    }

    static class ClientThread extends Thread {
        @Override
        public void run() {
            SocketChannel sc = null;
            FileChannel fc = null;
            try {
                sc = SocketChannel.open(new InetSocketAddress(9527));
                fc = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
                ByteBuffer buf = ByteBuffer.allocate(1000);

                while (fc.read(buf) != -1) {
                    buf.flip();
                    sc.write(buf);
                    buf.clear();
                }

                //关闭客户端输出流
                sc.shutdownOutput();

                //读取服务端返回数据
                int len;
                while ((len = sc.read(buf)) != -1) {
                    buf.flip();
                    System.out.println(new String(buf.array(), 0, len));
                    buf.clear();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    sc.close();
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ServerThread extends Thread {
        @Override
        public void run() {
            ServerSocketChannel ssc = null;
            SocketChannel sc = null;
            FileChannel fc = null;
            try {
                ssc = ServerSocketChannel.open();
                fc = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                ByteBuffer buf = ByteBuffer.allocate(1000);

                ssc.bind(new InetSocketAddress(9527));
                sc = ssc.accept();

                while (sc.read(buf) != -1) {
                    buf.flip();
                    fc.write(buf);
                    buf.clear();
                }
                buf.put("Write Success!".getBytes());
                buf.flip();
                sc.write(buf);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    ssc.close();
                    sc.close();
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
