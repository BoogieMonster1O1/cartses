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

public class GlowstoneMinecart extends CartsesMinecartEntity {
	private static final BlockState STATE = Blocks.GLOWSTONE.getDefaultState();

	public GlowstoneMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public GlowstoneMinecart(World world, double x, double y, double z) {
		super(CartsesEntities.GLOWSTONE_MINECART, world, x, y, z);
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return STATE;
	}
}
