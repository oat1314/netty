package com.iverson.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @program: netty-hello
 * @author: ouguoxin
 * @create: 2020-09-27 09:38
 **/

public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        System.out.println("capacity:" + buffer.capacity());

        for (int i =0;i<5;++i) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        System.out.println("before flip limit:" + buffer.limit());

        buffer.flip();

        System.out.println("afer filp limit:" + buffer.limit());

        System.out.println("enter while loop");

        while (buffer.hasRemaining()) {
            System.out.println("position:" +buffer.position());

            System.out.println("limit:" + buffer.limit());

            System.out.println("capacity:" + buffer.capacity());

            System.out.println(buffer.get());
        }
    }

}

