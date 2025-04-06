package me.aycy.quickcapes;

import java.io.File;
import me.aycy.quickcapes.commands.CommandQuickCapes;
import me.aycy.quickcapes.config.Config;
import me.aycy.quickcapes.handlers.CapeEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "quickcapes",
        name = "Quick Capes",
        version = "1.7",
        clientSideOnly = true,
        acceptedMinecraftVersions = "[1.8.9]"
)
public class QuickCapes {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Config config = new Config(new File(Loader.instance().getConfigDir(), "quickcapes.cfg"));

    public QuickCapes() {
    }

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        config.load();
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new CapeEventHandler());
        ClientCommandHandler.instance.registerCommand(new CommandQuickCapes());
    }
}
