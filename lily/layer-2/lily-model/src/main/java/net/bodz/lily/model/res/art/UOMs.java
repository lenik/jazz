package net.bodz.lily.model.res.art;

public interface UOMs {

    UOM PIECE = new UOM(1, "pcs", "件", "数量");

    UOM METER = new UOM(100, "m", "米", "长度");
    UOM MILLIMETER = new UOM(101, "mm", "毫米", 0.001, METER);
    UOM CENTIMETER = new UOM(102, "cm", "厘米", 0.01, METER);
    UOM DECIMETER = new UOM(103, "dm", "分米", 0.1, METER);
    UOM KILOMETER = new UOM(104, "km", "千米", 1000, METER);

    UOM SQUARE_METER = new UOM(200, "m2", "平方米", "面积");
    UOM SQUARE_CENTIMETER = new UOM(202, "cm2", "平方厘米", 0.0001, SQUARE_METER);
    UOM SQUARE_KILOMETER = new UOM(204, "km2", "平方千米", 1000000, SQUARE_METER);

    UOM CUBIC_METER = new UOM(300, "m3", "立方米", "体积");
    UOM LITER = new UOM(350, "L", "升", 0.001, CUBIC_METER, "容量");
    UOM MILLILITER = new UOM(351, "mL", "毫升", 0.000001, CUBIC_METER, "容量");

    UOM GRAM = new UOM(400, "g", "克", "质量");
    UOM MILLIGRAM = new UOM(401, "mg", "毫克", 0.001, GRAM);
    UOM KILOGRAM = new UOM(402, "kg", "千克", 1000, GRAM);
    UOM TON = new UOM(403, "t", "吨", 1000000, GRAM);
    UOM POUND = new UOM(410, "lb", "磅", 453.59237, GRAM); // 16 ounce
    UOM OUNCE = new UOM(411, "oz", "盎司", 28.3495231, GRAM);
    UOM CARET = new UOM(430, "ct", "克拉", 0.2, GRAM);

    UOM NEWTON = new UOM(480, "N", "牛顿", "力");
    UOM KILONEWTON = new UOM(481, "kN", "千牛顿", 1000, NEWTON);

    UOM P_ZHANG = new UOM(902, "张", "张", 1, PIECE);
    UOM P_ZHI = new UOM(903, "只", "只", 1, PIECE);
    UOM P_ZHI2 = new UOM(904, "支", "支", 1, PIECE);
    UOM P_TAO = new UOM(905, "套", "套", 1, PIECE);
    UOM P_TAI = new UOM(907, "台", "台", 1, PIECE);
    UOM P_PING = new UOM(908, "瓶", "瓶", 1, PIECE);
    UOM P_TONG = new UOM(909, "桶", "桶", 1, PIECE);
    UOM P_XIANG = new UOM(910, "箱", "箱", 1, PIECE);
    UOM P_LI = new UOM(911, "粒", "粒", 1, PIECE);
    UOM P_TIAO = new UOM(912, "条", "条", 1, PIECE);
    UOM P_HE = new UOM(913, "盒", "盒", 1, PIECE);

}
