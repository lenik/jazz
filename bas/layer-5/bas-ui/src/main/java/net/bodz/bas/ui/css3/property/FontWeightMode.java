package net.bodz.bas.ui.css3.property;

public enum FontWeightMode {

    normal,

    bold,

    bolder,

    lighter,

    _100("100"), //
    _200("200"), //
    _300("300"), //
    _400("400"), //
    _500("500"), //
    _600("600"), //
    _700("700"), //
    _800("800"), //
    _900("900"), //
    ;

    private final String name;

    private FontWeightMode() {
        this.name = name();
    }

    private FontWeightMode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static FontWeightMode parse(String s) {
        if (s == null)
            return null;
        switch (s) {
        case "normal":
            return normal;
        case "bold":
            return bold;
        case "bolder":
            return bolder;
        case "lighter":
            return lighter;
        case "100":
            return _100;
        case "200":
            return _200;
        case "300":
            return _300;
        case "400":
            return _400;
        case "500":
            return _500;
        case "600":
            return _600;
        case "700":
            return _700;
        case "800":
            return _800;
        case "900":
            return _900;
        default:
            return null;
        }
    }

}
