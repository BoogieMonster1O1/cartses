package io.github.boogiemonster1o1.cartses.entity.variant;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraftingTableMinecart extends CartsesMinecartEntity {
	private static final BlockState STATE = Blocks.CRAFTING_TABLE.getDefaultState();
	private static final Text TITLE = Text.translatable("container.crafting");

	public CraftingTableMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public CraftingTableMinecart(World world, double x, double y, double z) {
		super(CartsesEntities.CRAFTING_TABLE_MINECART, world, x, y, z);
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (this.world.isClient) {
			return ActionResult.SUCCESS;
		}
		player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player1) -> new CraftingTableMinecartHandler(syncId, inventory, ScreenHandlerContext.create(world, BlockPos.ORIGIN)), TITLE));
		return ActionResult.CONSUME;
	}

	@Override
	public BlockState getContainedBlock() {
		return STATE;
	}

	public static class CraftingTableMinecartHandler extends CraftingScreenHandler {
		public CraftingTableMinecartHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
			super(syncId, playerInventory, context);
		}

		@Override
		public boolean canUse(PlayerEntity player) {
			return true;
		}
	}
}
