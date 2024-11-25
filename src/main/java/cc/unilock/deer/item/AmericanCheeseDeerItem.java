package cc.unilock.deer.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AmericanCheeseDeerItem extends Item {
	public AmericanCheeseDeerItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		ItemStack finished = super.finishUsing(stack, world, user);
		if (!world.isClient) {
			LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
			if (lightning != null) {
				lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(user.getBlockPos()));
				world.spawnEntity(lightning);
			}
		}
		return finished;
	}
}
