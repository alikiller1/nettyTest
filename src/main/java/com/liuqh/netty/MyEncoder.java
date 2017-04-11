package com.liuqh.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 17-1-9.
 * 编码器 将自定义消息转化成ByteBuff
 */
public class MyEncoder extends MessageToByteEncoder<MyMessage> {
 
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyMessage myMessage, ByteBuf byteBuf) throws Exception {
 
        int length = myMessage.getHead().getLength();
        int version = myMessage.getHead().getVersion();
        String content = myMessage.getContent();
 
        byteBuf.writeInt(length);
        byteBuf.writeInt(version);
        byteBuf.writeBytes(content.getBytes(Charset.forName("UTF-8")));
 
    }
}