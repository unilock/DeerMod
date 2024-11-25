package cc.unilock.deer.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CookingRecipeSerializer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CookingRecipeSerializer.class)
public class CookingRecipeSerializerMixin {
	@Redirect(method = "method_53766", at = @At(value = "FIELD", target = "Lnet/minecraft/item/ItemStack;VALIDATED_UNCOUNTED_CODEC:Lcom/mojang/serialization/Codec;", opcode = Opcodes.GETSTATIC))
	private static Codec<ItemStack> method_53766$getCodec() {
		return ItemStack.VALIDATED_CODEC;
	}
}
