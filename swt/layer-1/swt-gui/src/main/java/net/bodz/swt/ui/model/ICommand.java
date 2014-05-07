package net.bodz.swt.ui.model;

import org.eclipse.swt.graphics.Image;

public interface ICommand {

    boolean isEnabled();

    boolean isVisible();

    String getText();

    String getDoc();

    Image getImage();

    void execute();

    String getMenuItem();

    String getToolItem();

    String getViewId();

}
