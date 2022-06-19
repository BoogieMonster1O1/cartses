package io.github.boogiemonster1o1.cartses.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CartsesMinecartEntity extends MinecartEntity {
	public CartsesMinecartEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public CartsesMinecartEntity(EntityType<?> entityType, World world, double x, double y, double z) {
		this(entityType, world);
		this.setPosition(x, y, z);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
	}

	@Override
	protected Item getItem() {
		return ((CartsesEntityType<?>) this.getType()).getItem();
	}

	@Override
	public void onActivatorRail(int x, int y, int z, boolean powered) {
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		return ActionResult.PASS;
	}
}
