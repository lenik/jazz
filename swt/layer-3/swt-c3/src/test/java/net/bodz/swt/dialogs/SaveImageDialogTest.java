package net.bodz.swt.dialogs;

import java.io.File;

import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Shell;

public class SaveImageDialogTest {

    static Image image;
    static {
        image = SWTResources.getImage(new File("V:/Downloads/mx/Images/03.jpg")); //$NON-NLS-1$
    }

    public static void main(String[] args) {
        ImageData imageData = image.getImageData();
        Shell parent = new Shell();
        SaveImageDialog dialog = new SaveImageDialog(parent, SWT.NONE, imageData);
        File file = dialog.open();
        System.out.println("File: " + file); //$NON-NLS-1$
    }

}
