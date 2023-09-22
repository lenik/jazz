package net.bodz.bas.doc.io;

import net.bodz.bas.doc.attr.ElementType;
import net.bodz.bas.doc.attr.MeasureLength;
import net.bodz.bas.doc.attr.SectionLevel;

public interface IDocumentWriter<self_t extends IDocumentWriter<self_t>>
        extends
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

    void flush();

    @Override
    default void close() {
        endAll();
        flush();
    }

    self_t attribute(String name, Object value);

    self_t put(Object element);

    self_t section(SectionLevel level, Object title);

    default self_t beginSection(SectionLevel level, Object title) {
        return section(level, title);
    }

    default self_t chapter(Object title) {
        return section(SectionLevel.CHAPTER, title);
    }

    default self_t beginChapter(Object title) {
        return chapter(title);
    }

    default self_t endChapter(Object title) {
        return end(ElementType.CHAPTER);
    }

    default self_t section(Object title) {
        return section(SectionLevel.SECTION, title);
    }

    default self_t beginSection(Object title) {
        return section(title);
    }

    default self_t endSection(Object title) {
        return end(ElementType.SECTION);
    }

    default self_t subsection(Object title) {
        return beginSection(SectionLevel.SUBSECTION, title);
    }

    default self_t beginSubSection(Object title) {
        return subsection(title);
    }

    default self_t endSubSection(Object title) {
        return end(ElementType.SUBSECTION);
    }

    default self_t subsubsection(Object title) {
        return section(SectionLevel.SUBSUBSECTION, title);
    }

    default self_t beginSubSubSection(Object title) {
        return subsubsection(title);
    }

    default self_t endSubSubSection(Object title) {
        return end(ElementType.SUBSUBSECTION);
    }

    default self_t list() {
        return list(false);
    }

    self_t list(boolean ordered);

    default self_t beginList() {
        return list();
    }

    default self_t endList() {
        return end(ElementType.UNORDERED_LIST);
    }

    default self_t beginOrderedList() {
        return list();
    }

    default self_t endOrderedList() {
        return end(ElementType.ORDERED_LIST);
    }

    self_t item();

    default self_t item(String text) {
        return item().text(text).end();
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

    default self_t p(String content) {
        return p().text(content).end();
    }

    default self_t b(String text) {
        return b().text(text).end();
    }

    default self_t i(String text) {
        return i().text(text).end();
    }

    default self_t u(String text) {
        return u().text(text).end();
    }

    self_t table();

    default self_t beginTable() {
        return table();
    }

    default self_t endTable() {
        return end(ElementType.TABLE);
    }

    self_t tr();

    default self_t beginTableRow() {
        return tr();
    }

    default self_t endTableRow() {
        return end(ElementType.TR);
    }

    self_t th();

    default self_t th(String text) {
        return th().text(text).end();
    }

    default self_t beginTableHead() {
        return th();
    }

    default self_t endTableHead() {
        return end(ElementType.TH);
    }

    self_t td();

    default self_t td(String text) {
        return td().text(text).end();
    }

    default self_t beginTableData() {
        return td();
    }

    default self_t endTableData() {
        return end(ElementType.TD);
    }

    self_t hr();

    self_t font(String family, MeasureLength size);

    self_t fontFamily(String family);

    self_t fontSize(MeasureLength size);

    self_t color(String color);

    self_t backgroundColor(String color);

    self_t text(String s);

}
