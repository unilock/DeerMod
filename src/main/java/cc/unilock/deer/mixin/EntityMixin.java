package cc.unilock.deer.mixin;

import cc.unilock.deer.init.DeerItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "onStruckByLightning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LightningEntity;)V", at = @At("HEAD"), cancellable = true)
	private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
		if (ItemEntity.class.isAssignableFrom(getClass())) {
			ItemEntity item = ItemEntity.class.cast(this);
			if (item.getStack().isOf(DeerItems.RAW_DEER)) {
				ItemEntity processed = new ItemEntity(world, item.getX(), item.getY(), item.getZ(), DeerItems.PROCESSED_DEER.getDefaultStack());
				item.discard();
				world.spawnEntity(processed);
				ci.cancel();
			}
			if (item.getStack().isOf(DeerItems.PROCESSED_DEER)) {
				ci.cancel();
			}
		}
	}
}
