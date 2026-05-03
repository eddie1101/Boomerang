package com.erg.boomerang;

import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoomerangEntity extends Projectile
{


  public BoomerangEntity(EntityType<? extends BoomerangEntity> type, Level level, LivingEntity owner, ItemStack boomerang, int itemSlot, double x, double y, double z, float power)
  {
    super(type, level);
  }

  public BoomerangEntity(EntityType<? extends BoomerangEntity> type, Level level)
  {
    super(type, level);
  }

  public int getTicksForRotation()
  {
    return tickCount;
  }
  
  @Override
  protected void defineSynchedData(Builder builder) {

  }



}
