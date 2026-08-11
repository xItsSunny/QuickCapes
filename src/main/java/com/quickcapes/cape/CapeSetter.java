package com.quickcapes.cape;

import com.quickcapes.QuickCapes;
import net.minecraft.util.ResourceLocation;

public class CapeSetter {
    public static ResourceLocation cape;

    public static void setCape(Cape cape) {
        CapeSetter.cape = new ResourceLocation("quickcapes", "textures/capes/" + cape.resource);
    }

    public static boolean canRender() {
        return QuickCapes.config.isEnabled() && QuickCapes.config.getCape() != null;
    }
}
