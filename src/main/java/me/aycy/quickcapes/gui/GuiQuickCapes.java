package me.aycy.quickcapes.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import me.aycy.quickcapes.QuickCapes;
import me.aycy.quickcapes.cape.Cape;
import me.aycy.quickcapes.cape.CustomLayerCape;
import me.aycy.quickcapes.utils.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class GuiQuickCapes extends GuiScreen {
    private GuiButton buttonPrev;
    private GuiButton buttonNext;
    private GuiButton buttonEnable;
    private GuiButton buttonFolder;
    private GuiButton buttonLoad;
    public File directory;

    public GuiQuickCapes() {
    }

    public void initGui() {
        super.buttonList.clear();
        super.buttonList.add(this.buttonPrev = new GuiButton(0, super.width / 2 - 80, super.height / 2 - 10, 14, 20, "<"));
        super.buttonList.add(this.buttonNext = new GuiButton(1, super.width / 2 + 65, super.height / 2 - 10, 14, 20, ">"));
        boolean flag = QuickCapes.config.isEnabled();
        super.buttonList.add(this.buttonEnable = new GuiButton(5, super.width / 2 - 50, super.height - 35, 100, 20, "Cape: " + (flag ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off")));
        this.buttonPrev.enabled = flag;
        this.buttonNext.enabled = flag;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawBackground(0);
        GuiScreen.drawRect(0, 50, super.width, super.height - 50, Integer.MIN_VALUE);
        super.drawGradientRect(0, 50, super.width, 58, Integer.MIN_VALUE, 0);
        super.drawGradientRect(0, super.height - 58, super.width, super.height - 50, 0, Integer.MIN_VALUE);
        GL11.glPushMatrix();
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        super.drawCenteredString(super.fontRendererObj, "Quick Capes", (int)((float)super.width / 1.5F) / 2, 13, -1);
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (QuickCapes.config.isEnabled()) {
            int i = super.fontRendererObj.getStringWidth(QuickCapes.config.getCape().name) / 2 + 12;
            super.drawHoveringText(Collections.singletonList(QuickCapes.config.getCape().name), super.width / 2 - i, super.height / 2 + 65);
        }

        RenderUtils.renderPlayerOnScreen(super.width / 2, super.height / 2 + 40, 50, QuickCapes.mc.thePlayer);
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                QuickCapes.config.setCape(QuickCapes.config.getCape().prev());
                CustomLayerCape.setCape(QuickCapes.config.getCape());
                break;
            case 1:
                QuickCapes.config.setCape(QuickCapes.config.getCape().next());
                CustomLayerCape.setCape(QuickCapes.config.getCape());
                break;
            case 5:
                boolean flag = !QuickCapes.config.isEnabled();
                QuickCapes.config.setEnabled(flag);
                this.buttonEnable.displayString = "Cape: " + (flag ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off");
                this.buttonPrev.enabled = flag;
                this.buttonNext.enabled = flag;
                break;
        }

    }

    public void onGuiClosed() {
        QuickCapes.config.save();
    }
}