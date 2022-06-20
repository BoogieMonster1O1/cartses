package io.github.boogiemonster1o1.cartses.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableSet;
import io.github.boogiemonster1o1.cartses.item.CartsesMinecartItem;

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
	public static final Set<CartsesEntityType<? extends CartsesMinecartEntity>> ALL = new HashSet<>();

	private final Item item;
	private final Identifier id;
	private final WorldSpawnFactory<T> worldSpawnFactory;
	private final ToIntFunction<T> blockLightFunction;
	private final ToIntFunction<T> skyLightFunction;

	public CartsesEntityType(EntityFactory<T> factory, SpawnGroup spawnGroup, boolean bl, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> spawnBlocks, EntityDimensions entityDimensions, int maxTrackDistance, int trackTickInterval, Boolean alwaysUpdateVelocity, Identifier id, WorldSpawnFactory<T> worldSpawnFactory, ToIntFunction<T> blockLightFunction, ToIntFunction<T> skyLightFunction) {
		super(factory, spawnGroup, bl, summonable, fireImmune, spawnableFarFromPlayer, spawnBlocks, entityDimensions, maxTrackDistance, trackTickInterval, alwaysUpdateVelocity);
		this.blockLightFunction = blockLightFunction;
		this.skyLightFunction = skyLightFunction;
		this.item = new CartsesMinecartItem(this, new FabricItemSettings().group(ItemGroup.TRANSPORTATION));
		this.id = id;
		this.worldSpawnFactory = worldSpawnFactory;
		register();
	}
	public final void register() {
		Registry.register(Registry.ENTITY_TYPE, this.id, this);
		Registry.register(Registry.ITEM, this.id, this.item);
		ALL.add(this);
	}

	public ToIntFunction<T> getBlockLightFunction() {
		return blockLightFunction;
	}

	public ToIntFunction<T> getSkyLightFunction() {
		return skyLightFunction;
	}

	public Item getItem() {
		return item;
	}

	public Identifier getId() {
		return id;
	}

	public WorldSpawnFactory<T> getWorldSpawnFactory() {
		return worldSpawnFactory;
	}

	public static class Builder<T extends CartsesMinecartEntity> extends FabricEntityTypeBuilder<T> {
		private final SpawnGroup spawnGroup;
		private final WorldSpawnFactory<T> worldSpawnFactory;
		private EntityType.EntityFactory<T> factory;
		private Identifier id;
		private boolean saveable = true;
		private boolean summonable = true;
		private int trackRange = 5;
		private int trackedUpdateRate = 3;
		private Boolean forceTrackedVelocityUpdates;
		private boolean fireImmune = false;
		private boolean spawnableFarFromPlayer;
		private EntityDimensions dimensions = EntityDimensions.changing(-1.0f, -1.0f);
		private ImmutableSet<Block> specificSpawnBlocks = ImmutableSet.of();
		private ToIntFunction<T> blockLightFunction = cart -> -1;
		private ToIntFunction<T> skyLightFunction = cart -> -1;

		protected Builder(SpawnGroup spawnGroup, EntityFactory<T> factory, WorldSpawnFactory<T> worldSpawnFactory) {
			super(spawnGroup, factory);
			this.factory = factory;
			this.spawnGroup = spawnGroup;
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
			this.summonable = false;
			return this;
		}

		@Override
		public Builder<T> disableSaving() {
			this.saveable = false;
			return this;
		}

		@Override
		public Builder<T> fireImmune() {
			this.fireImmune = true;
			return this;
		}

		@Override
		public Builder<T> spawnableFarFromPlayer() {
			this.spawnableFarFromPlayer = true;
			return this;
		}

		@Override
		public Builder<T> dimensions(EntityDimensions dimensions) {
			this.dimensions = dimensions;
			return this;
		}

		@Override
		public Builder<T> trackRangeChunks(int range) {
			this.trackRange = range;
			return this;
		}

		@Override
		public Builder<T> trackedUpdateRate(int rate) {
			this.trackedUpdateRate = rate;
			return this;
		}

		@Override
		public Builder<T> forceTrackedVelocityUpdates(boolean forceTrackedVelocityUpdates) {
			this.forceTrackedVelocityUpdates = forceTrackedVelocityUpdates;
			return this;
		}

		@Override
		public Builder<T> specificSpawnBlocks(Block... blocks) {
			this.specificSpawnBlocks = ImmutableSet.copyOf(blocks);
			return this;
		}

		public Builder<T> id(Identifier id) {
			this.id = id;
			return this;
		}

		public Builder<T> blockLightFunction(ToIntFunction<T> blockLightFunction) {
			this.blockLightFunction = blockLightFunction;
			return this;
		}

		public Builder<T> skyLightFunction(ToIntFunction<T> skyLightFunction) {
			this.skyLightFunction = skyLightFunction;
			return this;
		}

		@Override
		public CartsesEntityType<T> build() {
			return new CartsesEntityType<>(this.factory, spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, specificSpawnBlocks, dimensions, trackRange, trackedUpdateRate, forceTrackedVelocityUpdates, this.id, this.worldSpawnFactory, this.blockLightFunction, this.skyLightFunction);
		}

		public static <T extends CartsesMinecartEntity> Builder<T> create(SpawnGroup spawnGroup, EntityType.EntityFactory<T> factory, WorldSpawnFactory<T> worldSpawnFactory) {
			return new CartsesEntityType.Builder<>(spawnGroup, factory, worldSpawnFactory);
		}
	}

	@FunctionalInterface
	public interface WorldSpawnFactory<T extends CartsesMinecartEntity> {
		T create(World world, double x, double y, double z);
	}
}
