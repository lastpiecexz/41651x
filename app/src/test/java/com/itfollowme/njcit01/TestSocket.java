package com.itfollowme.njcit01;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by notre on 2018/3/20.
 */

public class TestSocket {
    @Test
    public void testInt(){
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void testSocketServer(){
        //所有的电脑都有端口
        //创建一个总机 总机的号码就是你的当前ip地址 Socket == 电话
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept(); //安排一个电话机接听电话
            //电话有 听筒和话筒
            //InputStream 听筒  OutputStream 话筒
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            char[] buf = new char[1];
            while(br.read(buf)!=-1){
                System.out.print(buf[0]);
            }

            br.close();
            //挂机
            socket.close();
            serverSocket.close();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketClient(){
        //打电话
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            //拿起话筒说句话
            OutputStream os = socket.getOutputStream();
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os));
            br.write("热烈庆祝十九大胜利闭幕！");
            br.newLine();
            br.flush();
            br.close();

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketServer2(){
        //所有的电脑都有端口
        //创建一个总机 总机的号码就是你的当前ip地址 Socket == 电话
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            final Socket socket = serverSocket.accept(); //安排一个电话机接听电话

            Thread thread = new Thread(){
                @Override
                public void run() {
                    //电话有 听筒和话筒
                    //InputStream 听筒  OutputStream 话筒
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        char[] buf = new char[1];
                        while (br.read(buf) != -1) {
                            System.out.print(buf[0]);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };
            thread.start();




            //挂机
//           socket.close();
//           serverSocket.close();
            //从键盘拿取一个输入
            Scanner scanner = new Scanner(System.in);
            OutputStream os = socket.getOutputStream();
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os));
            while(true) {

                String str = scanner.nextLine();

                br.write(str);
                br.newLine();
                br.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketClient2(){
        try {
            final Socket socket = new Socket("127.0.0.1",8888); //安排一个电话机接听电话

            Thread thread = new Thread(){
                @Override
                public void run() {
                    //电话有 听筒和话筒
                    //InputStream 听筒  OutputStream 话筒
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        char[] buf = new char[1];
                        while (br.read(buf) != -1) {
                            System.out.print(buf[0]);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };
            thread.start();




            //挂机
            //           socket.close();
            //           serverSocket.close();
            //从键盘拿取一个输入
            Scanner scanner = new Scanner(System.in);
            OutputStream os = socket.getOutputStream();
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os));
            while(true) {

                String str = scanner.nextLine();

                br.write(str);
                br.newLine();
                br.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
