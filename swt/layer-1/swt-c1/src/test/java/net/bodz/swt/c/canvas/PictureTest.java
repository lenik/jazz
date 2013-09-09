package net.bodz.swt.c.canvas;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.junit.Test;

import net.bodz.swt.c.canvas.Picture;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.c.test.WidgetTester;

public class PictureTest
        extends WidgetTester {

    @Test
    public void test1() {
        Device dev = display;
        Color ctrlBg = new Color(dev, 128, 128, 255);
        Color picBg = new Color(dev, 128, 0, 128);

        body.setBackground(ctrlBg);

        Picture picture = new Picture(body, SWT.BORDER, //
                false);
        picture.setBackground(picBg);

        Image image = SWTResources.//
                getImage(new File("V:/Downloads/mx/Images/02.jpg"));
        // getImageRes("icons/full/obj16/ant.gif");
        picture.setImage(image);
    }

}
