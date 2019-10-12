package chatroom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import chatroom.protocol.PacketCodeC;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 22:48
 **/
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FILED_OFFSET = 7;
    private static final int LENGTH_FILED_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FILED_OFFSET, LENGTH_FILED_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 校验请求包是否符合规定的自定义协议
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
