package com.vibe.periodictable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ElementTable {

    static final int CAT_ALKALI = 0;
    static final int CAT_ALKALINE_EARTH = 1;
    static final int CAT_TRANSITION = 2;
    static final int CAT_POST_TRANSITION = 3;
    static final int CAT_METALLOID = 4;
    static final int CAT_NONMETAL = 5;
    static final int CAT_HALOGEN = 6;
    static final int CAT_NOBLE = 7;
    static final int CAT_LANTHANIDE = 8;
    static final int CAT_ACTINIDE = 9;

    static final String[] CATEGORY_NAMES = {
            "Alkali metal",
            "Toprak alkali metal",
            "Geçiş metali",
            "Geçiş sonrası metal",
            "Yarı metal (metaloit)",
            "Ametal (metal olmayan)",
            "Halojen",
            "Soy gaz",
            "Lantanit",
            "Aktinit"
    };

    static final String[] CATEGORY_NAMES_EN = {
            "Alkali metal",
            "Alkaline earth metal",
            "Transition metal",
            "Post-transition metal",
            "Metalloid",
            "Nonmetal",
            "Halogen",
            "Noble gas",
            "Lanthanide",
            "Actinide"
    };

    static final int[] CATEGORY_COLORS = {
            0xFFEF5350,
            0xFFFFB74D,
            0xFF64B5F6,
            0xFFA1887F,
            0xFFAED581,
            0xFFFFF176,
            0xFFFFD54F,
            0xFFB39DDB,
            0xFFF48FB1,
            0xFFCE93D8
    };

    static final String[] SYMBOLS = {
            "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne",
            "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca",
            "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn",
            "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr",
            "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn",
            "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
            "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb",
            "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg",
            "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th",
            "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm",
            "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds",
            "Rg", "Cn", "Nh", "Fl", "Mc", "Lv", "Ts", "Og"
    };

    static final String[] NAMES_TR = {
            "Hidrojen", "Helyum", "Lityum", "Berilyum", "Bor", "Karbon",
            "Azot", "Oksijen", "Fluor", "Neon",
            "Sodyum", "Magnezyum", "Alüminyum", "Silisyum", "Fosfor", "Kükürt",
            "Klor", "Argon", "Potasyum", "Kalsiyum",
            "Skandiyum", "Titan", "Vanadyum", "Krom", "Mangan", "Demir",
            "Kobalt", "Nikel", "Bakır", "Çinko",
            "Galyum", "Germanyum", "Arsenik", "Selen", "Brom", "Kripton",
            "Rubidyum", "Stronsiyum", "İtriyum", "Zirkonyum",
            "Niobyum", "Molibden", "Teknesyum", "Rutenyum", "Rodiyum", "Paladyum",
            "Gümüş", "Kadmiyum", "İndiyum", "Kalay",
            "Antimon", "Tellür", "İyot", "Ksenon", "Sezyum", "Baryum",
            "Lantan", "Seryum", "Prazneodyum", "Neodimyum",
            "Prometyum", "Samaryum", "Evropyum", "Gadolinyum", "Terbiyum", "Disprosyum",
            "Holmiyum", "Erbiyum", "Tulyum", "İterbiyum",
            "Lütesiyum", "Hafniyum", "Tantal", "Volfram", "Renyum", "Osmiyum",
            "İridyum", "Platin", "Altın", "Cıva",
            "Talyum", "Kurşun", "Bizmut", "Polonyum", "Astatin", "Radon",
            "Fransiyum", "Radyum", "Aktinyum", "Toriyum",
            "Protaktinyum", "Uranyum", "Neptünyum", "Plütonyum", "Amerisyum", "Kuriyum",
            "Berkelyum", "Kaliforniyum", "Aynştaynyum", "Fermiyum",
            "Mendelevyum", "Nobelyum", "Lavrensiyum", "Ruterfordiyum", "Dubniyum", "Seaborgiyum",
            "Bohriyum", "Hasyum", "Meitneriyum", "Darmştadyum",
            "Röntgenyum", "Kopernisyum", "Nihonyum", "Flerovyum", "Moskoviyum", "Livermoryum",
            "Tennessin", "Oganeson"
    };

    static final String[] NAMES_EN = {
            "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon",
            "Nitrogen", "Oxygen", "Fluorine", "Neon",
            "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur",
            "Chlorine", "Argon", "Potassium", "Calcium",
            "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron",
            "Cobalt", "Nickel", "Copper", "Zinc",
            "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton",
            "Rubidium", "Strontium", "Yttrium", "Zirconium",
            "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium",
            "Silver", "Cadmium", "Indium", "Tin",
            "Antimony", "Tellurium", "Iodine", "Xenon", "Caesium", "Barium",
            "Lanthanum", "Cerium", "Praseodymium", "Neodymium",
            "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium",
            "Holmium", "Erbium", "Thulium", "Ytterbium",
            "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium",
            "Iridium", "Platinum", "Gold", "Mercury",
            "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon",
            "Francium", "Radium", "Actinium", "Thorium",
            "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium",
            "Berkelium", "Californium", "Einsteinium", "Fermium",
            "Mendelevium", "Nobelium", "Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium",
            "Bohrium", "Hassium", "Meitnerium", "Darmstadtium",
            "Roentgenium", "Copernicium", "Nihonium", "Flerovium", "Moscovium", "Livermorium",
            "Tennessine", "Oganesson"
    };

    static final String[] MASSES = {
            "1,008", "4,0026", "6,94", "9,0122", "10,81", "12,011",
            "14,007", "15,999", "18,998", "20,180",
            "22,990", "24,305", "26,982", "28,085", "30,974", "32,06",
            "35,45", "39,95", "39,098", "40,078",
            "44,956", "47,867", "50,942", "51,996", "54,938", "55,845",
            "58,933", "58,693", "63,546", "65,38",
            "69,723", "72,630", "74,922", "78,971", "79,904", "83,798",
            "85,468", "87,62", "88,906", "91,224",
            "92,906", "95,95", "[98]", "101,07", "102,91", "106,42",
            "107,87", "112,41", "114,82", "118,71",
            "121,76", "127,60", "126,90", "131,29", "132,91", "137,33",
            "138,91", "140,12", "140,91", "144,24",
            "[145]", "150,36", "151,96", "157,25", "158,93", "162,50",
            "164,93", "167,26", "168,93", "173,05",
            "174,97", "178,49", "180,95", "183,84", "186,21", "190,23",
            "192,22", "195,08", "196,97", "200,59",
            "204,38", "207,2", "208,98", "[209]", "[210]", "[222]",
            "[223]", "[226]", "[227]", "232,04",
            "231,04", "238,03", "[237]", "[244]", "[243]", "[247]",
            "[247]", "[251]", "[252]", "[257]",
            "[258]", "[259]", "[266]", "[267]", "[268]", "[269]",
            "[270]", "[277]", "[278]", "[281]",
            "[282]", "[285]", "[286]", "[289]", "[290]", "[293]",
            "[294]", "[294]"
    };

    static final int[] CATEGORIES = {
            CAT_NONMETAL, CAT_NOBLE, CAT_ALKALI, CAT_ALKALINE_EARTH, CAT_METALLOID, CAT_NONMETAL,
            CAT_NONMETAL, CAT_NONMETAL, CAT_HALOGEN, CAT_NOBLE,
            CAT_ALKALI, CAT_ALKALINE_EARTH, CAT_POST_TRANSITION, CAT_METALLOID, CAT_NONMETAL, CAT_NONMETAL,
            CAT_HALOGEN, CAT_NOBLE, CAT_ALKALI, CAT_ALKALINE_EARTH,
            CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_POST_TRANSITION, CAT_METALLOID, CAT_METALLOID, CAT_NONMETAL, CAT_HALOGEN, CAT_NOBLE,
            CAT_ALKALI, CAT_ALKALINE_EARTH, CAT_TRANSITION, CAT_TRANSITION,
            CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_TRANSITION, CAT_TRANSITION, CAT_POST_TRANSITION, CAT_POST_TRANSITION,
            CAT_METALLOID, CAT_METALLOID, CAT_HALOGEN, CAT_NOBLE, CAT_ALKALI, CAT_ALKALINE_EARTH,
            CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE,
            CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE,
            CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE, CAT_LANTHANIDE,
            CAT_LANTHANIDE, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_POST_TRANSITION, CAT_POST_TRANSITION, CAT_POST_TRANSITION, CAT_POST_TRANSITION, CAT_HALOGEN, CAT_NOBLE,
            CAT_ALKALI, CAT_ALKALINE_EARTH, CAT_ACTINIDE, CAT_ACTINIDE,
            CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE,
            CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE,
            CAT_ACTINIDE, CAT_ACTINIDE, CAT_ACTINIDE, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION, CAT_TRANSITION,
            CAT_TRANSITION, CAT_TRANSITION, CAT_POST_TRANSITION, CAT_POST_TRANSITION, CAT_POST_TRANSITION, CAT_POST_TRANSITION,
            CAT_HALOGEN, CAT_NOBLE
    };

    private static final int[] ROWS = {
            1, 1,
            2, 2, 2, 2, 2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            6, 6, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            7, 7, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7
    };

    private static final int[] COLS = {
            1, 18,
            1, 2, 13, 14, 15, 16, 17, 18,
            1, 2, 13, 14, 15, 16, 17, 18,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
            1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
            1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
    };

    private static final String[] AUFBAU_ORDER = {
            "1s", "2s", "2p", "3s", "3p", "4s", "3d", "4p", "5s", "4d",
            "5p", "6s", "4f", "5d", "6p", "7s", "5f", "6d", "7p"
    };

    private static final int[] AUFBAU_CAPACITY = {
            2, 2, 6, 2, 6, 2, 10, 6, 2, 10,
            6, 2, 14, 10, 6, 2, 14, 10, 6
    };

    private static final int[] NOBLE_Z = {2, 10, 18, 36, 54, 86};
    private static final String[] NOBLE_SYMBOL = {"He", "Ne", "Ar", "Kr", "Xe", "Rn"};

    private static final Map<Integer, String> CONFIG_EXCEPTIONS = new HashMap<Integer, String>();

    static {
        CONFIG_EXCEPTIONS.put(24, "3d⁵4s¹");
        CONFIG_EXCEPTIONS.put(29, "3d¹⁰4s¹");
        CONFIG_EXCEPTIONS.put(41, "4d⁴5s¹");
        CONFIG_EXCEPTIONS.put(42, "4d⁵5s¹");
        CONFIG_EXCEPTIONS.put(44, "4d⁷5s¹");
        CONFIG_EXCEPTIONS.put(45, "4d⁸5s¹");
        CONFIG_EXCEPTIONS.put(46, "4d¹⁰");
        CONFIG_EXCEPTIONS.put(47, "4d¹⁰5s¹");
        CONFIG_EXCEPTIONS.put(57, "5d¹6s²");
        CONFIG_EXCEPTIONS.put(58, "4f¹5d¹6s²");
        CONFIG_EXCEPTIONS.put(64, "4f⁷5d¹6s²");
        CONFIG_EXCEPTIONS.put(78, "4f¹⁴5d⁹6s¹");
        CONFIG_EXCEPTIONS.put(79, "4f¹⁴5d¹⁰6s¹");
        CONFIG_EXCEPTIONS.put(90, "6d²7s²");
        CONFIG_EXCEPTIONS.put(91, "5f²6d¹7s²");
        CONFIG_EXCEPTIONS.put(92, "5f³6d¹7s²");
        CONFIG_EXCEPTIONS.put(93, "5f⁴6d¹7s²");
        CONFIG_EXCEPTIONS.put(96, "5f⁷6d¹7s²");
        CONFIG_EXCEPTIONS.put(103, "5f¹⁴7s²7p¹");
    }

    private ElementTable() {
    }

    static int count() {
        return 118;
    }

    static int row(int z) {
        return ROWS[z - 1];
    }

    static int col(int z) {
        return COLS[z - 1];
    }

    static int color(int z) {
        return CATEGORY_COLORS[CATEGORIES[z - 1]];
    }

    static String category(int z) {
        return CATEGORY_NAMES[CATEGORIES[z - 1]];
    }

    static int period(int z) {
        int r = row(z);
        if (r == 9) return 6;
        if (r == 10) return 7;
        return r;
    }

    static int group(int z) {
        int r = row(z);
        if (r == 9 || r == 10) return 3;
        return col(z);
    }

    static String electronConfig(int z) {
        int[] counts = new int[AUFBAU_ORDER.length];
        int remaining = z;
        for (int i = 0; i < AUFBAU_ORDER.length && remaining > 0; i++) {
            int take = Math.min(AUFBAU_CAPACITY[i], remaining);
            counts[i] = take;
            remaining -= take;
        }

        int coreZ = 0;
        String coreSymbol = null;
        int cumulative = 0;
        for (int i = 0; i < AUFBAU_ORDER.length; i++) {
            cumulative += counts[i];
            for (int n = NOBLE_Z.length - 1; n >= 0; n--) {
                if (NOBLE_Z[n] == cumulative && cumulative < z) {
                    coreZ = cumulative;
                    coreSymbol = NOBLE_SYMBOL[n];
                }
            }
        }

        String tail;
        String custom = CONFIG_EXCEPTIONS.get(z);
        if (custom != null) {
            tail = custom;
        } else {
            List<int[]> kept = new ArrayList<int[]>();
            cumulative = 0;
            for (int i = 0; i < AUFBAU_ORDER.length; i++) {
                if ((coreZ == 0 || cumulative >= coreZ) && counts[i] > 0) {
                    int n = AUFBAU_ORDER[i].charAt(0) - '0';
                    char l = AUFBAU_ORDER[i].charAt(1);
                    kept.add(new int[]{n, l, counts[i]});
                }
                cumulative += counts[i];
            }
            sortSubshells(kept);
            StringBuilder sb = new StringBuilder();
            for (int[] s : kept) {
                sb.append(s[0]).append((char) s[1]).append(superscript(s[2]));
            }
            tail = sb.toString();
        }

        if (coreSymbol == null) return tail;
        return "[" + coreSymbol + "] " + tail;
    }

    private static void sortSubshells(List<int[]> kept) {
        for (int i = 1; i < kept.size(); i++) {
            int[] key = kept.get(i);
            int j = i - 1;
            while (j >= 0 && compareSubshell(kept.get(j), key) > 0) {
                kept.set(j + 1, kept.get(j));
                j--;
            }
            kept.set(j + 1, key);
        }
    }

    private static int compareSubshell(int[] a, int[] b) {
        if (a[0] != b[0]) return a[0] - b[0];
        return letterRank((char) a[1]) - letterRank((char) b[1]);
    }

    private static int letterRank(char c) {
        switch (c) {
            case 's': return 0;
            case 'p': return 1;
            case 'd': return 2;
            default: return 3;
        }
    }

    private static String superscript(int n) {
        String digits = Integer.toString(n);
        String sup = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length(); i++) {
            sb.append(sup.charAt(digits.charAt(i) - '0'));
        }
        return sb.toString();
    }
}
