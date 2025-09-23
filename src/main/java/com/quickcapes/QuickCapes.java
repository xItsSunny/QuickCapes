package com.quickcapes;

import com.quickcapes.commands.CommandQuickCapes;
import com.quickcapes.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = "quickcapes", useMetadata=true)
public class QuickCapes {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Config config = new Config(new File(Loader.instance().getConfigDir(), "quickcapes.cfg"));

    public QuickCapes() {
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        config.load();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandQuickCapes());
        MinecraftForge.EVENT_BUS.register(new com.quickcapes.utils.TickManager());
    }
}
