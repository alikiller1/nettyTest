package com.liuqh.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	/**
	 * 此方法会在连接到服务器后被调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("client channelActive");
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	}

	/**
	 * 捕捉到异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	/**
	 * 接收到服务端的数据调用
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("Client messageReceived: " +new String( msg.readBytes(msg.readableBytes()).array(),"UTF-8"));
	}

}