package io.github.boogiemonster1o1.cartses.entity.variant;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RedstoneLampMinecart extends CartsesMinecartEntity {
	private static final TrackedData<Boolean> POWERED = DataTracker.registerData(CartsesMinecartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final BlockState STATE = Blocks.REDSTONE_LAMP.getDefaultState();
	private static final BlockState STATE_POWERED = Blocks.REDSTONE_LAMP.getDefaultState().with(RedstoneLampBlock.LIT, Boolean.TRUE);

	public RedstoneLampMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public RedstoneLampMinecart(World world, double x, double y, double z) {
		super(CartsesEntities.REDSTONE_LAMP_MINECART, world, x, y, z);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(POWERED, Boolean.FALSE);
	}

	@Override
	public void tick() {
		super.tick();
		int x = MathHelper.floor(this.getX());
		int y = MathHelper.floor(this.getY());
		int z = MathHelper.floor(this.getZ());
		if (this.world.getBlockState(new BlockPos(x, y - 1, z)).isIn(BlockTags.RAILS)) {
			y -= 1;
		}
		BlockPos pos = new BlockPos(x, y, z);
		BlockState state = this.world.getBlockState(pos);
		this.setLit(state.isOf(Blocks.ACTIVATOR_RAIL) && state.get(PoweredRailBlock.POWERED));
	}

	public Boolean isLit() {
		return this.dataTracker.get(POWERED);
	}

	public void setLit(boolean value) {
		this.dataTracker.set(POWERED, value);
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		if (isLit()) {
			return STATE_POWERED;
		}
		return STATE;
	}
}
