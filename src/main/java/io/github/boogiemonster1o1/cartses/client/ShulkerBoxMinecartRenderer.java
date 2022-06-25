package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.variant.ShulkerBoxMinecart;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;

public class ShulkerBoxMinecartRenderer extends EmissiveMinecartRenderer<ShulkerBoxMinecart> {
	public ShulkerBoxMinecartRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer) {
		super(ctx, layer);
	}

	@Override
	protected void renderBlock(ShulkerBoxMinecart entity, float delta, BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		int color = entity.getDyeColor();
		if (color == -1) {
			super.renderBlock(entity, delta, state, matrices, vertexConsumers, light);
		} else {
			DyeColor dye = DyeColor.byId(color);
			// TODO
			super.renderBlock(entity, delta, state, matrices, vertexConsumers, light);
		}
	}
}
