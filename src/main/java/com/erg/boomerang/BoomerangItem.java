package com.erg.boomerang;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BoomerangItem extends Item {

    public BoomerangItem(Item.Properties props) {
        super(props.durability(350));
    }

     @Override
     public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
         return UseAnim.BOW;
     }

     @Override
     public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
         return 72000;
     }

     @Override
     public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int ticksRemaining) {
         float duration = (float) this.getUseDuration(stack, entity) - ticksRemaining;
         float power = duration / 20.0f;
         power = (power * power + power * 2.0f) / 3.0f;
         if (power > 1.0f) {
             power = 1.0f;
         } else if (power < 0.2f) {
             return;
         }

         int slot = entity instanceof Player ? ((Player) entity).getInventory().selected : -1;
         BoomerangEntity boomerang = new BoomerangEntity(
                 (EntityType<? extends BoomerangEntity>) Boomerang.BOOMERANG_ENTITY.get(),
                 level,
                 entity,
                 stack,
                 slot,
                 power
         );
         if (entity instanceof Player player) {
             player.getInventory().removeItem(stack);
         }

         boomerang.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0f, power, 0.5f + power / 2.0f);
         level.addFreshEntity(boomerang);
         level.playSound(null, entity, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 0.5f + power, 1.0f + power / 2.0f);
     }

     @Override
     public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
         player.startUsingItem(hand);
         return InteractionResultHolder.consume(player.getItemInHand(hand));
     }

     @Override
     public int getEnchantmentValue() {
         return 15;
     }

}

