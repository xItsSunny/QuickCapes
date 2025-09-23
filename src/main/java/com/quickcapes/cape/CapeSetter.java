package com.quickcapes.cape;

import com.quickcapes.QuickCapes;
import com.quickcapes.utils.TickManager;
import net.minecraft.util.ResourceLocation;

public class CapeSetter {
    public static ResourceLocation cape;

    public static void setCape(Cape cape) {
        TickManager.clearTasks();
        if (cape == Cape.test) {
            ResourceLocation[] frames = new ResourceLocation[]{
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_01.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_02.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_03.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_04.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_05.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_06.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_07.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_08.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_09.png"),
                    new ResourceLocation("quickcapes", "textures/capes/animated/test/frame_10.png"),
            };

            CapeSetter.cape = frames[0];

            TickManager.repeatTicks(3, new Runnable() {
                int frameIndex = 0;

                @Override
                public void run() {
                    frameIndex = (frameIndex + 1) % frames.length;
                    CapeSetter.cape = frames[frameIndex];
                }
            });
        } else {
            CapeSetter.cape = new ResourceLocation("quickcapes", "textures/capes/" + cape.resource);
        }
    }

    public static boolean canRender() {
        return QuickCapes.config.isEnabled() && QuickCapes.config.getCape() != null;
    }
}
