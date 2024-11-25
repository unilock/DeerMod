package cc.unilock.deer.mixin;

import cc.unilock.deer.init.DeerItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "onStruckByLightning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LightningEntity;)V", at = @At("HEAD"), cancellable = true)
	private void onStruckByLightning(CallbackInfo ci) {
		if (ItemEntity.class.isAssignableFrom(getClass())) {
			ItemEntity itemEntity = (ItemEntity) (Object) this;
			if (itemEntity.getStack().isOf(DeerItems.RAW_DEER)) {
				itemEntity.setStack(DeerItems.PROCESSED_DEER.getDefaultStack());
				ci.cancel();
			}
		}
	}
}
