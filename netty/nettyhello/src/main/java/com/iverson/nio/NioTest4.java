package com.iverson.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: netty-hello
 * @author: ouguoxin
 * @create: 2020-09-27 11:03
 **/

public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();

            int read = inputChannel.read(buffer);

            if (-1 ==read) {
                break;
            }
            buffer.flip();

            outChannel.write(buffer);
        }
        inputChannel.close();
        outChannel.close();
    }

}

