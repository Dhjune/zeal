package org.zeal.rpc.remoting.transport.netty.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class NettyClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		super.channelRead(ctx, msg);
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		// TODO Auto-generated method stub
		super.close(ctx, promise);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.write(ctx, msg, promise);
	}
	
}
