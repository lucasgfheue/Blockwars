package com.blockwars.network;

import com.blockwars.BlockWars;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** Client -> server: the pilot pressed a mecha control. */
public record MechaActionPayload(byte action) implements CustomPacketPayload {
    public static final byte PRIMARY = 0;
    public static final byte SECONDARY = 1;
    public static final byte BOOST_ON = 2;
    public static final byte BOOST_OFF = 3;

    public static final Type<MechaActionPayload> TYPE = new Type<>(BlockWars.id("mecha_action"));
    public static final StreamCodec<ByteBuf, MechaActionPayload> STREAM_CODEC =
            ByteBufCodecs.BYTE.map(MechaActionPayload::new, MechaActionPayload::action);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
