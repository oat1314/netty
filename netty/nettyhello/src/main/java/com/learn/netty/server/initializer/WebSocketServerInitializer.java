package com.learn.netty.server.initializer;

import com.learn.netty.server.service.handler.WebSocketChatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/17 9:11
 * @Version V1.0
 **/
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        /**webSocket是基于http协议的，所以需要有http的编解码器*/
        pipeline.addLast(new HttpServerCodec());
        /**netty内置的对写大数据流的处理器*/
        pipeline.addLast(new ChunkedWriteHandler());
        /**
         * 对httpMessage进行聚合处理，聚合成FullHttpRequest或FullHttpResponse
         * 几乎在netty的编程中，使用http请求的都会是使用到此处理器
         */
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /**===========================以上处理器用于支持http协议===============================*/

        /**
         * webSocket服务器处理的协议，用于指定给客户端连接访问的路由：/ws
         * 这个handler会处理一些繁重复杂的工作，会处理握手动作handshaking(close,ping,pong) , ping + pong = 心跳
         * 对于webSocket来讲，都是以frames(帧)进行传输的，不同的数据类型对应不同的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        /**自定义处理器*/
        pipeline.addLast(new WebSocketChatHandler());
    }

}
