package net.bodz.violet.schema.art;

import net.bodz.lily.schema.util.Uom;

public interface Uoms {

    Uom PIECE = new Uom(1, "pcs", "件", "数量");

    Uom METER = new Uom(100, "m", "米", "长度");
    Uom MILLIMETER = new Uom(101, "mm", "毫米", 0.001, METER);
    Uom CENTIMETER = new Uom(102, "cm", "厘米", 0.01, METER);
    Uom DECIMETER = new Uom(103, "dm", "分米", 0.1, METER);
    Uom KILOMETER = new Uom(104, "km", "千米", 1000, METER);

    Uom SQUARE_METER = new Uom(200, "m2", "平方米", "面积");
    Uom SQUARE_CENTIMETER = new Uom(202, "cm2", "平方厘米", 0.0001, SQUARE_METER);
    Uom SQUARE_KILOMETER = new Uom(204, "km2", "平方千米", 1000000, SQUARE_METER);

    Uom CUBIC_METER = new Uom(300, "m3", "立方米", "体积");
    Uom LITER = new Uom(350, "L", "升", 0.001, CUBIC_METER, "容量");
    Uom MILLILITER = new Uom(351, "mL", "毫升", 0.000001, CUBIC_METER, "容量");

    Uom GRAM = new Uom(400, "g", "克", "质量");
    Uom MILLIGRAM = new Uom(401, "mg", "毫克", 0.001, GRAM);
    Uom KILOGRAM = new Uom(402, "kg", "千克", 1000, GRAM);
    Uom TON = new Uom(403, "t", "吨", 1000000, GRAM);
    Uom POUND = new Uom(410, "lb", "磅", 453.59237, GRAM); // 16 ounce
    Uom OUNCE = new Uom(411, "oz", "盎司", 28.3495231, GRAM);
    Uom CARET = new Uom(430, "ct", "克拉", 0.2, GRAM);

    Uom NEWTON = new Uom(480, "N", "牛顿", "力");
    Uom KILONEWTON = new Uom(481, "kN", "千牛顿", 1000, NEWTON);

    Uom P_ZHANG = new Uom(902, "张", "张", 1, PIECE);
    Uom P_ZHI = new Uom(903, "只", "只", 1, PIECE);
    Uom P_ZHI2 = new Uom(904, "支", "支", 1, PIECE);
    Uom P_TAO = new Uom(905, "套", "套", 1, PIECE);
    Uom P_TAI = new Uom(907, "台", "台", 1, PIECE);
    Uom P_PING = new Uom(908, "瓶", "瓶", 1, PIECE);
    Uom P_TONG = new Uom(909, "桶", "桶", 1, PIECE);
    Uom P_XIANG = new Uom(910, "箱", "箱", 1, PIECE);
    Uom P_LI = new Uom(911, "粒", "粒", 1, PIECE);
    Uom P_TIAO = new Uom(912, "条", "条", 1, PIECE);
    Uom P_HE = new Uom(913, "盒", "盒", 1, PIECE);

}
