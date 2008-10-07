package net.bodz.swt.gui;

import java.lang.reflect.Member;
import java.util.Map.Entry;

import net.bodz.bas.lang.err.CreateException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class GridBuilder implements GUIBuilder {

    private int margin = 3;

    public GridBuilder() {
    }

    private static int defaultIconSize = 16;

    @Override
    public Composite build(GUIClass guiclass, Composite parent, int style)
            throws BuildException {
        // Scrollable scrollable = new Scrollable(parent,style);
        Composite grid = new Composite(parent, style);
        // ICON CONTENTS
        GridLayout layout = new GridLayout(2, false);
        grid.setLayout(layout);

        int row = 0;
        for (Entry<Member, GUIMember> entry : guiclass.members.entrySet()) {
            Member m = entry.getKey();
            GUIMember g = entry.getValue();
            // ICON | ...
            GUIHint hint = null;

            Label iconLabel = null;
            try {
                Image icon = hint.getIcon();
                if (icon != null) {
                    iconLabel = new Label(grid, SWT.None);
                    iconLabel.setImage(icon);
                    GridData gd = new GridData();
                    gd.horizontalAlignment = SWT.CENTER;
                    gd.verticalAlignment = SWT.TOP;
                    iconLabel.setLayoutData(gd);
                } else {
                    // TODO - default icon
                    // createDefaultIcon(grid, SWT.NONE);
                    iconLabel = new Label(grid, SWT.None);
                }
            } catch (CreateException e) {
                throw new BuildException(e);
            }

            Control control;
        }
        return grid;
    }
}
