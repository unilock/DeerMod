package cc.unilock.deer.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.I18n;
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
	private String tooltipString;
	private int tooltipLength;
	private int maxTickOffset;
	private int tickOffset = 0;

	public SolidGoldDeerArtifactItem(Settings settings) {
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(stack, world, entity, slot, selected);
		if (world.isClient && tooltipString != null) {
			tickOffset++;
			if (tickOffset > maxTickOffset) tickOffset = 0;
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);

		if (tooltipString == null) {
			if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
				tooltipString = I18n.translate("item.deer.solid_gold_deer_artifact.tooltip");
			} else {
				tooltipString = "Every deer is gold";
			}
			tooltipString += " ";
			tooltipLength = tooltipString.length();
			maxTickOffset = ((tooltipLength - 1) * 3) + 2;
		}

		int offset = MathHelper.floor((double) tickOffset / 3);
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
