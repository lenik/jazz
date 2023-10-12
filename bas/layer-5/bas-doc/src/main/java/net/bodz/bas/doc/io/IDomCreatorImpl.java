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

    @Override
    default Part newPart(PartLevel level, String title) {
        // level...
        PartGroup group = getContext().closest(np.PART_GROUP);
        Part part = group.addPart(title);
        return part;
    }

    @Override
    default ListPar newList(boolean ordered) {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        return pars.addListPar(ordered);
    }

    @Override
    default ListItem newItem() {
        ListPar list = getContext().closest(np.LIST);
        return list.addItem();
    }

    @Override
    default TextPar newP() {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        return pars.addTextPar();
    }

    @Override
    default FontStyleEnv newFontStyle() {
        IHaveRuns parent = getContext().closest(np.HAVE_RUNS);
        return parent.addFontStyleEnv();
    }

    @Override
    default Table newTable(TableHeaderPosition headerPosition) {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        Table table = pars.addTable();
        table.setHeaderPosition(headerPosition);
        return table;
    }

    @Override
    default TableRow newTr() {
        Table table = getContext().closest(np.TABLE);
        return table.addRow();
    }

    @Override
    default TableCell newTh() {
        TableCell cell = newTd();
        cell.setHeader(true);
        return cell;
    }

    @Override
    default TableCell newTd() {
        TableRow row = getContext().closest(np.TABLE_ROW);
        TableCell cell = row.addCell();
        return cell;
    }

    @Override
    default Hr newHr() {
        IHavePars pars = getContext().closest(np.HAVE_PARS);
        return pars.addHr();
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
        return image;
    }

    @Override
    default FontEnv newFont(String family, MeasureLength size) {
        IHaveRuns parent = getContext().closest(np.HAVE_RUNS);
        FontEnv env = parent.addFontEnv();
        env.setFamily(family);
        env.setSize(size);
        return env;
    }

}
