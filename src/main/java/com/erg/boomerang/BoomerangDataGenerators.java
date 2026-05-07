package com.erg.boomerang;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static com.erg.boomerang.Boomerang.MODID;

@EventBusSubscriber(modid = MODID)
public class BoomerangDataGenerators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        var blockTags = generator.addProvider(
                event.includeServer(),
                new BoomerangBlockTagsProvider(output, lookupProvider, existingFileHelper)
        );

        generator.addProvider(
                event.includeServer(),
                new BoomerangItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper)
        );
    }
}
