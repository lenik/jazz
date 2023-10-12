package net.bodz.bas.doc.node.util;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.ui.css3.property.ListStyleTypeMode;
import net.bodz.bas.ui.css3.property.util.Numbering;

public class Css3ListStyle
        extends AbstractListStyle {

    final ListStyleTypeMode mode;

    public Css3ListStyle(ListStyleTypeMode mode) {
        if (mode == null)
            throw new NullPointerException("mode");
        this.mode = mode;
    }

    public static Css3ListStyle valueOf(ListStyleTypeMode mode) {
        return new Css3ListStyle(mode);
    }

    @Override
    public boolean isCss3() {
        return true;
    }

    public ListStyleTypeMode getMode() {
        return mode;
    }

    @Override
    public String format(int number, int max) {
        switch (mode) {
        case none:
            return " ";

        case disc:
            return "•";
        case circle:
            return "●";
        case square:
            return "▪";

        // "‣"

        /** Decimal numbers: beginning with 1. */
        case decimal:
            return String.valueOf(number);

        /** Decimal numbers padded by initial zeros (e.g.: 01: 02: 03: ...: 98: 99). */
        case decimal_leading_zero:
            int w = String.valueOf(max).length();
            String num = String.valueOf(number);
            if (num.length() < w)
                num = Strings.repeat(w - num.length(), '0') + num;
            return num;

        /** Lowercase roman numerals (i: ii: iii: iv: v: etc.). */
        case lower_roman:
            return Numbering.toRomanNumeral(number).toLowerCase();

        /** Uppercase roman numerals (I: II: III: IV: V: etc.). */
        case upper_roman:
            return Numbering.toRomanNumeral(number);

        /** Traditional Georgian numbering (an: ban: gan: ...: he: tan: in: in-an: ...). */
        case georgian:

            /** Traditional uppercase Armenian numbering. */
        case armenian:
            throw new UnsupportedOperationException();

        /** Lowercase ascii letters (a: b: c: ... z). */
        case lower_latin:
        case lower_alpha:
            return Numbering.toLowerLatin(number);

        /** Uppercase ascii letters (A: B: C: ... Z). */
        case upper_latin:
        case upper_alpha:
            return Numbering.toUpperLatin(number);

        /** Lowercase classical Greek alpha: beta: gamma: ... (α: β: γ: ...) */
        case lower_greek:
            return Numbering.toLowerGreek(number);
        }
        throw new UnsupportedOperationException("not implemented: " + this);
    }

    @Override
    public boolean isOrdered() {
        switch (mode) {
        case decimal:
        case decimal_leading_zero:
        case lower_roman:
        case upper_roman:
        case georgian:
        case armenian:
        case lower_latin:
        case lower_alpha:
        case upper_latin:
        case upper_alpha:
        case lower_greek:
            return true;
        default:
            return false;
        }
    }

}
