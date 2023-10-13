package net.bodz.bas.doc.io;

import net.bodz.bas.doc.io.DomWriter.np;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.io.res.IStreamInputSource;

public interface IDomCreatorImpl
        extends
            IDomCreator {

    INode getContext();

    void enter(INode node);

    @Override
    default Part newPart(PartLevel level, String title) {
        // level...
        PartGroup group = getContext().closest(np.PART_GROUP);
        Part part = group.addPart(title);
        enter(part);
        return part;
    }

    @Override
    default ListPar newList(boolean ordered) {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        ListPar list = pars.addListPar(ordered);
        enter(list);
        return list;
    }

    @Override
    default ListItem newItem() {
        ListPar list = getContext().closest(np.LIST);
        ListItem item = list.addItem();
        enter(item);
        return item;
    }

    @Override
    default TextPar newP() {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        TextPar textPar = pars.addTextPar();
        enter(textPar);
        return textPar;
    }

    @Override
    default FontStyleEnv newFontStyle() {
        IHaveRuns parent = getContext().closest(np.HAVE_RUNS);
        FontStyleEnv env = parent.addFontStyleEnv();
        enter(env);
        return env;
    }

    @Override
    default Table newTable(int headRows, int headColumns) {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        Table table = pars.addTable();
        table.firstRows = headRows;
        table.firstColumns = headColumns;
        if (headRows > 0)
            table.hBands = true;
        else if (headColumns > 0)
            table.vBands = true;
        enter(table);
        return table;
    }

    @Override
    default TableRow newTr() {
        Table table = getContext().closest(np.TABLE);
        TableRow row = table.addRow();
        enter(row);
        return row;
    }

    @Override
    default TableCell newTh() {
        TableCell cell = newTd();
        cell.setHeader(true);
        enter(cell);
        return cell;
    }

    @Override
    default TableCell newTd() {
        TableRow row = getContext().closest(np.TABLE_ROW);
        TableCell cell = row.addCell();
        enter(cell);
        return cell;
    }

    @Override
    default Hr newHr() {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        Hr hr = pars.addHr();
        // enter(hr);
        return hr;
    }

    @Override
    default Image newImage(IStreamInputSource source, MeasureLength width, MeasureLength height) {
        INode context = getContext();
        Image image;
        if (context.haveRuns()) {
            IHaveRuns runs = (IHaveRuns) context;
            image = runs.addImage();
        } else if (context.havePars()) {
            IHavePars pars = (IHavePars) context;
            image = pars.addImage();
        } else {
            IHavePars pars = getContext().closest(np.HAVE_PARS);
            image = pars.addImage();
        }

        image.setSource(source);
        image.setWidth(width);
        image.setHeight(height);
        // enter(image);
        return image;
    }

    @Override
    default FontEnv newFont(String family, MeasureLength size) {
        IHaveRuns parent = getContext().closest(np.HAVE_RUNS);
        FontEnv env = parent.addFontEnv();
        env.setFamily(family);
        env.setSize(size);
        enter(env);
        return env;
    }

}
