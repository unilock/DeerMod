package cc.unilock.deer.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import java.util.Optional;

public class ProcessedDeerItem extends Item {
	public ProcessedDeerItem(Settings settings) {
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient && entity instanceof LivingEntity living && living.isHolding(this)) {
			Optional<RegistryEntry.Reference<SoundEvent>> optional = Registries.SOUND_EVENT.getRandom(world.random);
			if (optional.isPresent()) {
				RegistryEntry.Reference<SoundEvent> reference = optional.get();
				if (!reference.registryKey().getValue().getPath().startsWith("music")) {
					world.playSound(null, living.getX(), living.getY(), living.getZ(), reference, SoundCategory.MASTER, 1.0F, ((float) world.random.nextBetween(5, 15)) / 10, world.random.nextLong());
				}
			}
		}
	}
}
