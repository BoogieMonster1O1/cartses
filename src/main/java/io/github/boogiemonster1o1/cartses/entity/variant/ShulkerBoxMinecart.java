package io.github.boogiemonster1o1.cartses.entity.variant;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesStorageMinecartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

public class ShulkerBoxMinecart extends CartsesStorageMinecartEntity {
	private static final BlockState STATE_TEMP = Blocks.SHULKER_BOX.getDefaultState();
	private static final TrackedData<Integer> DYE_COLOR = DataTracker.registerData(ShulkerBoxMinecart.class, TrackedDataHandlerRegistry.INTEGER);
	public ShulkerBoxMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public ShulkerBoxMinecart(World world, double x, double y, double z) {
		super(CartsesEntities.SHULKER_BOX_MINECART, world, x, y, z);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(DYE_COLOR, DyeColor.PURPLE.getId());
	}

	@Override
	protected ScreenHandler getScreenHandler(int var1, PlayerInventory var2) {
		return null;
	}

	@Override
	public int size() {
		return 27;
	}

	public int getDyeColor() {
		return this.dataTracker.get(DYE_COLOR);
	}

	public void setDyeColor(int color) {
		this.dataTracker.set(DYE_COLOR, color);
	}



	public ItemStack getShulkerBoxAsItem() {
		return null; // TODO
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return STATE_TEMP;
	}

	@Override
	public void dropItems(DamageSource damageSource) {
		super.dropItems(damageSource);
	}
}
