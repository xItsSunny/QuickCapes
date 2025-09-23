package com.quickcapes.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TickManager {
    private static final List<ScheduledTask> tasks = new ArrayList<>();

    public static void waitTicks(int ticks, Runnable action) {
        tasks.add(new ScheduledTask(ticks, action, false));
    }

    public static void repeatTicks(int interval, Runnable action) {
        tasks.add(new ScheduledTask(interval, action, true));
    }

    public static void clearTasks() {
        tasks.clear();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Iterator<ScheduledTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            task.ticksRemaining--;

            if (task.ticksRemaining <= 0) {
                task.action.run();

                if (task.repeat) {
                    task.ticksRemaining = task.originalDelay;
                } else {
                    iterator.remove();
                }
            }
        }
    }

    private static class ScheduledTask {
        int ticksRemaining;
        final int originalDelay;
        final Runnable action;
        final boolean repeat;

        ScheduledTask(int ticksRemaining, Runnable action, boolean repeat) {
            this.ticksRemaining = ticksRemaining;
            this.originalDelay = ticksRemaining;
            this.action = action;
            this.repeat = repeat;
        }
    }
}
