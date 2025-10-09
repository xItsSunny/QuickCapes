package com.quickcapes.utils;

import com.quickcapes.QuickCapes;

public class Helper {
    public static void init() {
        if (QuickCapes.config.isInfo()) {
            QuickCapes.getExecutor().execute(() -> {
                Messenger.sendMessageAnyWay("If you need help with the mod use /qchelp!");
                boolean flag = !QuickCapes.config.isInfo();
                QuickCapes.config.setInfo(flag);
            });
        }
    }
}
