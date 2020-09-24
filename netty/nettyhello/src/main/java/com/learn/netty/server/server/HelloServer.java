package com.learn.netty.server.server;

import com.learn.netty.server.initializer.HelloServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description: 使用主从线程模型，实现客户端发起请求，服务端返回hello netty
 * @Author ga.zhang
 * @Date 2019/12/16 14:11
 * @Version V1.0
 **/
public class HelloServer {

    private static final Integer SERVER_PORT = 8000;

    public static void main(String[] args) throws InterruptedException {
        /**
         * 定义两个线程组
         * 主线程组：用于接受客户端连接，但不处理任何请求
         * 从线程组：主线程组会把请求丢给从线程组，从线程组对请求做相应处理
         */
        EventLoopGroup masterGroup = new NioEventLoopGroup();
        EventLoopGroup slaveGroup = new NioEventLoopGroup();

        try {
            /**
             * netty服务器的创建
             * ServerBootstrap为线程组启动类，将主从线程组放入到启动类中,并指定客户端与服务端建立连接的channel的类型为NioServerSocketChannel
             * childHandler为子处理器，用于处理slaveGroup
             */
            ServerBootstrap server = new ServerBootstrap();
            server.group(masterGroup, slaveGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HelloServerInitializer());

            /**
             * 启动server，并指定启动端口为8088，启动方式为同步
             */
            ChannelFuture channelFuture = server.bind(SERVER_PORT).sync();

            /**
             * 设置监听，关闭通道，关闭方式为同步
             */
            channelFuture.channel().closeFuture().sync();
        } finally {
            /**
             * 优雅的关闭主从线程组
             */
            masterGroup.shutdownGracefully();
            slaveGroup.shutdownGracefully();
        }

    }

}
