package com.vibe.periodictable;

import android.content.Context;

public final class L10n {

    private static Boolean english;

    private L10n() {
    }

    static boolean isEnglish(Context c) {
        if (english == null) {
            english = c.getSharedPreferences("app", Context.MODE_PRIVATE).getBoolean("english", false);
        }
        return english;
    }

    static void setEnglish(Context c, boolean value) {
        english = value;
        c.getSharedPreferences("app", Context.MODE_PRIVATE)
                .edit().putBoolean("english", value).apply();
    }

    static String pick(Context c, String tr, String en) {
        return isEnglish(c) ? en : tr;
    }

    static String elementName(Context c, int z) {
        return pick(c, ElementTable.NAMES_TR[z - 1], ElementTable.NAMES_EN[z - 1]);
    }

    static String categoryName(Context c, int index) {
        return pick(c, ElementTable.CATEGORY_NAMES[index], ElementTable.CATEGORY_NAMES_EN[index]);
    }

    static String compoundName(Context c, int index) {
        return pick(c, Compounds.NAMES[index], Compounds.NAMES_EN[index]);
    }

    static String elementCategory(Context c, int z) {
        return categoryName(c, ElementTable.CATEGORIES[z - 1]);
    }
}
