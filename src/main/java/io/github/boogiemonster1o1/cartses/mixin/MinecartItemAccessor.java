package io.github.boogiemonster1o1.cartses.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.MinecartItem;

@Mixin(MinecartItem.class)
public interface MinecartItemAccessor {
	@Accessor("DISPENSER_BEHAVIOR")
	static DispenserBehavior getDispenserBehavior() {
		throw new UnsupportedOperationException();
	}
}
