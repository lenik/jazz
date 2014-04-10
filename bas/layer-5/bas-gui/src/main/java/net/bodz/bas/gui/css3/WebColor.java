package net.bodz.bas.gui.css3;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.style.color.RGB24Color;

public class WebColor {

    private static Map<String, RGB24Color> colorMap = new HashMap<>();

    private static RGB24Color create(String name, int red, int green, int blue) {
        RGB24Color color = new RGB24Color((byte) red, (byte) green, (byte) blue);
        colorMap.put(name, color);
        return color;
    }

    public static RGB24Color getColor(String name) {
        return colorMap.get(name);
    }

    public static RGB24Color parse(String s, RGB24Color fail) {
        return new WebColorParser().parse(s, fail);
    }

    public static RGB24Color parse(String s)
            throws ParseException {
        WebColorParser parser = new WebColorParser();
        RGB24Color color = parser.parse(s, null);
        if (color == null)
            throw new ParseException(parser.getMessage());
        return color;
    }

    public static final RGB24Color AliceBlue;
    public static final RGB24Color AntiqueWhite;
    public static final RGB24Color Aqua; // STD
    public static final RGB24Color Aquamarine;
    public static final RGB24Color Azure;
    public static final RGB24Color Beige;
    public static final RGB24Color Bisque;
    public static final RGB24Color Black; // STD
    public static final RGB24Color BlanchedAlmond;
    public static final RGB24Color Blue; // STD
    public static final RGB24Color BlueViolet;
    public static final RGB24Color Brown;
    public static final RGB24Color BurlyWood;
    public static final RGB24Color CadetBlue;
    public static final RGB24Color Chartreuse;
    public static final RGB24Color Chocolate;
    public static final RGB24Color Coral;
    public static final RGB24Color CornflowerBlue;
    public static final RGB24Color Cornsilk;
    public static final RGB24Color Crimson;
    public static final RGB24Color Cyan;
    public static final RGB24Color DarkBlue;
    public static final RGB24Color DarkCyan;
    public static final RGB24Color DarkGoldenRod;
    public static final RGB24Color DarkGray;
    public static final RGB24Color DarkGreen;
    public static final RGB24Color DarkKhaki;
    public static final RGB24Color DarkMagenta;
    public static final RGB24Color DarkOliveGreen;
    public static final RGB24Color DarkOrange;
    public static final RGB24Color DarkOrchid;
    public static final RGB24Color DarkRed;
    public static final RGB24Color DarkSalmon;
    public static final RGB24Color DarkSeaGreen;
    public static final RGB24Color DarkSlateBlue;
    public static final RGB24Color DarkSlateGray;
    public static final RGB24Color DarkTurquoise;
    public static final RGB24Color DarkViolet;
    public static final RGB24Color DeepPink;
    public static final RGB24Color DeepSkyBlue;
    public static final RGB24Color DimGray;
    public static final RGB24Color DodgerBlue;
    public static final RGB24Color FireBrick;
    public static final RGB24Color FloralWhite;
    public static final RGB24Color ForestGreen;
    public static final RGB24Color Fuchsia; // STD
    public static final RGB24Color Gainsboro;
    public static final RGB24Color GhostWhite;
    public static final RGB24Color Gold;
    public static final RGB24Color GoldenRod;
    public static final RGB24Color Gray; // STD
    public static final RGB24Color Green; // STD
    public static final RGB24Color GreenYellow;
    public static final RGB24Color HoneyDew;
    public static final RGB24Color HotPink;
    public static final RGB24Color IndianRed;
    public static final RGB24Color Indigo;
    public static final RGB24Color Ivory;
    public static final RGB24Color Khaki;
    public static final RGB24Color Lavender;
    public static final RGB24Color LavenderBlush;
    public static final RGB24Color LawnGreen;
    public static final RGB24Color LemonChiffon;
    public static final RGB24Color LightBlue;
    public static final RGB24Color LightCoral;
    public static final RGB24Color LightCyan;
    public static final RGB24Color LightGoldenRodYellow;
    public static final RGB24Color LightGray;
    public static final RGB24Color LightGreen;
    public static final RGB24Color LightPink;
    public static final RGB24Color LightSalmon;
    public static final RGB24Color LightSeaGreen;
    public static final RGB24Color LightSkyBlue;
    public static final RGB24Color LightSlateGray;
    public static final RGB24Color LightSteelBlue;
    public static final RGB24Color LightYellow;
    public static final RGB24Color Lime; // STD
    public static final RGB24Color LimeGreen;
    public static final RGB24Color Linen;
    public static final RGB24Color Magenta;
    public static final RGB24Color Maroon; // STD
    public static final RGB24Color MediumAquaMarine;
    public static final RGB24Color MediumBlue;
    public static final RGB24Color MediumOrchid;
    public static final RGB24Color MediumPurple;
    public static final RGB24Color MediumSeaGreen;
    public static final RGB24Color MediumSlateBlue;
    public static final RGB24Color MediumSpringGreen;
    public static final RGB24Color MediumTurquoise;
    public static final RGB24Color MediumVioletRed;
    public static final RGB24Color MidnightBlue;
    public static final RGB24Color MintCream;
    public static final RGB24Color MistyRose;
    public static final RGB24Color Moccasin;
    public static final RGB24Color NavajoWhite;
    public static final RGB24Color Navy; // STD
    public static final RGB24Color OldLace;
    public static final RGB24Color Olive; // STD
    public static final RGB24Color OliveDrab;
    public static final RGB24Color Orange; // STD
    public static final RGB24Color OrangeRed;
    public static final RGB24Color Orchid;
    public static final RGB24Color PaleGoldenRod;
    public static final RGB24Color PaleGreen;
    public static final RGB24Color PaleTurquoise;
    public static final RGB24Color PaleVioletRed;
    public static final RGB24Color PapayaWhip;
    public static final RGB24Color PeachPuff;
    public static final RGB24Color Peru;
    public static final RGB24Color Pink;
    public static final RGB24Color Plum;
    public static final RGB24Color PowderBlue;
    public static final RGB24Color Purple; // STD
    public static final RGB24Color Red; // STD
    public static final RGB24Color RosyBrown;
    public static final RGB24Color RoyalBlue;
    public static final RGB24Color SaddleBrown;
    public static final RGB24Color Salmon;
    public static final RGB24Color SandyBrown;
    public static final RGB24Color SeaGreen;
    public static final RGB24Color SeaShell;
    public static final RGB24Color Sienna;
    public static final RGB24Color Silver; // STD
    public static final RGB24Color SkyBlue;
    public static final RGB24Color SlateBlue;
    public static final RGB24Color SlateGray;
    public static final RGB24Color Snow;
    public static final RGB24Color SpringGreen;
    public static final RGB24Color SteelBlue;
    public static final RGB24Color Tan;
    public static final RGB24Color Teal; // STD
    public static final RGB24Color Thistle;
    public static final RGB24Color Tomato;
    public static final RGB24Color Turquoise;
    public static final RGB24Color Violet;
    public static final RGB24Color Wheat;
    public static final RGB24Color White; // STD
    public static final RGB24Color WhiteSmoke;
    public static final RGB24Color Yellow; // STD
    public static final RGB24Color YellowGreen;

