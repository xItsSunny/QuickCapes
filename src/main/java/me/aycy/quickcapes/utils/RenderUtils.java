package me.aycy.quickcapes.utils;

import me.aycy.quickcapes.QuickCapes;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;

public class RenderUtils {
    private static final Animator animator = new Animator(3000.0F);
    private static boolean flag = true;

    public RenderUtils() {
    }

    public static void renderPlayerOnScreen(int x, int y, int scale, EntityPlayer player) {
        float f = animator.getValue(138.0F, 222.0F, flag);
        if (f == 222.0F && flag) {
            flag = false;
            animator.reset();
        } else if (f == 138.0F && !flag) {
            flag = true;
            animator.reset();
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float renderYawOffset = player.renderYawOffset;
        float rotationYaw = player.rotationYaw;
        float rotationPitch = player.rotationPitch;
        float rotationYawHead = player.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        player.renderYawOffset = f;
        player.rotationYaw = f;
        player.rotationPitch = 0.0F;
        player.rotationYawHead = f;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = QuickCapes.mc.getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(player, (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        player.renderYawOffset = renderYawOffset;
        player.rotationYaw = rotationYaw;
        player.rotationPitch = rotationPitch;
        player.rotationYawHead = rotationYawHead;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
