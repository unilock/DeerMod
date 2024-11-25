package cc.unilock.deer.mixin;

import cc.unilock.deer.init.DeerItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
	@WrapOperation(method = "craftRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
	private static void craftRecipe$decrement(ItemStack instance, int amount, Operation<Void> original, @Local LocalRef<ItemStack> artifact) {
		if (instance.isOf(DeerItems.SOLID_GOLD_DEER_ARTIFACT)) {
			artifact.set(DeerItems.RAW_DEER.getDefaultStack());
		} else {
			original.call(instance, amount);
		}
	}
}
