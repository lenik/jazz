package net.bodz.bas.doc.io;

import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.doc.property.PartLevel;

public interface IStructDocWriter<self_t extends IStructDocWriter<self_t>>
        extends
            IDocWriter<self_t> {

    default self_t beginSection(PartLevel level, String title) {
        return section(level, title);
    }

    default self_t endSection(PartLevel level) {
        return end(ElementType.GENERIC_SECTION);
    }

    default self_t beginChapter(String title) {
        return chapter(title);
    }

    default self_t endChapter() {
        return end(ElementType.CHAPTER);
    }

    default self_t beginSection(String title) {
        return section(title);
    }

    default self_t endSection() {
        return end(ElementType.SECTION);
    }

    default self_t beginSubSection(String title) {
        return subsection(title);
    }

    default self_t endSubSection() {
        return end(ElementType.SUBSECTION);
    }

    default self_t beginSubSubSection(String title) {
        return subsubsection(title);
    }

    default self_t endSubSubSection() {
        return end(ElementType.SUBSUBSECTION);
    }

    default self_t beginList() {
        return ul();
    }

    default self_t endList() {
        return end(ElementType.UNORDERED_LIST);
    }

    default self_t beginOrderedList() {
        return ol();
    }

    default self_t endOrderedList() {
        return end(ElementType.ORDERED_LIST);
    }

    default self_t beginTable() {
        return table();
    }

    default self_t endTable() {
        return end(ElementType.TABLE);
    }

    default self_t beginTableRow() {
        return tr();
    }

    default self_t endTableRow() {
        return end(ElementType.TR);
    }

    default self_t beginTableHead() {
        return th();
    }

    default self_t endTableHead() {
        return end(ElementType.TH);
    }

    default self_t beginTableData() {
        return td();
    }

    default self_t endTableData() {
        return end(ElementType.TD);
    }

}
