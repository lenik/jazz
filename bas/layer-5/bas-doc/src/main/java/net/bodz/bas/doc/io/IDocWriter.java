package net.bodz.bas.doc.io;

import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.res.IStreamInputSource;

public interface IDocWriter<self_t extends IDocWriter<self_t>>
        extends
            IPrintOut,
            AutoCloseable {

    /**
     * pseudio group. can be div or span for block or inline context.
     */
    self_t begin();

    self_t begin(String className);

    self_t end();

    default self_t end(int n) {
        for (int i = 0; i < n - 1; i++)
            end();
        return end();
    }

    self_t end(ElementType elementType);

    void endAll();

    @Override
    void flush();

    @Override
    default void close() {
        endAll();
        flush();
    }

    self_t attribute(String name, Object value);

    self_t put(Object element);

    self_t section(PartLevel level, String title);

    default self_t chapter(String title) {
        return section(PartLevel.CHAPTER, title);
    }

    default self_t section(String title) {
        return section(PartLevel.SECTION, title);
    }

    default self_t subsection(String title) {
        return section(PartLevel.SUBSECTION, title);
    }

    default self_t subsubsection(String title) {
        return section(PartLevel.SUBSUBSECTION, title);
    }

    self_t list(boolean ordered);

    default self_t ul() {
        return list(false);
    }

    default self_t ol() {
        return list(true);
    }

    self_t item();

    default self_t item(Object data) {
        return item().data(data).end();
    }

    default self_t beginItem() {
        return item();
    }

    default self_t endItem() {
        return end(ElementType.ITEM);
    }

    self_t p();

    self_t b();

    self_t i();

    self_t u();

    default self_t p(Object data) {
        return p().data(data).end();
    }

    default self_t b(Object data) {
        return b().data(data).end();
    }

    default self_t i(Object data) {
        return i().data(data).end();
    }

    default self_t u(Object data) {
        return u().data(data).end();
    }

    default self_t table() {
        return table(TableHeaderPosition.TOP);
    }

    self_t table(TableHeaderPosition headerPosition);

    self_t tr();

    default self_t trHead(Object... cells) {
        tr();
        for (Object cell : cells)
            th(cell);
        return end();
    }

    default self_t trHead(String... cells) {
        tr();
        for (Object cell : cells)
            th(cell);
        return end();
    }

    default self_t tr(Object... cells) {
        tr();
        for (Object cell : cells)
            td(cell);
        return end();
    }

    default self_t tr(String... cells) {
        tr();
        for (Object cell : cells)
            td(cell);
        return end();
    }

    self_t th();

    default self_t th(Object data) {
        return th().data(data).end();
    }

    self_t td();

    default self_t td(Object data) {
        return td().data(data).end();
    }

    self_t hr();

    self_t image(IStreamInputSource source, MeasureLength width, MeasureLength height);

    self_t setAlign(HorizAlignment alignment);

    default self_t left() {
        return setAlign(HorizAlignment.LEFT);
    }

    default self_t center() {
        return setAlign(HorizAlignment.CENTER);
    }

    default self_t right() {
        return setAlign(HorizAlignment.RIGHT);
    }

    self_t font(String family, MeasureLength size);

    default self_t fontFamily(String family) {
        return font(family, null);
    }

    default self_t fontSize(MeasureLength size) {
        return font(null, size);
    }

    self_t color(String color);

    self_t backgroundColor(String color);

    self_t data(Object data);

    self_t text(String text);

}
