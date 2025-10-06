package com.quickcapes.cape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cape {
    private static final List<Cape> CAPES = new ArrayList<>();

    public static final Cape test = new Cape("Test");
    public static final Cape oxeye = new Cape("Oxeye");
    public static final Cape founders = new Cape("Founders");
    public static final Cape pan = new Cape("Pan");
    public static final Cape common = new Cape("Common");
    public static final Cape yearn = new Cape("Yearn");
    public static final Cape menace = new Cape("Menace");
    public static final Cape home = new Cape("Home");
    public static final Cape xmas = new Cape("Xmas");
    public static final Cape vanillacape = new Cape("VanillaCape");
    public static final Cape valentinecape = new Cape("ValentineCape");
    public static final Cape twitchcape = new Cape("TwitchCape");
    public static final Cape turtle = new Cape("Turtle");
    public static final Cape translator_japan = new Cape("Translator_Japan");
    public static final Cape translator_crowdin = new Cape("Translator_Crowdin");
    public static final Cape translator_chinese = new Cape("Translator_Chinese");
    public static final Cape tiktokcape = new Cape("TikTokCape");
    public static final Cape sniffercape = new Cape("SnifferCape");
    public static final Cape snailcape = new Cape("SnailCape");
    public static final Cape scrolls = new Cape("Scrolls");
    public static final Cape realms = new Cape("Realms");
    public static final Cape prismarine = new Cape("Prismarine");
    public static final Cape oldmojang = new Cape("OldMojang");
    public static final Cape newyears = new Cape("NewYears");
    public static final Cape newmojang = new Cape("NewMojang");
    public static final Cape mscape = new Cape("MSCape");
    public static final Cape mrmessiah = new Cape("MrMessiah");
    public static final Cape mojangstudios = new Cape("MojangStudios");
    public static final Cape minecraftexperience = new Cape("MinecraftExperience");
    public static final Cape mcchampionship = new Cape("MCChampionship");
    public static final Cape julianclark = new Cape("JulianClark");
    public static final Cape gr8_escape = new Cape("Gr8_Escape");
    public static final Cape frogcape = new Cape("FrogCape");
    public static final Cape dannybstyle = new Cape("dannyBstyle");
    public static final Cape cubed = new Cape("Cubed");
    public static final Cape cobalt = new Cape("Cobalt");
    public static final Cape fifteenanniversary = new Cape("FifteenAnniversary");
    public static final Cape cherryblossom = new Cape("CherryBlossom");
    public static final Cape bugtracker = new Cape("BugTracker");
    public static final Cape bacon = new Cape("Bacon");
    public static final Cape millionth_customer = new Cape("Millionth_Customer");
    public static final Cape eyeblossom = new Cape("Eyeblossom");
    public static final Cape unused_14 = new Cape("Unused_14");
    public static final Cape unused_13 = new Cape("Unused_13");
    public static final Cape unused_12 = new Cape("Unused_12");
    public static final Cape unused_11 = new Cape("Unused_11");
    public static final Cape unused_10 = new Cape("Unused_10");
    public static final Cape unused_9 = new Cape("Unused_9");
    public static final Cape unused_8 = new Cape("Unused_8");
    public static final Cape unused_7 = new Cape("Unused_7");
    public static final Cape unused_6 = new Cape("Unused_6");
    public static final Cape unused_5 = new Cape("Unused_5");
    public static final Cape unused_4 = new Cape("Unused_4");
    public static final Cape unused_3 = new Cape("Unused_3");
    public static final Cape unused_2 = new Cape("Unused_2");
    public static final Cape unused_1 = new Cape("Unused_1");
    public static final Cape minecon_2016 = new Cape("Minecon_2016");
    public static final Cape minecon_2015 = new Cape("Minecon_2015");
    public static final Cape minecon_2013 = new Cape("Minecon_2013");
    public static final Cape minecon_2012 = new Cape("Minecon_2012");
    public static final Cape minecon_2011 = new Cape("Minecon_2011");

    public final String name;
    public final String resource;

    private Cape(String name) {
        this.name = name;
        this.resource = name.toLowerCase().replace(" ", "_") + ".png";
        CAPES.add(this);
    }

    public Cape next() {
        int index = CAPES.indexOf(this);
        return CAPES.get((index + 1) % CAPES.size());
    }

    public Cape prev() {
        int index = CAPES.indexOf(this);
        return CAPES.get(index - 1 >= 0 ? index - 1 : CAPES.size() - 1);
    }

    public static Cape getCape(String name) {
        for (Cape cape : CAPES) {
            if (cape.resource.equals(name)) {
                return cape;
            }
        }
        return minecon_2011;
    }

    public static List<Cape> values() {
        return Collections.unmodifiableList(CAPES);
    }

    @Override
    public String toString() {
        return name;
    }
}
