package net.bodz.bas.c.string;

public class Phrase {

    public String foo_bar;
    public String FOO_BAR;
    public String fooBar;
    public String FooBar;

    public static Phrase foo_bar(String s) {
        Phrase phrase = new Phrase();
        if (s != null) {
            phrase.foo_bar = s;
            phrase.FOO_BAR = s.toUpperCase();
            phrase.fooBar = StringId.UL.toCamel(s);
            phrase.FooBar = Strings.ucfirst(phrase.fooBar);
        }
        return phrase;
    }

    public static Phrase fooBar(String s) {
        Phrase phrase = new Phrase();
        if (s != null) {
            phrase.foo_bar = StringId.UL.breakCamel(s);
            phrase.FOO_BAR = phrase.foo_bar.toUpperCase();
            phrase.fooBar = s;
            phrase.FooBar = Strings.ucfirst(phrase.fooBar);
        }
        return phrase;
    }

    public static Phrase FooBar(String s) {
        Phrase phrase = new Phrase();
        if (s != null) {
            phrase.FooBar = s;
            phrase.fooBar = Strings.lcfirst(s);
            phrase.foo_bar = StringId.UL.breakCamel(phrase.fooBar);
            phrase.FOO_BAR = phrase.foo_bar.toUpperCase();
        }
        return phrase;
    }

    public static Phrase dual(String foo_bar, String fooBar) {
        Phrase phrase = new Phrase();
        if (foo_bar != null) {
            phrase.foo_bar = foo_bar;
            phrase.FOO_BAR = foo_bar.toUpperCase();
            phrase.fooBar = fooBar;
            phrase.FooBar = Strings.ucfirst(fooBar);
        }
        return phrase;
    }

}
