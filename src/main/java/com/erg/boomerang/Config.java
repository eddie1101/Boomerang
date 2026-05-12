package com.erg.boomerang;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue BOOMERANG_RANGE = BUILDER
            .comment("The range in blocks that the boomerang travels before homing back to the user.")
            .defineInRange("boomerangRange", 16, 0, 1024);

    public static final ModConfigSpec.IntValue BOOMERANG_OWNER_TIMEOUT = BUILDER
            .comment("The number of ticks after which a timed-out boomerang will drop itself as an item at the owner's last-known position.")
            .defineInRange("boomerangOwnerTimeout", 10, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue BOOMERANG_LIFESPAN = BUILDER
            .comment("The number of ticks a boomerang will home for, after which it will return to its owner's inventory automatically.")
            .defineInRange("boomerangLifespan", 300, 0, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
