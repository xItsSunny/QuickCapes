package com.quickcapes.cape;

public enum Cape {

    // Account-related capes
    PAN("Pan Cape", "Pan_Cape.png"),
    MIGRATOR("Migrator Cape", "Migrator_Cape.png"),
    VANILLA("Vanilla Cape", "Vanilla_Cape.png"),
    COMMON("Common Cape", "Common_Cape.png"),

    // Staff capes
    CLASSIC_MOJANG("Classic Mojang Cape", "Classic_Mojang_Cape.png"),
    MICROSOFT_XBOX("Microsoft Xbox Cape", "Microsoft_Xbox_Cape.png"),
    FOUR_J_STUDIOS("4J Studios Cape", "4J_Studios_Cape.png"),
    MOJANG("Mojang Cape", "Mojang_Cape.png"),
    MOJANG_STUDIOS("Mojang Studios Cape", "Mojang_Studios_Cape.png"),

    // Physical event capes
    MINECON_2011("MINECON 2011 Cape", "MINECON_2011_Cape.png"),
    MINECON_2012("MINECON 2012 Cape", "MINECON_2012_Cape.png"),
    MINECON_2013("MINECON 2013 Cape", "MINECON_2013_Cape.png"),
    MINECON_2015("MINECON 2015 Cape", "MINECON_2015_Cape.png"),
    MINECON_2016("MINECON 2016 Cape", "MINECON_2016_Cape.png"),
    MINECRAFT_EXPERIENCE("Minecraft Experience Cape", "Minecraft_Experience_Cape.png"),
    MOONLIGHT_TRAIL("Moonlight Trail Cape", "Moonlight_Trail_Cape.png"),
    CRAFTER("Crafter Cape", "Crafter_Cape.png"),

    // Virtual event capes
    FOUNDERS("Founder's Cape", "Founder's_Cape.png"),
    PROGRESS_PRIDE("Progress Pride Cape", "Progress_Pride_Cape.png"),
    CHERRY_BLOSSOM("Cherry Blossom Cape", "Cherry_Blossom_Cape.png"),
    FOLLOWERS("Follower's Cape", "Follower's_Cape.png"),
    PURPLE_HEART("Purple Heart Cape", "Purple_Heart_Cape.png"),
    FIFTEEN_ANNIVERSARY("15th Anniversary Cape", "15th_Anniversary_Cape.png"),
    MCC_15TH_YEAR("MCC 15th Year Cape", "MCC_15th_Year_Cape.png"),
    MOJANG_OFFICE("Mojang Office Cape", "Mojang_Office_Cape.png"),
    HOME("Home Cape", "Home_Cape.png"),
    MENACE("Menace Cape", "Menace_Cape.png"),
    YEARN("Yearn Cape", "Yearn_Cape.png"),
    COPPER("Copper Cape", "Copper_Cape.png"),
    ZOMBIE_HORSE("Zombie Horse Cape", "Zombie_Horse_Cape.png"),
    BUILDER("Builder Cape", "Builder_Cape.png"),

    // Personal capes
    BACON("Bacon Cape", "Bacon_Cape.png"),
    MILLIONTH_CUSTOMER("Millionth Customer Cape", "Millionth_Customer_Cape.png"),
    DB("dB Cape", "dB_Cape.png"),
    SNOWMAN("Snowman Cape", "Snowman_Cape.png"),
    CHEAPSH0T("Cheapsh0t's Cape", "Cheapsh0t's_Cape.png"),
    SPADE("Spade Cape", "Spade_Cape.png"),
    PRISMARINE("Prismarine Cape", "Prismarine_Cape.png"),
    TURTLE("Turtle Cape", "Turtle_Cape.png"),
    BIRTHDAY("Birthday Cape", "Birthday_Cape.png"),
    VALENTINE("Valentine Cape", "Valentine_Cape.png"),
    OXEYE("Oxeye Cape", "Oxeye_Cape.png"),
    BLUEPRINT("Blueprint Cape", "Blueprint_Cape.png"),

    // Competition capes
    SCROLLS_CHAMPION("Scrolls Champion Cape", "Scrolls_Champion_Cape.png"),
    COBALT("Cobalt Cape", "Cobalt_Cape.png"),

    // Volunteer capes
    TRANSLATOR("Translator Cape", "Translator_Cape.png"),
    CHINESE_TRANSLATOR("Chinese Translator Cape", "Chinese_Translator_Cape.png"),
    MODERATOR("Moderator Cape", "Moderator_Cape.png"),
    REALMS_MAPMAKER("Realms MapMaker Cape", "Realms_MapMaker_Cape.png"),

    // Temporary capes
    CHRISTMAS_2010("Christmas 2010 Cape", "Christmas_2010_Cape.png"),
    NEW_YEAR_2011("New Year 2011 Cape", "New_Year_2011_Cape.png"),
    XBOX_1ST_BIRTHDAY("Xbox 1st Birthday Cape", "Xbox_1st_Birthday_Cape.png"),

    // API testing capes
    SIZE_M("size-m cape", "size-m_cape.png"),
    SNAIL("Snail cape", "Snail_cape.png"),
    ICU_1("ICU cape #1", "ICU_cape_#1.png"),
    ICU_2("ICU cape #2", "ICU_cape_#2.png"),
    FROG("Frog cape", "Frog_cape.png"),

    // Vote Update capes
    AWESOM("Awesom caep", "Awesom_caep.png"),
    BLONK("Blonk caep", "Blonk_caep.png"),
    NO_CIRCLE("No-Circle caep", "No-Circle_caep.png"),
    NYAN("Nyan caep", "Nyan_caep.png"),
    SQUID("Squid caep", "Squid_caep.png"),
    VETERINARIAN("Veterinarian caep", "Veterinarian_caep.png"),

    // Unused capes
    UNUSED_1("Unused #1 cape", "Unused_#1_cape.png"),
    UNUSED_2("Unused #2 cape", "Unused_#2_cape.png"),
    UNUSED_3("Unused #3 cape", "Unused_#3_cape.png"),
    UNUSED_4("Unused #4 cape", "Unused_#4_cape.png"),
    UNUSED_5("Unused #5 cape", "Unused_#5_cape.png"),
    UNUSED_6("Unused #6 cape", "Unused_#6_cape.png"),
    UNUSED_7("Unused #7 cape", "Unused_#7_cape.png"),
    UNUSED_8("Unused #8 cape", "Unused_#8_cape.png"),
    UNUSED_9("Unused #9 cape", "Unused_#9_cape.png"),
    UNUSED_10("Unused #10 cape", "Unused_#10_cape.png"),
    UNUSED_11("Unused #11 cape", "Unused_#11_cape.png"),
    UNUSED_12("Unused #12 cape", "Unused_#12_cape.png"),
    UNUSED_13("Unused #13 cape", "Unused_#13_cape.png"),
    UNUSED_14("Unused #14 cape", "Unused_#14_cape.png");

    public final String name;
    public final String resource;

    private static final Cape[] copy = values();

    private Cape(String name, String resource) {
        this.name = name;
        this.resource = resource;
    }

    public Cape next() {
        return copy[(this.ordinal() + 1) % copy.length];
    }

    public Cape prev() {
        return copy[this.ordinal() - 1 >= 0 ? this.ordinal() - 1 : copy.length - 1];
    }

    public static Cape getCape(String name) {
        for (Cape cape : copy) {
            if (cape.resource.equals(name)) {
                return cape;
            }
        }

        return MINECON_2011;
    }
}