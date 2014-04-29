package net.bodz.pkg.sis.c;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.gui.style.IImageData;
import net.bodz.bas.gui.style.ImageUsage;
import net.bodz.bas.gui.xjdoc.GUIElementDoc;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.tree.IQuadState;
import net.bodz.bas.t.tree.QuadStateTreeItems;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.swt.gui.style.SwtImageMapper;

public class SisComponentTree
        extends Tree
        implements IQuadState {

    static final int iconSize = 16;

    Label descriptionLabel;
    Map<ISisComponent, TreeItem> map;

    public SisComponentTree(Composite parent, int style, Label descriptionLabel) {
        super(parent, style);

        this.descriptionLabel = descriptionLabel;

        this.map = new IdentityHashMap<ISisComponent, TreeItem>();

        addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if ((e.detail & SWT.CHECK) != 0)
                    onCheckTreeItem(e);
                else
                    onSelectTreeItem(e);
            }
        });

        addTreeListener(new TreeListener() {
            @Override
            public void treeExpanded(TreeEvent e) {
                // Pre-loaded
            }

            @Override
            public void treeCollapsed(TreeEvent e) {
                // Pre-loaded
            }
        });
    }

    public int createTree(TreeItem item, ISisComponent component) {
        Display device = item.getDisplay();

        // cItemMap.put(component, item);
        item.setData(component);
        map.put(component, item); // XXX also, check dead loop here.

        iString text = component.getDescription();
        if (text != null)
            item.setText(text.toString());

        if (component instanceof IXjdocElement) {
            IElementDoc xjdoc = ((IXjdocElement) component).getXjdoc();
            GUIElementDoc guidoc = xjdoc.to(GUIElementDoc.class);
            IGUIElementStyleDeclaration styleClass = guidoc.getStyleClass();

            IImageData imageData = styleClass.getImage(ImageUsage.NORMAL);
            if (imageData != null) {
                if (imageData.getWidth() > iconSize || imageData.getHeight() > iconSize)
                    imageData = imageData.scaledTo(iconSize, iconSize);

                Image _image = SwtImageMapper.convert(device, imageData);
                item.setImage(_image);
            }
        }

        boolean selection = component.isSelected();
        int state = selection ? ONE : ZERO;
        Collection<? extends ISisComponent> children = component.getChildren();
        if (children != null)
            for (ISisComponent child : children) {
                if (!child.isVisible())
                    continue;

                TreeItem childItem = new TreeItem(item, SWT.NONE);

                if (component.isLocked()) {
                    Color readOnlyColor = device.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
                    childItem.setForeground(readOnlyColor);
                }

                int childState = createTree(childItem, child);
                state |= childState;
            }

        if (state == QuadStateTreeItems.Q0 || state == QuadStateTreeItems.Q1)
            state = ONE;
        QuadStateTreeItems.setState(item, state);
        return state;
    }

    public TreeItem getTreeItem(ISisComponent component) {
        return map.get(component);
    }

    void onSelectTreeItem(SelectionEvent e) {
        TreeItem[] items = getSelection();
        if (items != null && items.length > 0) {
            TreeItem item = items[0];
            ISisComponent c = (ISisComponent) item.getData();
            iString text = c.getDescription();
            if (text != null)
                descriptionLabel.setText(text.toString());
        }
    }

    void onCheckTreeItem(SelectionEvent e) {
        TreeItem item = (TreeItem) e.item;
        ISisComponent c = (ISisComponent) item.getData();
        if (c.isLocked()) {
            item.setChecked(c.isSelected()); // revert if any change.
            e.doit = false;
            return;
        }

        boolean checked = item.getChecked();
        QuadStateTreeItems.setStateRec(item, checked ? ONE : ZERO, 1);
        QuadStateTreeItems.updateAncestorStates(item);
    }

}
