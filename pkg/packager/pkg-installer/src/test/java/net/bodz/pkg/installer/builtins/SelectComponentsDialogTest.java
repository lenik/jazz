package net.bodz.pkg.installer.builtins;

import java.util.Collection;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.pkg.installer.Components;
import net.bodz.pkg.installer.IComponent;
import net.bodz.pkg.installer.TestProject;

public class SelectComponentsDialogTest {

    @Test
    public void test()
            throws Exception {
        TestProject project = new TestProject();
        Components components = Components.collect(project);
        IComponent[] cv = components.values().toArray(new IComponent[0]);
        SelectComponentsDialog dialog = new SelectComponentsDialog(null, SWT.NONE, "Select Components Test",
                "Please select components:", cv);
        Collection<IComponent> selection = dialog.open();
        if (selection == null)
            System.out.println("Canceled");
        else
            for (IComponent c : selection) {
                System.out.printf("Selected: %s - %s\n", c.getId(), c.getDescription());
            }
    }

}
