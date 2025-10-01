package cc.unilock.deer.mixin;

import cc.unilock.deer.init.DeerItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
	@WrapOperation(method = "craftRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
	private static void craftRecipe$decrement(ItemStack instance, int amount, Operation<Void> original, @Local(argsOnly = true) DefaultedList<ItemStack> slots) {
		boolean bl = instance.isOf(DeerItems.SOLID_GOLD_DEER_ARTIFACT);
		original.call(instance, amount);
		if (bl) slots.set(0, DeerItems.RAW_DEER.getDefaultStack());
	}
}
