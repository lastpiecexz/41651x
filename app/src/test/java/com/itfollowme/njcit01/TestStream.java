package com.itfollowme.njcit01;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by notre on 2018/3/6.
 */

public class TestStream {

    @Test
    public void testReadFile() {
        //从一个文件中读取数据，并打印出来
        //文件就是水桶
        //插一个管子进水桶
        //从里面吸水
        //插入文件的管子是一种特殊的管子
        //有各种不同的管子
        try {
            //准备一个专门读文件的管子
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("g:\\students\\tomcat.png")));

            /*//准备一个盆。盆的大小就似乎文件的长度
            byte[] buf = new byte[(int) file.length()];
            //从管子中读取指定长度的内容到盆里面
            fis.read(buf);

            System.out.println(new String(buf));*/

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("g:\\2.png")));

            byte[] buf = new byte[1];
            while (bis.read(buf) != -1) {
                bos.write(buf);
            }

            //关闭水管
            bis.close();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInt2Byte(){
        int i = 12345678; //‭10111100 01100001 01001110‬
        byte b = (byte)(i&0xFF);
        System.out.println(b);
        byte c = (byte)((i>>8)&0xFF);
        System.out.println(c);
    }

    @Test
    public void testDataStream(){
        int hp = 12345678;
        File file = new File("g:\\1.dat");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(hp);
            dos.writeUTF("今天下雨了");
            dos.flush();
            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReadDataStream(){
        try {
            FileInputStream fis = new FileInputStream(new File("g:\\1.dat"));
            DataInputStream dis = new DataInputStream(new BufferedInputStream(fis));
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());

            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadFile2() {
        try {
/*            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("g:\\1.txt")));



            byte[] buf = new byte[1];
            while (bis.read(buf) != -1) {
                System.out.println(new String(buf));
            }

            //关闭水管
            bis.close();*/
            //InputStream Reader
            //OutputStream Writer
            FileReader fileReader = new FileReader("g:\\1.txt");
            char[] buf = new char[1];
            while (fileReader.read(buf) != -1) {
                System.out.println(new String(buf));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
