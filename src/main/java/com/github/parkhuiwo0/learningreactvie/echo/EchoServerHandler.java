package com.github.parkhuiwo0.learningreactvie.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import static io.netty.channel.ChannelHandler.Sharable;

/**
 * 단순히 데이터를 원격 피어로 반향 출력하는 것.ㅎ
 */

@Sharable // ChannelHandler를 여러 채널 간에 안전하게 공유할 수 있음을 나타냄.
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in); // OutBound 메시지를 Flush하지 않은 채로 받은 메시지를 발신자로 출력함
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // 대기 중인 메시지를 원격 피어로 플러시하고 채널을 닫음
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); // 예외를 출력하고,
        ctx.close(); // 채널을 닫음
    }
}
