package io.github.boogiemonster1o1.cartses.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;

@Mixin(BlockRenderManager.class)
public interface BlockRenderManagerAccessor {
	@Accessor
	BuiltinModelItemRenderer getBuiltinModelItemRenderer();
}
