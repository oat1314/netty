package com.learn.netty.server.server;

import com.learn.netty.server.initializer.MyChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.channels.ServerSocketChannel;

/**
 * @program: netty-hello
 * @author: ouguoxin
 * @create: 2020-09-23 17:30
 **/

public class MyChatServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8898).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭调度模块
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}

