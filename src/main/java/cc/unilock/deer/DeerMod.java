package cc.unilock.deer;

import cc.unilock.deer.init.DeerItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class DeerMod implements ModInitializer {
	public static final String MOD_ID = "deer";
//    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DeerItems.init();

		LootTableEvents.MODIFY.register((key, builder, source, wrapperLookup) -> {
			if (source.isBuiltin() && LootTables.SIMPLE_DUNGEON_CHEST.equals(key)) {
				LootPool pool = LootPool.builder()
					.rolls(UniformLootNumberProvider.create(0, 1))
					.with(ItemEntry.builder(DeerItems.SOLID_GOLD_DEER_ARTIFACT))
					.build();

				builder.pool(pool);
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
