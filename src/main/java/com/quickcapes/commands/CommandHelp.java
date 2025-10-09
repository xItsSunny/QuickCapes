package com.quickcapes.commands;

import com.quickcapes.QuickCapes;
import com.quickcapes.utils.AutoUpdater;
import com.quickcapes.utils.Info;
import com.quickcapes.utils.Messenger;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandHelp extends CommandBase {
    public CommandHelp() {
    }

    public String getCommandName() {
        return "qchelp";
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
        for (String line : Info.HELP) {
            Messenger.sendMessage(line);
        }
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}