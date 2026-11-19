package com.vibe.periodictable;

import java.util.ArrayList;
import java.util.Arrays;

public final class Compounds {

    static final String[] NAMES = {
            "Su", "Tuz", "Amonyak", "Metan", "Karbon dioksit", "Karbon monoksit",
            "Oksijen gazı", "Azot gazı", "Hidrojen gazı", "Klor gazı",
            "Hidrojen klorür", "Hidrojen peroksit", "Kokarca gazı", "Kükürt dioksit", "Kükürt trioksit",
            "Azot monoksit", "Azot dioksit", "Güldürücü gaz", "Fosfin", "Silan",
            "Sülfürik asit", "Nitrik asit", "Karbonik asit", "Hidroflorik asit", "Hidrobromik asit",
            "Hidroiyodik asit", "Fosforik asit", "Borik asit", "Sirke asidi", "Kostik soda",
            "Potasyum hidroksit", "Kireç suyu", "Süt alkali", "Alüminyum hidroksit", "Demir(III) hidroksit",
            "Amonyum hidroksit", "Yanmış kireç", "Magnezyum oksit", "Pas", "Manyetit",
            "Alüminyum oksit", "Bakır(II) oksit", "Bakır(I) oksit", "Çinko oksit", "Beyaz boya",
            "Kum (silis)", "Manganez dioksit", "Krom(III) oksit", "Nikel(II) oksit", "Kurşun(II) oksit",
            "Cıva(II) oksit", "Kireç taşı", "Kabartma tozu", "Çamaşır sodası", "Potasyum karbonat",
            "Magnezyum karbonat", "Üre", "Alçı taşı", "Epsom tuzu", "Gök mavi",
            "Yeşil göztaşı", "Çinko sülfat", "Sodyum sülfat", "Barit", "Kurşun sülfat",
            "Şap", "Güherçile", "Şili güherçilesi", "Amonyum nitrat", "Gümüş nitrat",
            "Kalsiyum nitrat", "Kurşun nitrat", "Fluorit", "Sodyum florür", "Lityum florür",
            "Potasyum klorür", "Kalsiyum klorür", "Magnezyum klorür", "Baryum klorür", "Bakır klorür",
            "Demir(III) klorür", "Gümüş klorür", "Gümüş bromür", "Gümüş iyodür", "Potasyum iyodür",
            "Potasyum bromür", "Sodyum iyodür", "Çamaşır suyu", "Potasyum permanganat", "Kalsiyum hipoklorit",
            "Hidrazin", "Asetilen", "Olgunlaştırma gazı", "Etan", "Propan",
            "Taş ruhu", "Formaldehit", "Aseton", "Antifriz", "Volfram karbür"
    };

    static final String[] FORMULAS = {
            "H2O", "NaCl", "NH3", "CH4", "CO2", "CO",
            "O2", "N2", "H2", "Cl2",
            "HCl", "H2O2", "H2S", "SO2", "SO3",
            "NO", "NO2", "N2O", "PH3", "SiH4",
            "H2SO4", "HNO3", "H2CO3", "HF", "HBr",
            "HI", "H3PO4", "H3BO3", "CH3COOH", "NaOH",
            "KOH", "Ca(OH)2", "Mg(OH)2", "Al(OH)3", "Fe(OH)3",
            "NH4OH", "CaO", "MgO", "Fe2O3", "Fe3O4",
            "Al2O3", "CuO", "Cu2O", "ZnO", "TiO2",
            "SiO2", "MnO2", "Cr2O3", "NiO", "PbO",
            "HgO", "CaCO3", "NaHCO3", "Na2CO3", "K2CO3",
            "MgCO3", "CH4N2O", "CaSO4", "MgSO4", "CuSO4",
            "FeSO4", "ZnSO4", "Na2SO4", "BaSO4", "PbSO4",
            "KAl(SO4)2", "KNO3", "NaNO3", "NH4NO3", "AgNO3",
            "Ca(NO3)2", "Pb(NO3)2", "CaF2", "NaF", "LiF",
            "KCl", "CaCl2", "MgCl2", "BaCl2", "CuCl2",
            "FeCl3", "AgCl", "AgBr", "AgI", "KI",
            "KBr", "NaI", "NaClO", "KMnO4", "Ca(ClO)2",
            "N2H4", "C2H2", "C2H4", "C2H6", "C3H8",
            "CH3OH", "CH2O", "C3H6O", "C2H4(OH)2", "WC"
    };

