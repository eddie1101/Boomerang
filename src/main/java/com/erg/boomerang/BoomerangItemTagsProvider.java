package com.erg.boomerang;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static com.erg.boomerang.Boomerang.MODID;

public class BoomerangItemTagsProvider extends ItemTagsProvider {

    public BoomerangItemTagsProvider(PackOutput output,
                                     CompletableFuture<HolderLookup.Provider> lookupProvider,
                                     CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
                                     ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(Boomerang.BOOMERANG_ITEM.get());
        tag(ItemTags.WEAPON_ENCHANTABLE).add(Boomerang.BOOMERANG_ITEM.get());
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(Boomerang.BOOMERANG_ITEM.get());
        tag(ItemTags.CROSSBOW_ENCHANTABLE).add(Boomerang.BOOMERANG_ITEM.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(Boomerang.BOOMERANG_ITEM.get());
    }
}