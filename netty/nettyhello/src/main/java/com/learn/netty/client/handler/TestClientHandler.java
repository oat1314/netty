package com.learn.netty.client.handler;

import com.iverson.example9.protobuf.MyDataInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("张三").setAge(20).setAddress("北京").build();
        ctx.channel().writeAndFlush(person);*/
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;
        if (0==randomInt) {
             myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("张三").setAge(20)
                    .setAddress("北京")).build();
        } else if (1 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("一只狗").setAge(20)).build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("一只狗").setCity("上海")).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
