package com.learn.netty.server.initializer;

import com.learn.netty.server.service.handler.CustomHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description: 初始化器，当channel初始化之后会执行里面相应的初始化方法
 * @Author ga.zhang
 * @Date 2019/12/16 14:44
 * @Version V1.0
 **/
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /**
         * 通过socketChannel获取pipeLine
         * 通过管道添加handler，HttpServerCodec是netty本身自带的助手类（处理器）
         * 他的作用是当请求到服务端时，HttpServerCodec对请求做一个解码，然后服务端响应到客户端时，对响应做一个编码
         */
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        /**
         * 添加自定义的助手类（处理器），返回“hello netty”
         */
        pipeline.addLast("customHandler", new CustomHandler());
    }

}
