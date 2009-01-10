package net.bodz.swt.controls;

import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

public class PictureTest {

    public static void main(String[] args) {
        new ControlTest() {

            @Override
            protected void createContents(Shell shell) throws Exception {
                shell.setBackground(color(128, 128, 255));
                Picture picture = new Picture(shell, SWT.BORDER);
                picture.setBackground(color(128, 0, 0));
                Image image = SWTResources
                        .getImageRes("icons/full/obj16/ant.gif");
                picture.setImage(image);

                // Thread.sleep(1);
            }

        }.run();
    }

}
