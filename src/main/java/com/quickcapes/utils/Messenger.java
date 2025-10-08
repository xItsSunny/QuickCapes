package com.quickcapes.utils;

import com.quickcapes.QuickCapes;
import jline.internal.Nullable;
import net.minecraft.util.ChatComponentText;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Messenger {
    public static final String PREFIX = "&7[&6qc&7]&r ";
    private static final Queue<String> delayedMessage = new ConcurrentLinkedQueue<>();

    public static void sendMessage(String txt) {
        if (nullCheck()) {
            String m = formatColor(PREFIX + replace(txt));
            QuickCapes.mc.thePlayer.addChatMessage(new ChatComponentText(m));
        }
    }

    public static void sendMessageAnyWay(String txt) {
        if (nullCheck()) {
            sendMessage(txt);
        } else {
            delayedMessage.add(txt);
        }
    }

    static {
        QuickCapes.getExecutor().scheduleWithFixedDelay(() -> {
            if (nullCheck() && !delayedMessage.isEmpty()) {
                for (String s : delayedMessage) {
                    sendMessage(s);
                }
                delayedMessage.clear();
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    public static @Nullable String replace(String string) {
        if (string == null)
            return null;

        return string;
    }

    public static boolean nullCheck() {
        return QuickCapes.mc.thePlayer != null && QuickCapes.mc.theWorld != null;
    }

    public static String formatColor(String txt) {
        return txt.replaceAll("&", "§");
    }
}
