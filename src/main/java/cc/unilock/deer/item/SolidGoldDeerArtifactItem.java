package cc.unilock.deer.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SolidGoldDeerArtifactItem extends Item {
	public SolidGoldDeerArtifactItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("item.deer.solid_gold_deer_artifact.tooltip").formatted(Formatting.GOLD).formatted(Formatting.ITALIC));
	}
}
