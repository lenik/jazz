package net.bodz.xfo.browser.ui;

import net.bodz.bas.ui.UIException;
import net.bodz.swt.gui.BasicGUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class Main extends BasicGUI {

    private SashForm            mainSash;
    private SashForm            topPane;
    private Composite           listPane;
    private Composite           summaryPane;

    private FileStoreListEditor storeList;

    @Override
    protected void createInitialView(Composite holder) throws UIException {
        holder.setLayout(new FillLayout());
        mainSash = new SashForm(holder, SWT.VERTICAL | SWT.BORDER);
        mainSash.setSashWidth(1);

        topPane = new SashForm(mainSash, SWT.BORDER);
        topPane.setSashWidth(1);
        listPane = new Composite(mainSash, SWT.NONE);
        summaryPane = new Composite(mainSash, SWT.NONE);
        mainSash.setWeights(new int[] { 93, 222, 54 });

        storeList = new FileStoreListEditor(topPane, SWT.NONE);
        storeList.setText("File Stores");

        String[] groups = { "&Tags", "&Author", "&Date" };
        for (String group : groups) {
            new CategoryList(topPane, SWT.NONE).setText(group);
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

}
