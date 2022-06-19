package io.github.boogiemonster1o1.cartses.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

@Mixin(CraftingScreenHandler.class)
public interface CraftingScreenHandlerAccessor {
	@Accessor
	ScreenHandlerContext getContext();
}
