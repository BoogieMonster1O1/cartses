package io.github.boogiemonster1o1.cartses.client;

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
			EntityModelLayer layer = new EntityModelLayer(type.getId(), "main");
			EntityModelLayerRegistry.registerModelLayer(layer, MinecartEntityModel::getTexturedModelData);
			if (type.noRenderer()) {
				return;
			}
			EntityRendererRegistry.register(type, ctx -> new EmissiveMinecartRenderer<>(ctx, layer));
		});
	}
}
