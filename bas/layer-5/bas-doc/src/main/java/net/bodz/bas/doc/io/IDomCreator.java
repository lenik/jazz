package net.bodz.bas.doc.io;

import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.io.res.IStreamInputSource;

public interface IDomCreator
        extends
            IAutoFormat {

    Part newPart(PartLevel level, String title);

    default Part newChapter(String title) {
        return newPart(PartLevel.CHAPTER, title);
    }

    default Part newSection(String title) {
        return newPart(PartLevel.SECTION, title);
    }

    default Part newSubsection(String title) {
        return newPart(PartLevel.SUBSECTION, title);
    }

    default Part newSubsubsection(String title) {
        return newPart(PartLevel.SUBSUBSECTION, title);
    }

    ListPar newList(boolean ordered);

    default ListPar newUl() {
        return newList(false);
    }

    default ListPar newOl() {
        return newList(true);
    }

    ListItem newItem();

    default ListItem newItem(Object data) {
        ListItem item = newItem();
        item.setText(formatData(data));
        return item;
    }

    TextPar newP();

    FontStyleEnv newFontStyle();

    default FontStyleEnv newB() {
        FontStyleEnv env = newFontStyle();
        env.setBold(true);
        return env;
    }

    default FontStyleEnv newI() {
        FontStyleEnv env = newFontStyle();
        env.setItalic(true);
        return env;
    }

    default FontStyleEnv newU() {
        FontStyleEnv env = newFontStyle();
        env.setUnderline(true);

        return env;
    }

    default TextPar newP(Object data) {
        TextPar par = newP();
        par.setText(formatData(data));
        return par;
    }

    default FontStyleEnv newB(Object data) {
        FontStyleEnv env = newB();
        env.setText(formatData(data));
        return env;
    }

    default FontStyleEnv newI(Object data) {
        FontStyleEnv env = newI();
        env.setText(formatData(data));
        return env;
    }

    default FontStyleEnv newU(Object data) {
        FontStyleEnv env = newU();
        env.setText(formatData(data));
        return env;
    }

    default Table newTable() {
        return newTable(TableHeaderPosition.TOP);
    }

    Table newTable(TableHeaderPosition headerPosition);

    TableRow newTr();

    default TableRow newTrHead(Object... cells) {
        TableRow row = newTr();
        for (Object data : cells) {
            TableCell cell = row.addCell();
            cell.setHeader(true);
            cell.setText(formatData(data));
        }
        return row;
    }

    default TableRow newTrHead(String... cells) {
        return newTrHead((Object[]) cells);
    }

    default TableRow newTr(Object... cells) {
        TableRow row = newTr();
        for (Object data : cells) {
            TableCell cell = row.addCell();
            cell.setText(formatData(data));
        }
        return row;
    }

    default TableRow newTr(String... cells) {
        return newTrHead((Object[]) cells);
    }

    TableCell newTh();

    default TableCell newTh(Object data) {
        TableCell cell = newTh();
        cell.setText(formatData(data));
        return cell;
    }

    TableCell newTd();

    default TableCell newTd(Object data) {
        TableCell cell = newTd();
        cell.setText(formatData(data));
        return cell;
    }

    Hr newHr();

    Image newImage(IStreamInputSource source, MeasureLength width, MeasureLength height);

    FontEnv newFont(String family, MeasureLength size);

    default FontEnv newFontFamily(String family) {
        return newFont(family, null);
    }

    default FontEnv newFontSize(MeasureLength size) {
        return newFont(null, size);
    }

}
