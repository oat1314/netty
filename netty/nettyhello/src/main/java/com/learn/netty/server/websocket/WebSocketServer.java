package com.learn.netty.server.websocket;

import com.learn.netty.server.initializer.WebSocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/16 17:22
 * @Version V1.0
 **/
public class WebSocketServer {

    private static final Integer SERVER_PORT = 8088;

    public static void main(String[] args) throws InterruptedException {
        /**定义一对线程组*/
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**定义启动类*/
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketServerInitializer());
            ChannelFuture channelFuture = server.bind(SERVER_PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
