package com.learn.netty.server.service.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Description: 处理消息的handler，TextWebSocketFrame：是netty为webSocket专门处理文本的对象，frame是消息的载体
 * @Author ga.zhang
 * @Date 2019/12/17 9:38
 * @Version V1.0
 **/
public class WebSocketChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的channel
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        /**拿到从客户端发送过来的消息*/
        String text = msg.text();
        System.out.println("接收到的数据：" + text);
        /**这里的消息不能是别的类型，只能是TextWebSocketFrame，因为SimpleChannelInboundHandler需要的是TextWebSocketFrame的内容*/
        clients.writeAndFlush(new TextWebSocketFrame("服务端在:" + LocalDateTime.now() + "收到消息,收到的消息为:" + text));
    }

    /**
     * 客户端连接时调用,当客户端连接到服务端之后，获取到客户端的channel，并放到ChannelGroup中去管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        System.out.println(ctx.channel().id().asLongText() + "建立连接-----长");
        System.out.println(ctx.channel().id().asShortText() + "建立连接-----短");
        super.handlerAdded(ctx);
    }

    /**
     * 客户端离开时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        /**当客户端离开时，会执行handlerRemoved，同时会自动的将ChannelGroup中客户端对应的channel移除，所以下面移除的代码可以不用写*/
        /**clients.remove(ctx.channel());*/
        /**打印长id和短id：长id是唯一的不重复的，短id在系统比较庞大时可能会重复*/
        System.out.println(ctx.channel().id().asLongText() + "已经离开-----长");
        System.out.println(ctx.channel().id().asShortText() + "已经离开-----短");
        super.handlerRemoved(ctx);
    }
}
