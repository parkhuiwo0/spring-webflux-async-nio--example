package com.github.parkhuiwo0.learningreactvie.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import static io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 채널 활성화 알림을 받으면 메시지 전송
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /* 데이터를 수신할 때마다 호출
       서버가 전송한 메시지가 여러 청크로 수신될 수 있다는 점. (서버가 5byte를 전송할 때 5바이트가 모두 한 번에 수신된단 보장이 없다.)
       다만, TCP는 Stream 기반 프로토콜이므로 서버가 보낸 순서대로 바이트를 수신할 수 있게 보장한다.
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
