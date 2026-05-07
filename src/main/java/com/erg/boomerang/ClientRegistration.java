package com.erg.boomerang;

import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.erg.boomerang.Boomerang.BOOMERANG_ENTITY;
import static com.erg.boomerang.Boomerang.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientRegistration {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType<BoomerangEntity>) BOOMERANG_ENTITY.get(), BoomerangRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BoomerangModel.BOOMERANG_LAYER, BoomerangModel::createLayer);
    }

}
