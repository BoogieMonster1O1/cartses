package io.github.boogiemonster1o1.cartses.item;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntityType;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class CartsesMinecartItem extends Item {
	private static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior(){
		private final ItemDispenserBehavior defaultBehavior = new ItemDispenserBehavior();

		@Override
		public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
			double g;
			Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
			ServerWorld world = pointer.getWorld();
			double d = pointer.getX() + (double)direction.getOffsetX() * 1.125;
			double e = Math.floor(pointer.getY()) + (double)direction.getOffsetY();
			double f = pointer.getZ() + (double)direction.getOffsetZ() * 1.125;
			BlockPos blockPos = pointer.getPos().offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			RailShape railShape = blockState.getBlock() instanceof AbstractRailBlock ? blockState.get(((AbstractRailBlock) blockState.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
			if (blockState.isIn(BlockTags.RAILS)) {
				g = railShape.isAscending() ? 0.6 : 0.1;
			} else if (blockState.isAir() && world.getBlockState(blockPos.down()).isIn(BlockTags.RAILS)) {
				BlockState blockState2 = world.getBlockState(blockPos.down());
				RailShape railShape22 = blockState2.getBlock() instanceof AbstractRailBlock ? blockState2.get(((AbstractRailBlock) blockState2.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
				g = direction == Direction.DOWN || !railShape22.isAscending() ? -0.9 : -0.4;
			} else {
				return this.defaultBehavior.dispense(pointer, stack);
			}
			Entity entity = ((CartsesMinecartItem) stack.getItem()).type.getWorldSpawnFactory().create(world, d, e +g , f);
			if (stack.hasCustomName()) {
				entity.setCustomName(stack.getName());
			}
			world.spawnEntity(entity);
			stack.decrement(1);
			return stack;
		}

		@Override
		protected void playSound(BlockPointer pointer) {
			pointer.getWorld().syncWorldEvent(WorldEvents.DISPENSER_DISPENSES, pointer.getPos(), 0);
		}
	};
	private final CartsesEntityType<? extends Entity> type;

	public CartsesMinecartItem(CartsesEntityType<? extends Entity> type, Settings settings) {
		super(settings);
		this.type = type;
		DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
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
			CartsesMinecartEntity entity = this.type.getWorldSpawnFactory().create(world, blockPos.getX() + 0.5, blockPos.getY() + 0.0625 + (shape.isAscending() ? 0.5 : 0.0), blockPos.getZ() + 0.5);
			if (stack.hasCustomName()) {
				entity.setCustomName(stack.getName());
			}
			this.modifyEntity(entity, stack);
			world.spawnEntity(entity);
			world.emitGameEvent(GameEvent.ENTITY_PLACE, blockPos, GameEvent.Emitter.of(context.getPlayer(), world.getBlockState(blockPos.down())));
		}
		stack.decrement(1);
		return ActionResult.success(world.isClient);
	}

	public void modifyEntity(CartsesMinecartEntity entity, ItemStack stack) {
	}

	@Override
	public String getTranslationKey() {
		return this.type.getTranslationKey();
	}

	@Override
	public Text getName() {
		return super.getName();
	}
}
