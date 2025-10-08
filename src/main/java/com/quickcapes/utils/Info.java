package com.quickcapes.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Info {
    public static final String VERSION = "2.0.3";
    public static final List<String> CHANGELOG = Collections.unmodifiableList(Arrays.asList(
            "&3QuickCapes &6" + VERSION + " &3Changelog:",
            "-[+] **Rework** Cape enum turned into class",
            "-[+] **Rework** Cape rendering system",
            "-[+] **Add** Support for animated capes",
            "-[+] **Add** Automatic mod updates",
            "-[+] **Add** Changelog system",
            "-[+] **WIP** Texture downloading instead of in jar",
            "-[+] **WIP** Dynamic cape updating"
            ));
}
