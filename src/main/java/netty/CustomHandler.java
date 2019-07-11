package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @param: none
 * @description: 创建自定义助手类
 *               SimpleChannelInboundHandler: 对于请求来讲，相当于入站/入境的操作
 * @author: KingJ
 * @create: 2019-07-06 16:36
 **/
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        // 获取Channel
        Channel channel = channelHandlerContext.channel();

        if (httpObject instanceof HttpRequest) {
            // 显示客户端的远程地址
            System.out.println(channel.remoteAddress());
            // 定义发送的数据消息，刷到对应缓冲区
            ByteBuf content = Unpooled.copiedBuffer("Hello Netty~", CharsetUtil.UTF_8);

            // 构建一个response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            // 设置http头部,为响应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 把相应刷到客户端
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
