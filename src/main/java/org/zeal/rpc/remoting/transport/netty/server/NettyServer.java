package org.zeal.rpc.remoting.transport.netty.server;

import java.io.IOException;

import org.zeal.rpc.remoting.transport.Server;
import org.zeal.rpc.remoting.transport.netty.codec.NettyMessageDecoder;
import org.zeal.rpc.remoting.transport.netty.codec.NettyMessageEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;




public class NettyServer implements Server{
	
	public NettyServer(){
			
	}
	
	public void bind(String host,int port){
		// 配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 100)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
				    @Override
				    public void initChannel(SocketChannel ch)
					    throws IOException {
					ch.pipeline().addLast(
						new NettyMessageDecoder(1024 * 1024, 4, 4));
					ch.pipeline().addLast(new NettyMessageEncoder());
//					ch.pipeline().addLast("readTimeoutHandler",
//						new ReadTimeoutHandler(50));
//					ch.pipeline().addLast(new LoginAuthRespHandler());
//					ch.pipeline().addLast("HeartBeatHandler",
//						new HeartBeatRespHandler());
					ch.pipeline().addLast("NettyServerHandler",new  NettyServerHandler());
				    }
				});
	
			// 绑定端口，同步等待成功
			b.bind(host, port).sync();
		}catch(InterruptedException e){
			
		}finally{
			 // 优雅退出，释放线程池资源
		    bossGroup.shutdownGracefully();
		    workerGroup.shutdownGracefully();
		}
	}
	
}
