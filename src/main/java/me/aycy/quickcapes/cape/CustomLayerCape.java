package me.aycy.quickcapes.cape;

import me.aycy.quickcapes.QuickCapes;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CustomLayerCape implements LayerRenderer<AbstractClientPlayer> {
    private static ResourceLocation cape;
    private final RenderPlayer playerRenderer;

    public CustomLayerCape(RenderPlayer playerRenderer) {
        this.playerRenderer = playerRenderer;
    }

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float prevLimbSwing, float partialTicks, float ticksExisted, float rotation, float prevRotation, float scale) {
        if (player.isUser() && player.hasPlayerInfo() && !player.isInvisible() && QuickCapes.config.isEnabled()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.playerRenderer.bindTexture(cape);
            GlStateManager.pushMatrix();
            if (player.isSneaking()) {
                GlStateManager.translate(0.0F, 0.145F, 0.11F);
            } else {
                GlStateManager.translate(0.0F, 0.0F, 0.125F);
            }

            double x = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
            double y = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
            double z = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
            float renderYaw = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
            double d3 = (double)MathHelper.sin(renderYaw * (float)Math.PI / 180.0F);
            double d4 = (double)(-MathHelper.cos(renderYaw * (float)Math.PI / 180.0F));
            float f1 = (float)y * 10.0F;
            f1 = MathHelper.clamp_float(f1, -6.0F, 32.0F);
            float f2 = (float)(x * d3 + z * d4) * 100.0F;
            float f3 = (float)(x * d4 - z * d3) * 100.0F;
            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            float cameraYaw = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
            f1 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * cameraYaw;
            if (player.isSneaking()) {
                f1 += 25.0F;
            }

            GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.playerRenderer.getMainModel().renderCape(0.0625F);
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }

    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public static void setCape(Cape cape) {
        CustomLayerCape.cape = new ResourceLocation("quickcapes", "textures/capes/" + cape.resource);
    }
}
