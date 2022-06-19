package io.github.boogiemonster1o1.cartses.item;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntityType;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;
import io.github.boogiemonster1o1.cartses.mixin.MinecartItemAccessor;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CartsesMinecartItem extends Item {
	private final CartsesEntityType<? extends Entity> type;

	public CartsesMinecartItem(CartsesEntityType<? extends Entity> type, Settings settings) {
		super(settings);
		this.type = type;
		DispenserBlock.registerBehavior(this, MinecartItemAccessor.getDispenserBehavior());
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		BlockPos blockPos = context.getBlockPos();
		World world = context.getWorld();
		BlockState state = world.getBlockState(blockPos);
		if (!state.isIn(BlockTags.RAILS)) {
			return ActionResult.FAIL;
		}
		ItemStack stack = context.getStack();
		if (!world.isClient) {
			RailShape shape = state.getBlock() instanceof AbstractRailBlock ? state.get(((AbstractRailBlock) state.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
			CartsesMinecartEntity entity = (CartsesMinecartEntity) this.type.getWorldSpawnFactory().create(world, blockPos.getX() + 0.5, blockPos.getY() + 0.0625 + (shape.isAscending() ? 0.5 : 0.0), blockPos.getZ() + 0.5);
			if (stack.hasCustomName()) {
				entity.setCustomName(stack.getName());
			}
			world.spawnEntity(entity);
			world.emitGameEvent(GameEvent.ENTITY_PLACE, blockPos, GameEvent.Emitter.of(context.getPlayer(), world.getBlockState(blockPos.down())));
		}
		stack.decrement(1);
		return ActionResult.success(world.isClient);
	}
}
