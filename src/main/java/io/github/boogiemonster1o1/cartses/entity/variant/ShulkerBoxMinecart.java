package io.github.boogiemonster1o1.cartses.entity.variant;

import io.github.boogiemonster1o1.cartses.entity.CartsesEntities;
import io.github.boogiemonster1o1.cartses.entity.CartsesStorageMinecartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
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
	public int size() {
		return 27;
	}

	public int getDyeColor() {
		return this.dataTracker.get(DYE_COLOR);
	}

	public void setDyeColor(int color) {
		this.dataTracker.set(DYE_COLOR, color);
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (player.isSneaking()) {
			if (world.isClient) {
				return ActionResult.SUCCESS;
			}
			Item item = player.getStackInHand(hand).getItem();
			if (item instanceof DyeItem && ((DyeItem) item).getColor().getId() != this.getDyeColor()) {
				this.setDyeColor(((DyeItem) item).getColor().getId());
				if (!player.isCreative()) {
					player.getStackInHand(hand).decrement(1);
				}
				this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_SLIME_SQUISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			}
			return ActionResult.CONSUME;
		}
		return super.interact(player, hand);
	}

	@Override
	protected ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return STATE_TEMP;
	}

	@Override
	public void dropItems(DamageSource damageSource) {
		this.kill();
		if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			ItemStack itemStack = new ItemStack(this.getItem());
			if (this.hasCustomName()) {
				itemStack.setCustomName(this.getCustomName());
			}
			NbtCompound inv = new NbtCompound();
			this.writeInventoryToNbt(inv);
			itemStack.getOrCreateNbt().put("Inventory", inv);
			itemStack.getOrCreateNbt().putInt("Color", this.getDyeColor());
			this.dropStack(itemStack);
		}
	}

	@Override
	public void onBroken(DamageSource source, World world, Entity vehicle) {
	}
}