    static final String[] NAMES_EN = {
            "Water", "Salt", "Ammonia", "Methane", "Carbon dioxide", "Carbon monoxide",
            "Oxygen gas", "Nitrogen gas", "Hydrogen gas", "Chlorine gas",
            "Hydrogen chloride", "Hydrogen peroxide", "Hydrogen sulfide", "Sulfur dioxide", "Sulfur trioxide",
            "Nitrogen monoxide", "Nitrogen dioxide", "Laughing gas", "Phosphine", "Silane",
            "Sulfuric acid", "Nitric acid", "Carbonic acid", "Hydrofluoric acid", "Hydrobromic acid",
            "Hydroiodic acid", "Phosphoric acid", "Boric acid", "Acetic acid (vinegar)", "Caustic soda",
            "Potassium hydroxide", "Limewater", "Milk of magnesia", "Aluminium hydroxide", "Iron(III) hydroxide",
            "Ammonium hydroxide", "Quicklime", "Magnesium oxide", "Rust", "Magnetite",
            "Alumina", "Copper(II) oxide", "Copper(I) oxide", "Zinc oxide", "Titanium white",
            "Sand (silica)", "Manganese dioxide", "Chromium(III) oxide", "Nickel(II) oxide", "Lead(II) oxide",
            "Mercury(II) oxide", "Limestone", "Baking soda", "Washing soda", "Potassium carbonate",
            "Magnesium carbonate", "Urea", "Gypsum", "Epsom salt", "Blue vitriol",
            "Green vitriol", "Zinc sulfate", "Glauber's salt", "Barite", "Lead sulfate",
            "Alum", "Saltpeter", "Chile saltpeter", "Ammonium nitrate", "Silver nitrate",
            "Calcium nitrate", "Lead nitrate", "Fluorite", "Sodium fluoride", "Lithium fluoride",
            "Potassium chloride", "Calcium chloride", "Magnesium chloride", "Barium chloride", "Copper chloride",
            "Iron(III) chloride", "Silver chloride", "Silver bromide", "Silver iodide", "Potassium iodide",
            "Potassium bromide", "Sodium iodide", "Bleach", "Potassium permanganate", "Calcium hypochlorite",
            "Hydrazine", "Acetylene", "Ethylene (ripening gas)", "Ethane", "Propane",
            "Methanol (wood spirit)", "Formaldehyde", "Acetone", "Antifreeze", "Tungsten carbide"
    };

    private Compounds() {
    }

    static int count() {
        return NAMES.length;
    }

    static int[] composition(int index) {
        int[] parsed = parse(FORMULAS[index]);
        return Arrays.copyOf(parsed, parsed.length);
    }

    static ArrayList<Integer> sequence(int index) {
        ArrayList<ArrayList<Integer>> stack = new ArrayList<ArrayList<Integer>>();
        stack.add(new ArrayList<Integer>());
        String f = FORMULAS[index];
        int i = 0;
        while (i < f.length()) {
            char c = f.charAt(i);
            if (c == '(') {
                stack.add(new ArrayList<Integer>());
                i++;
            } else if (c == ')') {
                i++;
                int[] num = readNumber(f, i);
                i = num[1];
                int mult = num[0] == 0 ? 1 : num[0];
                ArrayList<Integer> inner = stack.remove(stack.size() - 1);
                for (int m = 0; m < mult; m++) {
                    stack.get(stack.size() - 1).addAll(inner);
                }
            } else {
                int z = readSymbol(f, i);
                String sym = ElementTable.SYMBOLS[z - 1];
                i += sym.length();
                int[] num = readNumber(f, i);
                i = num[1];
                int mult = num[0] == 0 ? 1 : num[0];
                for (int m = 0; m < mult; m++) {
                    stack.get(stack.size() - 1).add(z);
                }
            }
        }
        return stack.get(0);
    }

    static int[] parse(String formula) {
        int[][] stack = new int[8][119];
        int depth = 0;
        int[] current = stack[0];
        int i = 0;
        int len = formula.length();
        while (i < len) {
            char c = formula.charAt(i);
            if (c == '(') {
                depth++;
                current = stack[depth];
                i++;
            } else if (c == ')') {
                int[] inner = stack[depth];
                depth--;
                current = stack[depth];
                i++;
                int[] num = readNumber(formula, i);
                i = num[1];
                int mult = num[0] == 0 ? 1 : num[0];
                for (int z = 1; z <= 118; z++) {
                    current[z] += inner[z] * mult;
                }
            } else {
                int z = readSymbol(formula, i);
                String sym = ElementTable.SYMBOLS[z - 1];
                i += sym.length();
                int[] num = readNumber(formula, i);
                i = num[1];
                current[z] += num[0] == 0 ? 1 : num[0];
            }
        }
        return current;
    }

    private static int readSymbol(String f, int start) {
        int len = f.length();
        int j = start + 1;
        while (j < len && Character.isLowerCase(f.charAt(j)) && j - start <= 2) j++;
        String sym = f.substring(start, j);
        for (int z = 1; z <= ElementTable.count(); z++) {
            if (ElementTable.SYMBOLS[z - 1].equals(sym)) return z;
        }
        throw new IllegalArgumentException("Bilinmeyen sembol: " + sym);
    }

    private static int[] readNumber(String f, int start) {
        int len = f.length();
        int j = start;
        while (j < len && Character.isDigit(f.charAt(j))) j++;
        if (j == start) return new int[]{0, start};
        return new int[]{Integer.parseInt(f.substring(start, j)), j};
    }

    static String pretty(String formula) {
        String sub = "₀₁₂₃₄₅₆₇₈₉";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(sub.charAt(c - '0'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
