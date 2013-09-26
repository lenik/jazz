package net.bodz.swt.c.file.image;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.c.resources.SWTResources;

public class SaveImageDialogTest {

    static Image image;
    static {
        image = SWTResources.getImage(new File("V:/Downloads/mx/Images/03.jpg"));
    }

    public static void main(String[] args) {
        ImageData imageData = image.getImageData();
        Shell parent = new Shell();
        SaveImageDialog dialog = new SaveImageDialog(parent, SWT.NONE, imageData);
        File file = dialog.open();
        System.out.println("File: " + file);
    }

}
