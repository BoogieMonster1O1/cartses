package io.github.boogiemonster1o1.cartses.client;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesEntityType;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.MinecartEntityModel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CartsesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		CartsesEntityType.ALL.forEach(type -> {
			if (type.noRenderer()) {
				return;
			}
			EntityModelLayer layer = new EntityModelLayer(type.getId(), "main");
			EntityModelLayerRegistry.registerModelLayer(layer, MinecartEntityModel::getTexturedModelData);
			EntityRendererRegistry.register(type, ctx -> new EmissiveMinecartRenderer<>(ctx, layer));
		});
		EntityModelLayer shulkerBoxLayer = new EntityModelLayer(CartsesEntities.SHULKER_BOX_MINECART.getId(), "main");
		EntityModelLayerRegistry.registerModelLayer(shulkerBoxLayer, MinecartEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(CartsesEntities.SHULKER_BOX_MINECART, ctx -> new ShulkerBoxMinecartRenderer(ctx, shulkerBoxLayer));
	}
}
