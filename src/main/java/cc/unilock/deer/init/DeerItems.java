package cc.unilock.deer.init;

import cc.unilock.deer.DeerMod;
import cc.unilock.deer.item.AmericanCheeseDeerItem;
import cc.unilock.deer.item.ProcessedDeerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class DeerItems {
	public static final Item AMERICAN_CHEESE_DEER = register("american_cheese_deer", new AmericanCheeseDeerItem(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100), 1.0F).build()).maxCount(1)));
	public static final Item SOLID_GOLD_DEER_ARTIFACT = register("solid_gold_deer_artifact", new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
	public static final Item RAW_DEER = register("raw_deer", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
	public static final Item PROCESSED_DEER = register("processed_deer", new ProcessedDeerItem(new Item.Settings().attributeModifiers(AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(DeerMod.id("speed"), 37.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HAND).build()).maxCount(1).rarity(Rarity.EPIC)));

	public static void init() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(AMERICAN_CHEESE_DEER);
			entries.add(SOLID_GOLD_DEER_ARTIFACT);
			entries.add(RAW_DEER);
			entries.add(PROCESSED_DEER);
		});
	}

	private static Item register(String path, Item item) {
		return Registry.register(Registries.ITEM, DeerMod.id(path), item);
	}
}
