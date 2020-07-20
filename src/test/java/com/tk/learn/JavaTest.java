package com.tk.learn;

import org.junit.Test;
import java.io.*;


public class JavaTest{
    public static void main(String[] args) {
        Proto proto = new Proto();
        Proto clone1 = (Proto)proto.clone();
        Proto clone2 = (Proto)proto.clone();
        System.out.println(proto.temp.hashCode());
        System.out.println(clone1.temp.hashCode());
        System.out.println(clone2.temp.hashCode());
    }
}

class Proto implements Serializable,Cloneable {
    int id;
    String name;
    Temp temp;

    public Proto(){
        id=1;
        name="牛牛";
        temp = new Temp();
    }

    @Override
    protected Object clone() {

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); //当前这个对象以对象流的方式输出

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            Proto copy = (Proto) ois.readObject();
            return copy;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                assert bos != null;
                bos.close();
                assert oos != null;
                oos.close();
                assert bis != null;
                bis.close();
                assert ois != null;
                ois.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
class Temp implements Serializable{
    int num;
    String Type;
    public Temp(){
        num=10;
        Type="动物";
    }
}