package io.github.boogiemonster1o1.cartses.entity;

import io.github.boogiemonster1o1.cartses.entity.variant.BarrelMinecart;
import io.github.boogiemonster1o1.cartses.entity.variant.CraftingTableMinecart;
import io.github.boogiemonster1o1.cartses.entity.variant.EnderChestMinecart;
import io.github.boogiemonster1o1.cartses.entity.variant.GlowstoneMinecart;
import io.github.boogiemonster1o1.cartses.entity.variant.RedstoneLampMinecart;
import io.github.boogiemonster1o1.cartses.entity.variant.ShulkerBoxMinecart;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnGroup;

import static io.github.boogiemonster1o1.cartses.Cartses.id;

public class CartsesEntities {
	public static final CartsesEntityType<CraftingTableMinecart> CRAFTING_TABLE_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, CraftingTableMinecart::new, CraftingTableMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("crafting_table_minecart")).build();
	public static final CartsesEntityType<BarrelMinecart> BARREL_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, BarrelMinecart::new, BarrelMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("barrel_minecart")).build();
	public static final CartsesEntityType<GlowstoneMinecart> GLOWSTONE_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, GlowstoneMinecart::new, GlowstoneMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("glowstone_minecart")).blockLightFunction(cart -> 15).skyLightFunction(cart -> 15).build();
	public static final CartsesEntityType<RedstoneLampMinecart> REDSTONE_LAMP_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, RedstoneLampMinecart::new, RedstoneLampMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("redstone_lamp_minecart")).blockLightFunction(cart -> cart.isLit() ? 15 : -1).skyLightFunction(cart -> cart.isLit() ? 15 : -1).build();
	public static final CartsesEntityType<EnderChestMinecart> ENDER_CHEST_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, EnderChestMinecart::new, EnderChestMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("ender_chest_minecart")).build();
	public static final CartsesEntityType<ShulkerBoxMinecart> SHULKER_BOX_MINECART = CartsesEntityType.Builder.create(SpawnGroup.MISC, ShulkerBoxMinecart::new, ShulkerBoxMinecart::new).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).id(id("shuler_box_minecart")).build();

	public static void init() {
	}
}
