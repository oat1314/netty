package com.learn.netty.server.service.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @Description: 自定义助手类（处理器）,SimpleChannelInboundHandler:对于请求来讲相当于“入境”
 * @Author ga.zhang
 * @Date 2019/12/16 15:07
 * @Version V1.0
 **/
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        /**获取channel*/
        Channel channel = channelHandlerContext.channel();
        /**客户端远程地址*/
        if (httpObject instanceof HttpRequest) {
            System.out.println(channel.remoteAddress());
            /**定义发送的数据消息*/
            ByteBuf buffer = Unpooled.copiedBuffer("Hello netty", CharsetUtil.UTF_8);
            /**构建一个http响应*/
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
            /**设置相应类型和长度*/
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain").set(HttpHeaderNames.CONTENT_LENGTH, buffer.readableBytes());
            /**将数据写到缓冲区并刷到客户端*/
            channelHandlerContext.writeAndFlush(response);
        }
    }

    /**
     * channel注册
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel注册");
        super.channelRegistered(ctx);
    }

    /**
     * channel移除
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel移除");
        super.channelUnregistered(ctx);
    }

    /**
     * channel活跃
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel活跃");
        super.channelActive(ctx);
    }

    /**
     * channel不活跃 客户端和服务端断开连接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel不活跃 客户端和服务端断开连接");
        super.channelInactive(ctx);
    }

    /**
     * channel读取完毕
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel读取完毕");
        super.channelReadComplete(ctx);
    }

    /**
     * 用户数据触发
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户数据触发");
        super.userEventTriggered(ctx, evt);
    }

    /**
     * channel可写更改
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel可写更改");
        super.channelWritabilityChanged(ctx);
    }

    /**
     * 异常捕获
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常捕获");
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 添加助手类
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("添加助手类");
        super.handlerAdded(ctx);
    }

    /**
     * 移除助手类
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("移除助手类");
        super.handlerRemoved(ctx);
    }
}
