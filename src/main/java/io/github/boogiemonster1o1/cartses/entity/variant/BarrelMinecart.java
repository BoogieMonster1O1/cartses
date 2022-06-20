package io.github.boogiemonster1o1.cartses.entity.variant;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesStorageMinecartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public class BarrelMinecart extends CartsesStorageMinecartEntity {
	private static final BlockState STATE = Blocks.BARREL.getDefaultState();

	public BarrelMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public BarrelMinecart(World world, double x, double y, double z) {
		super(CartsesEntities.BARREL_MINECART, world, x, y, z); // TODO
	}

	@Override
	protected ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
	}

	@Override
	public int size() {
		return 36;
	}

	@Override
	public BlockState getContainedBlock() {
		return STATE;
	}
}
