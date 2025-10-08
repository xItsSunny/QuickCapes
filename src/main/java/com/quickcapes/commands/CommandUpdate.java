package com.quickcapes.commands;

import com.quickcapes.QuickCapes;
import com.quickcapes.utils.AutoUpdater;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandUpdate extends CommandBase {
    public CommandUpdate() {
    }

    public String getCommandName() {
        return "qcupdate";
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
        QuickCapes.getExecutor().execute(AutoUpdater::update);
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}