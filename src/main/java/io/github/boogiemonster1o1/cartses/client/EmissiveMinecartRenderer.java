package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntityType;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.math.BlockPos;

import net.fabricmc.fabric.impl.object.builder.FabricEntityType;

public class EmissiveMinecartRenderer<T extends CartsesMinecartEntity> extends MinecartEntityRenderer<T> {
	public EmissiveMinecartRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer) {
		super(ctx, layer);
	}

	@Override
	protected int getSkyLight(T entity, BlockPos pos) {
		int light = ((CartsesEntityType<T>) entity.getType()).getSkyLightFunction().applyAsInt(entity);
		if (light < 0) {
			return super.getSkyLight(entity, pos);
		}
		return light;
	}

	@Override
	protected int getBlockLight(T entity, BlockPos pos) {
		int light = ((CartsesEntityType<T>) entity.getType()).getBlockLightFunction().applyAsInt(entity);
		if (light < 0) {
			return super.getBlockLight(entity, pos);
		}
		return light;
	}
}
