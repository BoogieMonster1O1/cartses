package io.github.boogiemonster1o1.cartses.item;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntityType;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;
import io.github.boogiemonster1o1.cartses.entity.variant.ShulkerBoxMinecart;

import net.minecraft.item.ItemStack;

public class ShulkerBoxMinecartItem extends CartsesMinecartItem {

	public ShulkerBoxMinecartItem(CartsesEntityType<?> type, Settings settings) {
		super(type, settings);
	}

	@Override
	public void modifyEntity(CartsesMinecartEntity entity, ItemStack stack) {
		if (stack.getOrCreateNbt().contains("Inventory")) {
			((ShulkerBoxMinecart) entity).readInventoryFromNbt(stack.getOrCreateNbt().getCompound("Inventory"));
		}
	}
}
