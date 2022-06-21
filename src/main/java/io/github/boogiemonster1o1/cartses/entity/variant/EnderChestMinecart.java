package io.github.boogiemonster1o1.cartses.entity.variant;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesMinecartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EnderChestMinecart extends CartsesMinecartEntity {
	private static final BlockState STATE = Blocks.ENDER_CHEST.getDefaultState();
	private static final Text TITLE = Text.translatable("container.enderchest");

	public EnderChestMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public EnderChestMinecart(World world, double x, double y, double z) {
		super(CartsesEntities.ENDER_CHEST_MINECART, world, x, y, z);
	}

	@Override
	public BlockState getContainedBlock() {
		return STATE;
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (this.world.isClient) {
			return ActionResult.SUCCESS;
		}
		player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player2) -> GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, player2.getEnderChestInventory()), TITLE));
		player.incrementStat(Stats.OPEN_ENDERCHEST);
		PiglinBrain.onGuardedBlockInteracted(player, true);
		return ActionResult.CONSUME;
	}
}
