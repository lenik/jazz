package net.bodz.swt.gui.api;

import org.eclipse.swt.graphics.Image;

public interface ICommand {

    boolean isEnabled();

    boolean isVisible();

    String getText();

    String getDoc();

    Image getImage();

    void execute();

}