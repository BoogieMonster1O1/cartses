package io.github.boogiemonster1o1.cartses.entity;

import io.github.boogiemonster1o1.cartses.entity.variant.BarrelMinecart;
import io.github.boogiemonster1o1.cartses.entity.variant.CraftingTableMinecart;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnGroup;

import static io.github.boogiemonster1o1.cartses.Cartses.id;

public class CartsesEntities {
	public static final CartsesEntityType<CraftingTableMinecart> CRAFTING_TABLE_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, CraftingTableMinecart::new, CraftingTableMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("crafting_table_minecart")).build();
	public static final CartsesEntityType<BarrelMinecart> BARREL_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, BarrelMinecart::new, BarrelMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("barrel_minecart")).build();

	public static void init() {
	}
}
