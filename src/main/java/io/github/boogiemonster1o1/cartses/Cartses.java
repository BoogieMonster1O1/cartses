package io.github.boogiemonster1o1.cartses;

import net.fabricmc.api.ModInitializer;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.util.Identifier;

public class Cartses implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("cartses");

	@Override
	public void onInitialize() {
		CartsesEntities.init();
	}

	public static Identifier id(String value) {
		return new Identifier("cartses", value);
	}
}
