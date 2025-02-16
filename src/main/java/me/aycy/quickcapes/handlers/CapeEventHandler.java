package me.aycy.quickcapes.handlers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.aycy.quickcapes.QuickCapes;
import me.aycy.quickcapes.cape.CustomLayerCape;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class CapeEventHandler {
    private static final Field FIELD_RENDERERS = ReflectionHelper.findField(RendererLivingEntity.class, new String[]{"field_177097_h", "layerRenderers"});
    private final ArrayList<LayerRenderer> oldCapes = new ArrayList();
    private boolean flag = true;
    private boolean flag1;

    public CapeEventHandler() {
    }

    @SubscribeEvent(
            receiveCanceled = true
    )
    public void onRenderPlayer(RenderPlayerEvent.Pre event) {
        if (event.entity instanceof EntityPlayerSP) {
            if (!this.flag1) {
                this.flag1 = true;
                event.renderer.addLayer(new CustomLayerCape(event.renderer));
            }

            if (QuickCapes.config.isEnabled() && this.flag) {
                try {
                    List<LayerRenderer> layerRenderers = (List)FIELD_RENDERERS.get(event.renderer);
                    Iterator<LayerRenderer> iter = layerRenderers.iterator();

                    while(iter.hasNext()) {
                        LayerRenderer renderer = (LayerRenderer)iter.next();
                        if (renderer instanceof LayerCape) {
                            this.oldCapes.add(renderer);
                            iter.remove();
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    this.flag = false;
                }
            } else if (!QuickCapes.config.isEnabled() && !this.flag) {
                this.flag = true;
                RenderPlayer var10001 = event.renderer;
                this.oldCapes.forEach(var10001::addLayer);
            }
        }

    }
}
