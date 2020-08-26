package com.github.parkhuiwo0.learningreactvie.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group) // Client Event를 처리할 EventLoopGroup (NIO) 지정
                    .channel(NioSocketChannel.class) // 채널 유형으로 NIO 전송 유형 중 하나를 지정
                    .remoteAddress(new InetSocketAddress(host, port)) // 서버의 주소와 포트
                    .handler(new ChannelInitializer<SocketChannel>() { // 채널이 생성될 때 파이프라인에 EchoClientHandler 하나를 추가
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync(); // 원격 피어로 연결하고 연결이 완료되기를 기다림
            f.channel().closeFuture().sync(); // 채널이 닫힐 때까지 블로킹함
        } finally {
            group.shutdownGracefully().sync(); // 스레드 풀을 종료하고 모든 리소스를 해제함.
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println(
                    "Usage: " + EchoClient.class.getSimpleName() +
                            "<host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }
}
