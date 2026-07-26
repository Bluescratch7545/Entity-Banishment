package com.bluescratch.entitybanishment.network;

import com.bluescratch.entitybanishment.EntityBanishment;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SetTargetEntityPacket(BlockPos pos, String targetEntity)
        implements CustomPacketPayload {

    public static final Type<SetTargetEntityPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    EntityBanishment.MOD_ID,
                    "set_target_entity"
            ));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetTargetEntityPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    SetTargetEntityPacket::pos,
                    ByteBufCodecs.STRING_UTF8,
                    SetTargetEntityPacket::targetEntity,
                    SetTargetEntityPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}