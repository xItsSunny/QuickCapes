package me.aycy.quickcapes.config;

import java.io.File;
import me.aycy.quickcapes.cape.Cape;
import me.aycy.quickcapes.cape.CustomLayerCape;
import net.minecraftforge.common.config.Configuration;

public class Config extends Configuration {
    private static boolean enabled;
    private static Cape cape;

    public Config(File configFile) {
        super(configFile, true);
    }

    public void load() {
        super.load();
        enabled = super.get("client", "enabled", true).getBoolean();
        CustomLayerCape.setCape(cape = Cape.getCape(super.get("client", "cape", "minecon_2016.png").getString()));
        super.save();
    }

    public void save() {
        super.get("client", "enabled", true).set(enabled);
        super.get("client", "cape", "minecon_2016.png").set(cape.resource);
        super.save();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        Config.enabled = enabled;
    }

    public Cape getCape() {
        return cape;
    }

    public void setCape(Cape cape) {
        Config.cape = cape;
    }
}
