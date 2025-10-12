package com.quickcapes;

import com.quickcapes.commands.CommandChangelog;
import com.quickcapes.commands.CommandHelp;
import com.quickcapes.commands.CommandQuickCapes;
import com.quickcapes.commands.CommandUpdate;
import com.quickcapes.config.Config;
import com.quickcapes.utils.AutoUpdater;
import com.quickcapes.utils.Downloader;
import com.quickcapes.utils.Helper;
import com.quickcapes.utils.Info;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Mod(modid = "quickcapes", useMetadata=true)
public class QuickCapes {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Config config = new Config(new File(Loader.instance().getConfigDir(), "quickcapes.cfg"));
    private static final String API_URL = "https://api.github.com/repos/xItsSunny/QuickCapes-Resources/contents/quickcapes";
    private static final File LOCAL_DIR = new File("quickcapes/");
    public static final String VERSION = Info.VERSION;
    private static final String TRACKER_URL = "https://quickcapes-api.onrender.com/";
    private static final String SESSION_ID = UUID.randomUUID().toString();
    @Getter
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);

    public QuickCapes() {
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        config.load();
        Downloader downloader = new Downloader(API_URL, LOCAL_DIR);
        downloader.downloadAll();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandQuickCapes());
        ClientCommandHandler.instance.registerCommand(new CommandUpdate());
        ClientCommandHandler.instance.registerCommand(new CommandChangelog());
        ClientCommandHandler.instance.registerCommand(new CommandHelp());
        MinecraftForge.EVENT_BUS.register(new com.quickcapes.utils.TickManager());
        Runtime.getRuntime().addShutdownHook(new Thread(executor::shutdown));
        AutoUpdater.init();
        Helper.init();
        sendRequest("join");
    }

    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event) {
        sendRequest("leave");
    }

    public void sendRequest(String action) {
        new Thread(() -> {
            try {
                URL url = new URL(TRACKER_URL + "?action=" + action + "&uuid=" + SESSION_ID);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(2000);
                conn.getResponseCode();
                conn.disconnect();
            } catch (Exception e) {

            }
        }).start();
    }
}
