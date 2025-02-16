package me.aycy.quickcapes.cape;

public enum Cape {
    xmas("Xmas"),
    vanillacape("VanillaCape"),
    valentinecape("ValentineCape"),
    twitchcape("TwitchCape"),
    turtle("Turtle"),
    translator_japan("Translator_Japan"),
    translator_crowdin("Translator_Crowdin"),
    translator_chinese("Translator_Chinese"),
    tiktokcape("TikTokCape"),
    sniffercape("SnifferCape"),
    snailcape("SnailCape"),
    scrolls("Scrolls"),
    realms("Realms"),
    prismarine("Prismarine"),
    oldmojang("OldMojang"),
    newyears("NewYears"),
    newmojang("NewMojang"),
    mscape("MSCape"),
    mrmessiah("MrMessiah"),
    mojangstudios("MojangStudios"),
    minecraftexperience("MinecraftExperience"),
    mcchampionship("MCChampionship"),
    julianclark("JulianClark"),
    gr8_escape("Gr8_Escape"),
    frogcape("FrogCape"),
    dannybstyle("dannyBstyle"),
    cubed("Cubed"),
    cobalt("Cobalt"),
    fifteenanniversary("FifteenAnniversary"),
    cherryblossom("CherryBlossom"),
    bugtracker("BugTracker"),
    bacon("Bacon"),
    millionth_customer("Millionth_Customer"),
    eyeblossom("Eyeblossom"),
    unused2011_3("Unused2011_3"),
    unused2011_2("Unused2011_2"),
    unused2011_1("Unused2011_1"),
    minecon_2016("Minecon_2016"),
    minecon_2015("Minecon_2015"),
    minecon_2013("Minecon_2013"),
    minecon_2012("Minecon_2012"),
    minecon_2011("Minecon_2011");

    public final String name;
    public final String resource;
    private static final Cape[] copy = values();

    private Cape(String name) {
        this.name = name;
        this.resource = name.toLowerCase().replace(" ", "_") + ".png";
    }

    public Cape next() {
        return copy[(this.ordinal() + 1) % copy.length];
    }

    public Cape prev() {
        return copy[this.ordinal() - 1 >= 0 ? this.ordinal() - 1 : copy.length - 1];
    }

    public static Cape getCape(String name) {
        for(Cape cape : copy) {
            if (cape.resource.equals(name)) {
                return cape;
            }
        }

        return minecon_2011;
    }
}