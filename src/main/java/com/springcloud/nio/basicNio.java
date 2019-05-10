package com.springcloud.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by yantao.chen on 2019-03-08
 */
public class basicNio {
    public static void main(String[] args) {
      nioReadWrite();
    }

    private static void nioRead(){
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\yantao.chen\\Desktop\\test.txt");
            FileChannel fc = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate( 1024 );
            fc.read(buffer);
            buffer.flip();
            Charset charset = Charset.forName("utf-8");
            System.out.println(charset.decode(buffer).toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nioWrite(){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream( "C:\\Users\\yantao.chen\\Desktop\\test.txt" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel fc = fout.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate( 1024 );
        Byte [] message = new Byte[1024];
        message[0] =  'c';
        message[1] =  'v';
        message[2] =  'b';
        for (int i=0; i<message.length; i++) {
            if(message[i] == null){
                break;
            }
            buffer.put( message[i] );
        }
        buffer.flip();
        try {
            fc.write( buffer );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nioReadWrite(){
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("C:\\Users\\yantao.chen\\Desktop\\test.txt");
            out = new FileOutputStream( "C:\\Users\\yantao.chen\\Desktop\\test.txt" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel fcin  = in.getChannel();
        FileChannel fcout = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate( 1024 );
        try {
            fcin.read(buffer);
            buffer.flip();
            fcout.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
