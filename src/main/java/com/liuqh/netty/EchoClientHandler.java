package com.liuqh.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;

@Sharable
public class EchoClientHandler extends ChannelHandlerAdapter {
	/**
	 * 此方法会在连接到服务器后被调用
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
		System.out.println("client channelActive");
		String sendMsg="ha ha ha ";
		ctx.writeAndFlush(new MyMessage(new MyHead(sendMsg.getBytes("UTF-8").length,1),sendMsg));
		/*ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks1!", CharsetUtil.UTF_8));
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks2!", CharsetUtil.UTF_8));
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks3!", CharsetUtil.UTF_8));
		*/
	}

	/**
	 * 捕捉到异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("client channelRead--------------");
		MyMessage in = (MyMessage) msg;
		try {
			System.out.println("client channelRead: " + in);
		} finally {
			// ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放
			// or ((ByteBuf)msg).release();
			ReferenceCountUtil.release(msg);
		}
	}
}