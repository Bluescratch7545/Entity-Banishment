package com.bluescratch.entitybanishment.network;

import com.bluescratch.entitybanishment.EntityBanishment;
import com.bluescratch.entitybanishment.block.entity.BanishmentBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = EntityBanishment.MOD_ID)
public class ModNetworking {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                SetTargetEntityPacket.TYPE,
                SetTargetEntityPacket.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        var player = context.player();

                        System.out.println("PACKET RECEIVED: " + payload.targetEntity());

                        // temporary: use the block the player is looking at
                        var hit = player.pick(5, 0, false);

                        if (hit.getType() == HitResult.Type.BLOCK) {
                            BlockPos pos = ((BlockHitResult) hit).getBlockPos();

                            var blockEntity = player.level().getBlockEntity(pos);

                            if (blockEntity instanceof BanishmentBlockEntity banishment) {
                                banishment.setTargetEntity(payload.targetEntity());
                            }
                        }
                    });
                }
        );
    }
}