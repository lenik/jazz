package net.bodz.swt.reflect;

import org.eclipse.swt.graphics.Image;

public interface IAction {

    boolean isEnabled();

    boolean isVisible();

    String getText();

    String getDoc();

    Image getImage();

    void execute();

}
