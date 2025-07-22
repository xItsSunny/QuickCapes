package com.quickcapes.mixin;

import com.quickcapes.QuickCapes;
import com.quickcapes.cape.CapeSetter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {

    @Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
    public void overrideCape(CallbackInfoReturnable<ResourceLocation> cir) {
        if ((AbstractClientPlayer) (Object) this == Minecraft.getMinecraft().thePlayer && CapeSetter.canRender()) cir.setReturnValue(CapeSetter.cape);
    }
}

