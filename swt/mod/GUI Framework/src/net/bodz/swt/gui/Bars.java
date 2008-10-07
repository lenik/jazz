package net.bodz.swt.gui;

import net.bodz.bas.types.PrefixMap;

import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Bars {

    private PrefixMap<ToolBar>    toolbars;
    private PrefixMap<Menu>       menubars;
    private PrefixMap<ExpandItem> expandbars;

    public Bars() {
        toolbars = new PrefixMap<ToolBar>();
        menubars = new PrefixMap<Menu>();
        expandbars = new PrefixMap<ExpandItem>();
    }

    interface OrderredItem {
        int getOrder();
    }

    class Insertion {
        int     index;
        boolean sepBefore;
        boolean sepAfter;
    }

    public void getOrderGroup(ToolBar toolBar, String path) {
        int count = toolBar.getItemCount();
        for (int i = 0; i < count; i++) {
            ToolItem item = toolBar.getItem(i);
            int order = 0;
            if (item instanceof OrderredItem)
                order = ((OrderredItem) item).getOrder();

        }
    }

    public ToolBar findToolBar(String path) {
        return toolbars.getParent(path);
    }

    public Menu findMenuBar(String path) {
        return menubars.getParent(path);
    }

    public ExpandItem findExpandBar(String path) {
        return expandbars.getParent(path);
    }

}
