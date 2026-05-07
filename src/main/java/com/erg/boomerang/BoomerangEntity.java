package com.erg.boomerang;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BoomerangEntity extends Projectile {

    public static final int MAX_DAMAGE_DIFFERENTIAL = 5;
    protected static final double P = 0.0072d, I = 0.0012d, D = 0.0650d;
    protected final ItemStack boomerangItemStack;
    protected Vec3 positionErrorIntegral = new Vec3(0.0d, 0.0d, 0.0d);

    protected boolean moving = true;
    protected int flyingSoundPlayed = 0;
    protected int tickStamp = 0;
    protected int tickTimeout = 0;
    protected float launchRadius = 0;
    protected int entitiesPierced = 0;

    protected int minDamage;
    protected int itemSlot;
    BoomerangState lastState;
    BoomerangState currentState;
    BoomerangState nextState;
    public BoomerangEntity(EntityType<? extends BoomerangEntity> type, Level level, LivingEntity owner, ItemStack boomerang, int itemSlot, double x, double y, double z, float power) {
        super(type, level);
        this.setOwner(owner);
        this.setPos(x, y, z);

        this.itemSlot = itemSlot;
        if (boomerang == null) {
            boomerangItemStack = new ItemStack(Boomerang.BOOMERANG_ITEM.get());
        } else {
            boomerangItemStack = boomerang;
        }

        this.launchRadius = Math.max(Config.BOOMERANG_RANGE.get() / 2f, Config.BOOMERANG_RANGE.get() * power);

        Holder<Enchantment> sharpness = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.SHARPNESS);
        int sharpnessLevels = boomerangItemStack.getEnchantmentLevel(sharpness);
        this.minDamage = 1 + sharpnessLevels;

        lastState = BoomerangState.TIMEOUT;
        currentState = BoomerangState.ATTACKING;
        nextState = BoomerangState.ATTACKING;

    }

    public BoomerangEntity(EntityType<? extends BoomerangEntity> type, Level level, LivingEntity owner, ItemStack boomerang, int itemSlot, float power) {
        this(type, level, owner, boomerang, itemSlot, owner.getX(), owner.getEyeY() - 0.5f, owner.getZ(), power);
    }

    public BoomerangEntity(EntityType<? extends BoomerangEntity> type, Level level, double x, double y, double z) {
        this(type, level, null, null, -1, x, y, z, 1.0f);
    }

    public BoomerangEntity(EntityType<? extends BoomerangEntity> entityType, Level level) {
        this(entityType, level, 0.0d, 0.0d, 0.0d);
    }

    public int getTicksForRotation() {
        return tickCount;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    protected enum BoomerangState {
        ATTACKING,
        RETURNING,
        RETURNED,
        TIMEOUT
    }


}
