package com.quickcapes.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Info {
    public static final String VERSION = "2.0.4";
    public static final List<String> CHANGELOG = Collections.unmodifiableList(Arrays.asList(
            "&3QuickCapes &6" + VERSION + " &3Changelog:",
            "-[+] **Rework** Cape enum turned into class",
            "-[+] **Rework** Cape rendering system",
            "-[+] **Add** Help message at first launch",
            "-[+] **Add** Support for animated capes",
            "-[+] **Add** Toggle for Update Message",
            "-[+] **Add** Player Number Tracker",
            "-[+] **Add** Automatic mod updates",
            "-[+] **Add** Changelog system",
            "-[+] **Add** Help system",
            "-[+] **FIX** Essential Capes Overlapping",
            "-[+] **WIP** Texture downloading instead of in jar",
            "-[+] **WIP** Dynamic cape updating"
            ));
    public static final List<String> HELP = Collections.unmodifiableList(Arrays.asList(
            "Use the command /capes to open the gui.",
            "Use the command /qcchangelog to see the newest changes.",
            "Use the command /qcupdate to update the mod."
    ));
}
