package com.liuqh.netty;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
/** 
 * Sharable表示此对象在channel间共享 
 * handler类是我们的具体业务类 
 * */  
@Sharable//注解@Sharable可以让它在channels间共享  
public class EchoServerHandler extends  ChannelHandlerAdapter{  
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {   
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) //flush掉所有写回的数据  
        .addListener(ChannelFutureListener.CLOSE); //当flush完成后关闭channel  
        System.out.println("server channelReadComplete--------------");
    } 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {   
        cause.printStackTrace();//捕捉异常信息  
        ctx.close();//出现异常时关闭channel   
    }
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf=(ByteBuf) msg;
		ByteBuf read=buf.readBytes(buf.readableBytes());
		System.out.println("server channelRead: " +new String(read.array(),"UTF-8"));
		ctx.writeAndFlush(Unpooled.copiedBuffer("hello,client", CharsetUtil.UTF_8));
	}
}  