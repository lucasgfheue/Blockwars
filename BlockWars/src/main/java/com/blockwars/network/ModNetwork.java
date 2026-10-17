package com.blockwars.network;

import com.blockwars.entity.BaseMechaEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetwork {
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("2");

        registrar.playToServer(MechaActionPayload.TYPE, MechaActionPayload.STREAM_CODEC, (payload, context) -> {
            Player player = context.player();
            if (!(player.getVehicle() instanceof BaseMechaEntity mecha) || mecha.getControllingPassenger() != player) {
                return;
            }
            switch (payload.action()) {
                case MechaActionPayload.PRIMARY -> mecha.tryPrimary(player);
                case MechaActionPayload.SECONDARY -> mecha.trySecondary(player);
                case MechaActionPayload.BOOST_ON -> mecha.setBoosting(mecha.canFly());
                case MechaActionPayload.BOOST_OFF -> mecha.setBoosting(false);
                default -> { }
            }
        });
    }
}
