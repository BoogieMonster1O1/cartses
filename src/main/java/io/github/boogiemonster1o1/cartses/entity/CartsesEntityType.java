package io.github.boogiemonster1o1.cartses.entity;

import com.google.common.collect.ImmutableSet;
import io.github.boogiemonster1o1.cartses.item.CartsesMinecartItem;
import io.github.boogiemonster1o1.cartses.mixin.EntityTypeAccessor;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;

public class CartsesEntityType<T extends CartsesMinecartEntity> extends FabricEntityType<T> {
	private final Item item;
	private final WorldSpawnFactory<T> worldSpawnFactory;

	public CartsesEntityType(EntityFactory<T> factory, SpawnGroup spawnGroup, boolean bl, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> spawnBlocks, EntityDimensions entityDimensions, int maxTrackDistance, int trackTickInterval, Boolean alwaysUpdateVelocity, Identifier id, WorldSpawnFactory<T> worldSpawnFactory) {
		super(factory, spawnGroup, bl, summonable, fireImmune, spawnableFarFromPlayer, spawnBlocks, entityDimensions, maxTrackDistance, trackTickInterval, alwaysUpdateVelocity);
		this.item = Registry.register(Registry.ITEM, id, new CartsesMinecartItem(this, new FabricItemSettings().group(ItemGroup.TRANSPORTATION)));
		this.worldSpawnFactory = worldSpawnFactory;
	}

	public Item getItem() {
		return item;
	}

	public WorldSpawnFactory<T> getWorldSpawnFactory() {
		return worldSpawnFactory;
	}

	public static class Builder<T extends CartsesMinecartEntity> extends FabricEntityTypeBuilder<T> {
		private final WorldSpawnFactory<T> worldSpawnFactory;
		private Identifier id;
		private EntityFactory<T> factory;

		protected Builder(SpawnGroup spawnGroup, EntityFactory<T> factory, WorldSpawnFactory<T> worldSpawnFactory) {
			super(spawnGroup, factory);
			this.worldSpawnFactory = worldSpawnFactory;
		}

		@Override
		public Builder<T> spawnGroup(SpawnGroup group) {
			return (Builder<T>) super.spawnGroup(group);
		}

		@Override
		public <N extends T> Builder<N> entityFactory(EntityFactory<N> factory) {
			this.factory = (EntityFactory<T>) factory;
			return (Builder<N>) super.entityFactory(factory);
		}

		@Override
		public Builder<T> disableSummon() {
			return (Builder<T>) super.disableSummon();
		}

		@Override
		public Builder<T> disableSaving() {
			return (Builder<T>) super.disableSaving();
		}

		@Override
		public Builder<T> fireImmune() {
			return (Builder<T>) super.fireImmune();
		}

		@Override
		public Builder<T> spawnableFarFromPlayer() {
			return (Builder<T>) super.spawnableFarFromPlayer();
		}

		@Override
		public Builder<T> dimensions(EntityDimensions dimensions) {
			return (Builder<T>) super.dimensions(dimensions);
		}

		@Override
		public Builder<T> trackRangeChunks(int range) {
			return (Builder<T>) super.trackRangeChunks(range);
		}

		@Override
		public Builder<T> trackRangeBlocks(int range) {
			return (Builder<T>) super.trackRangeBlocks(range);
		}

		@Override
		public Builder<T> trackedUpdateRate(int rate) {
			return (Builder<T>) super.trackedUpdateRate(rate);
		}

		@Override
		public Builder<T> forceTrackedVelocityUpdates(boolean forceTrackedVelocityUpdates) {
			return (Builder<T>) super.forceTrackedVelocityUpdates(forceTrackedVelocityUpdates);
		}

		@Override
		public Builder<T> specificSpawnBlocks(Block... blocks) {
			return (Builder<T>) super.specificSpawnBlocks(blocks);
		}

		public Builder<T> id(Identifier id) {
			this.id = id;
			return this;
		}

		@Override
		public CartsesEntityType<T> build() {
			EntityType<T> og = super.build();
			return new CartsesEntityType<>(this.factory, og.getSpawnGroup(), og.isSaveable(), og.isSummonable(), og.isFireImmune(), og.isSpawnableFarFromPlayer(), ((EntityTypeAccessor) og).getCanSpawnInside(), og.getDimensions(), og.getMaxTrackDistance(), og.getTrackTickInterval(), og.alwaysUpdateVelocity(), this.id, this.worldSpawnFactory);
		}
	}

	@FunctionalInterface
	public interface WorldSpawnFactory<T extends CartsesMinecartEntity> {
		T create(World world, double x, double y, double z);
	}
}
