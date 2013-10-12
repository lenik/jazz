package net.bodz.swt.c.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Menus {

    public static Menu newSubMenu(Menu parent, String text) {
        MenuItem parentItem = new MenuItem(parent, SWT.CASCADE);
        Menu subMenu = new Menu(parentItem);
        parentItem.setMenu(subMenu);
        return subMenu;
    }

}