    static {
        AliceBlue = create("aliceblue", 0xF0, 0xF8, 0xFF);
        AntiqueWhite = create("antiquewhite", 0xFA, 0xEB, 0xD7);
        Aqua = create("aqua", 0x00, 0xFF, 0xFF);
        Aquamarine = create("aquamarine", 0x7F, 0xFF, 0xD4);
        Azure = create("azure", 0xF0, 0xFF, 0xFF);
        Beige = create("beige", 0xF5, 0xF5, 0xDC);
        Bisque = create("bisque", 0xFF, 0xE4, 0xC4);
        Black = create("black", 0x00, 0x00, 0x00);
        BlanchedAlmond = create("blanchedalmond", 0xFF, 0xEB, 0xCD);
        Blue = create("blue", 0x00, 0x00, 0xFF);
        BlueViolet = create("blueviolet", 0x8A, 0x2B, 0xE2);
        Brown = create("brown", 0xA5, 0x2A, 0x2A);
        BurlyWood = create("burlywood", 0xDE, 0xB8, 0x87);
        CadetBlue = create("cadetblue", 0x5F, 0x9E, 0xA0);
        Chartreuse = create("chartreuse", 0x7F, 0xFF, 0x00);
        Chocolate = create("chocolate", 0xD2, 0x69, 0x1E);
        Coral = create("coral", 0xFF, 0x7F, 0x50);
        CornflowerBlue = create("cornflowerblue", 0x64, 0x95, 0xED);
        Cornsilk = create("cornsilk", 0xFF, 0xF8, 0xDC);
        Crimson = create("crimson", 0xDC, 0x14, 0x3C);
        Cyan = create("cyan", 0x00, 0xFF, 0xFF);
        DarkBlue = create("darkblue", 0x00, 0x00, 0x8B);
        DarkCyan = create("darkcyan", 0x00, 0x8B, 0x8B);
        DarkGoldenRod = create("darkgoldenrod", 0xB8, 0x86, 0x0B);
        DarkGray = create("darkgray", 0xA9, 0xA9, 0xA9);
        DarkGreen = create("darkgreen", 0x00, 0x64, 0x00);
        DarkKhaki = create("darkkhaki", 0xBD, 0xB7, 0x6B);
        DarkMagenta = create("darkmagenta", 0x8B, 0x00, 0x8B);
        DarkOliveGreen = create("darkolivegreen", 0x55, 0x6B, 0x2F);
        DarkOrange = create("darkorange", 0xFF, 0x8C, 0x00);
        DarkOrchid = create("darkorchid", 0x99, 0x32, 0xCC);
        DarkRed = create("darkred", 0x8B, 0x00, 0x00);
        DarkSalmon = create("darksalmon", 0xE9, 0x96, 0x7A);
        DarkSeaGreen = create("darkseagreen", 0x8F, 0xBC, 0x8F);
        DarkSlateBlue = create("darkslateblue", 0x48, 0x3D, 0x8B);
        DarkSlateGray = create("darkslategray", 0x2F, 0x4F, 0x4F);
        DarkTurquoise = create("darkturquoise", 0x00, 0xCE, 0xD1);
        DarkViolet = create("darkviolet", 0x94, 0x00, 0xD3);
        DeepPink = create("deeppink", 0xFF, 0x14, 0x93);
        DeepSkyBlue = create("deepskyblue", 0x00, 0xBF, 0xFF);
        DimGray = create("dimgray", 0x69, 0x69, 0x69);
        DodgerBlue = create("dodgerblue", 0x1E, 0x90, 0xFF);
        FireBrick = create("firebrick", 0xB2, 0x22, 0x22);
        FloralWhite = create("floralwhite", 0xFF, 0xFA, 0xF0);
        ForestGreen = create("forestgreen", 0x22, 0x8B, 0x22);
        Fuchsia = create("fuchsia", 0xFF, 0x00, 0xFF);
        Gainsboro = create("gainsboro", 0xDC, 0xDC, 0xDC);
        GhostWhite = create("ghostwhite", 0xF8, 0xF8, 0xFF);
        Gold = create("gold", 0xFF, 0xD7, 0x00);
        GoldenRod = create("goldenrod", 0xDA, 0xA5, 0x20);
        Gray = create("gray", 0x80, 0x80, 0x80);
        Green = create("green", 0x00, 0x80, 0x00);
        GreenYellow = create("greenyellow", 0xAD, 0xFF, 0x2F);
        HoneyDew = create("honeydew", 0xF0, 0xFF, 0xF0);
        HotPink = create("hotpink", 0xFF, 0x69, 0xB4);
        IndianRed = create("indianred", 0xCD, 0x5C, 0x5C);
        Indigo = create("indigo", 0x4B, 0x00, 0x82);
        Ivory = create("ivory", 0xFF, 0xFF, 0xF0);
        Khaki = create("khaki", 0xF0, 0xE6, 0x8C);
        Lavender = create("lavender", 0xE6, 0xE6, 0xFA);
        LavenderBlush = create("lavenderblush", 0xFF, 0xF0, 0xF5);
        LawnGreen = create("lawngreen", 0x7C, 0xFC, 0x00);
        LemonChiffon = create("lemonchiffon", 0xFF, 0xFA, 0xCD);
        LightBlue = create("lightblue", 0xAD, 0xD8, 0xE6);
        LightCoral = create("lightcoral", 0xF0, 0x80, 0x80);
        LightCyan = create("lightcyan", 0xE0, 0xFF, 0xFF);
        LightGoldenRodYellow = create("lightgoldenrodyellow", 0xFA, 0xFA, 0xD2);
        LightGray = create("lightgray", 0xD3, 0xD3, 0xD3);
        LightGreen = create("lightgreen", 0x90, 0xEE, 0x90);
        LightPink = create("lightpink", 0xFF, 0xB6, 0xC1);
        LightSalmon = create("lightsalmon", 0xFF, 0xA0, 0x7A);
        LightSeaGreen = create("lightseagreen", 0x20, 0xB2, 0xAA);
        LightSkyBlue = create("lightskyblue", 0x87, 0xCE, 0xFA);
        LightSlateGray = create("lightslategray", 0x77, 0x88, 0x99);
        LightSteelBlue = create("lightsteelblue", 0xB0, 0xC4, 0xDE);
        LightYellow = create("lightyellow", 0xFF, 0xFF, 0xE0);
        Lime = create("lime", 0x00, 0xFF, 0x00);
        LimeGreen = create("limegreen", 0x32, 0xCD, 0x32);
        Linen = create("linen", 0xFA, 0xF0, 0xE6);
        Magenta = create("magenta", 0xFF, 0x00, 0xFF);
        Maroon = create("maroon", 0x80, 0x00, 0x00);
        MediumAquaMarine = create("mediumaquamarine", 0x66, 0xCD, 0xAA);
        MediumBlue = create("mediumblue", 0x00, 0x00, 0xCD);
        MediumOrchid = create("mediumorchid", 0xBA, 0x55, 0xD3);
        MediumPurple = create("mediumpurple", 0x93, 0x70, 0xDB);
        MediumSeaGreen = create("mediumseagreen", 0x3C, 0xB3, 0x71);
        MediumSlateBlue = create("mediumslateblue", 0x7B, 0x68, 0xEE);
        MediumSpringGreen = create("mediumspringgreen", 0x00, 0xFA, 0x9A);
        MediumTurquoise = create("mediumturquoise", 0x48, 0xD1, 0xCC);
        MediumVioletRed = create("mediumvioletred", 0xC7, 0x15, 0x85);
        MidnightBlue = create("midnightblue", 0x19, 0x19, 0x70);
        MintCream = create("mintcream", 0xF5, 0xFF, 0xFA);
        MistyRose = create("mistyrose", 0xFF, 0xE4, 0xE1);
        Moccasin = create("moccasin", 0xFF, 0xE4, 0xB5);
        NavajoWhite = create("navajowhite", 0xFF, 0xDE, 0xAD);
        Navy = create("navy", 0x00, 0x00, 0x80);
        OldLace = create("oldlace", 0xFD, 0xF5, 0xE6);
        Olive = create("olive", 0x80, 0x80, 0x00);
        OliveDrab = create("olivedrab", 0x6B, 0x8E, 0x23);
        Orange = create("orange", 0xFF, 0xA5, 0x00);
        OrangeRed = create("orangered", 0xFF, 0x45, 0x00);
        Orchid = create("orchid", 0xDA, 0x70, 0xD6);
        PaleGoldenRod = create("palegoldenrod", 0xEE, 0xE8, 0xAA);
        PaleGreen = create("palegreen", 0x98, 0xFB, 0x98);
        PaleTurquoise = create("paleturquoise", 0xAF, 0xEE, 0xEE);
        PaleVioletRed = create("palevioletred", 0xDB, 0x70, 0x93);
        PapayaWhip = create("papayawhip", 0xFF, 0xEF, 0xD5);
        PeachPuff = create("peachpuff", 0xFF, 0xDA, 0xB9);
        Peru = create("peru", 0xCD, 0x85, 0x3F);
        Pink = create("pink", 0xFF, 0xC0, 0xCB);
        Plum = create("plum", 0xDD, 0xA0, 0xDD);
        PowderBlue = create("powderblue", 0xB0, 0xE0, 0xE6);
        Purple = create("purple", 0x80, 0x00, 0x80);
        Red = create("red", 0xFF, 0x00, 0x00);
        RosyBrown = create("rosybrown", 0xBC, 0x8F, 0x8F);
        RoyalBlue = create("royalblue", 0x41, 0x69, 0xE1);
        SaddleBrown = create("saddlebrown", 0x8B, 0x45, 0x13);
        Salmon = create("salmon", 0xFA, 0x80, 0x72);
        SandyBrown = create("sandybrown", 0xF4, 0xA4, 0x60);
        SeaGreen = create("seagreen", 0x2E, 0x8B, 0x57);
        SeaShell = create("seashell", 0xFF, 0xF5, 0xEE);
        Sienna = create("sienna", 0xA0, 0x52, 0x2D);
        Silver = create("silver", 0xC0, 0xC0, 0xC0);
        SkyBlue = create("skyblue", 0x87, 0xCE, 0xEB);
        SlateBlue = create("slateblue", 0x6A, 0x5A, 0xCD);
        SlateGray = create("slategray", 0x70, 0x80, 0x90);
        Snow = create("snow", 0xFF, 0xFA, 0xFA);
        SpringGreen = create("springgreen", 0x00, 0xFF, 0x7F);
        SteelBlue = create("steelblue", 0x46, 0x82, 0xB4);
        Tan = create("tan", 0xD2, 0xB4, 0x8C);
        Teal = create("teal", 0x00, 0x80, 0x80);
        Thistle = create("thistle", 0xD8, 0xBF, 0xD8);
        Tomato = create("tomato", 0xFF, 0x63, 0x47);
        Turquoise = create("turquoise", 0x40, 0xE0, 0xD0);
        Violet = create("violet", 0xEE, 0x82, 0xEE);
        Wheat = create("wheat", 0xF5, 0xDE, 0xB3);
        White = create("white", 0xFF, 0xFF, 0xFF);
        WhiteSmoke = create("whitesmoke", 0xF5, 0xF5, 0xF5);
        Yellow = create("yellow", 0xFF, 0xFF, 0x00);
        YellowGreen = create("yellowgreen", 0x9A, 0xCD, 0x32);
    }

}
