package io.github.boogiemonster1o1.cartses.mixin;

import com.google.common.collect.ImmutableSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;

@Mixin(EntityType.class)
public interface EntityTypeAccessor {
	@Accessor
	ImmutableSet<Block> getCanSpawnInside();
}
