package cc.unilock.deer.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class SolidGoldDeerArtifactItem extends Item {
	// TODO: Text.translatable("item.deer.solid_gold_deer_artifact.tooltip")
	private final String tooltipString = "Every deer is gold";
	private final int tooltipLength = tooltipString.length();
	private final int maxTickOffset = ((tooltipLength - 1) * 5) + 4;
	private int tickOffset = 0;

	public SolidGoldDeerArtifactItem(Settings settings) {
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(stack, world, entity, slot, selected);
		tickOffset++;
		if (tickOffset > maxTickOffset) tickOffset = 0;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		int offset = MathHelper.floor((double) tickOffset / 5);
		String first = tooltipString.substring(0, offset);
		String second = tooltipString.substring(offset, offset + 1);
		String third = tooltipString.substring(offset + 1, tooltipLength);
		tooltip.add(Text.empty()
			.append(Text.literal(first).formatted(Formatting.GOLD).formatted(Formatting.ITALIC))
			.append(Text.literal(second).formatted(Formatting.YELLOW).formatted(Formatting.ITALIC))
			.append(Text.literal(third).formatted(Formatting.GOLD).formatted(Formatting.ITALIC))
		);
	}
}
