package net.bodz.redist.installer.builtins;

import java.util.Collection;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.redist.installer.Components;
import net.bodz.redist.installer.IComponent;
import net.bodz.redist.installer.TestProject;

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
