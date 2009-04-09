package net.bodz.swt.controls;

import java.io.File;

import net.bodz.swt.gui.util.ControlTestApp;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

public class PictureTest {

    public static void main(String[] args) {
        ControlTestApp test = new ControlTestApp();
        Device dev = test.display;
        Color ctrlBg = new Color(dev, 128, 128, 255);
        Color picBg = new Color(dev, 128, 0, 128);

        test.parent.setBackground(ctrlBg);

        Picture picture = new Picture(test.parent, SWT.BORDER, //
                false);
        picture.setBackground(picBg);

        Image image = SWTResources.//
                getImage(new File("V:/Downloads/mx/Images/02.jpg"));
        // getImageRes("icons/full/obj16/ant.gif");
        picture.setImage(image);

        test.run();
    }

}
