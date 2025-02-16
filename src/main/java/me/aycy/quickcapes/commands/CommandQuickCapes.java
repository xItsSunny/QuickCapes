package me.aycy.quickcapes.commands;

import me.aycy.quickcapes.QuickCapes;
import me.aycy.quickcapes.gui.GuiQuickCapes;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandQuickCapes extends CommandBase {
    public CommandQuickCapes() {
    }

    public String getCommandName() {
        return "capes";
    }

    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.RED + "/" + this.getCommandName();
    }

    public void processCommand(ICommandSender sender, String[] args) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        QuickCapes.mc.displayGuiScreen(new GuiQuickCapes());
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
