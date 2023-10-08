package net.bodz.bas.ui.css3.property.util;

public class Numbering {

    public static String toRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;
        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

    static final String latinLower = "abcdefghijklmnopqrstuvwxyz";
    static final String latinUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String greekLower = "αβγδεζηθικλμνξοπρστυφχψω";
    static final String greekUpper = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";

    public static String toLowerLatin(int input) {
        return toAlpha(input, latinLower);
    }

    public static String toUpperLatin(int input) {
        return toAlpha(input, latinUpper);
    }

    public static String toLowerGreek(int input) {
        return toAlpha(input, greekLower);
    }

    public static String toUpperGreek(int input) {
        return toAlpha(input, greekUpper);
    }

    public static String toAlpha(int input, String tab) {
        if (input < 1)
            throw new IllegalArgumentException("must be positive: " + input);
        input--;
        if (input == 0)
            return String.valueOf(tab.charAt(0));

        int n = tab.length();
        StringBuilder sb = new StringBuilder();
        while (input != 0) {
            int digit = input % n;
            input = input / n;
            char ch = tab.charAt(digit);
            sb.append(ch);
        }
        sb.reverse();
        return sb.toString();
    }

}
