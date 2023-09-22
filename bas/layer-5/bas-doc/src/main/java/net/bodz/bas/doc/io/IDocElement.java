package net.bodz.bas.doc.io;

public interface IDocElement<self_t extends IDocElement<self_t>> {

    self_t h1(Object content);

    self_t h2(Object content);

    self_t h3(Object content);

    self_t h4(Object content);

    self_t h5(Object content);

    self_t h6(Object content);

    self_t b(Object content);

    self_t i(Object content);

    self_t u(Object content);

    self_t p(Object content);

    self_t table(Object content);

    self_t tr(Object content);

    self_t th(Object content);

    self_t td(Object content);

    self_t hr(Object content);

    self_t font(String family, int size, Object content);

    self_t fontFamily(String family, Object content);

    self_t fontSize(int size, Object content);

    self_t color(String color, Object content);

    self_t backgroundColor(String color, Object content);

}
