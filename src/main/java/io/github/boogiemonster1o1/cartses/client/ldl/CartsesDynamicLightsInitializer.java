package io.github.boogiemonster1o1.cartses.client.ldl;

import dev.lambdaurora.lambdynlights.api.DynamicLightHandlers;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;

public class CartsesDynamicLightsInitializer implements DynamicLightsInitializer {
	@Override
	public void onInitializeDynamicLights() {
		DynamicLightHandlers.registerDynamicLightHandler(CartsesEntities.GLOWSTONE_MINECART, cart -> 15);
		DynamicLightHandlers.registerDynamicLightHandler(CartsesEntities.REDSTONE_LAMP_MINECART, cart -> cart.isLit() ? 15 : 0);
	}
}
