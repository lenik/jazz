package net.bodz.pkg.sis.c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.SWT;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.TestSisProject;

public class SelectComponentsDialogTest
        extends Assert {

    @Test
    public void test()
            throws Exception {
        TestSisProject project = new TestSisProject();

        List<ISisComponent> components = new ArrayList<>();
        for (ISisComponent c : project.descendants())
            components.add(c);

        SelectComponentsDialog dialog = new SelectComponentsDialog(null, SWT.NONE, //
                "Select Components Test", "Please select components:", components);

        Collection<ISisComponent> selection = dialog.open();
        if (selection == null)
            System.out.println("Canceled");
        else
            for (ISisComponent c : selection) {
                System.out.printf("Selected: %s - %s\n", c.getId(), c.getDescription());
            }
    }

}
