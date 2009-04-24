package net.bodz.dist.ins.builtins;

import java.util.Collection;

import net.bodz.dist.ins.Component;
import net.bodz.dist.ins.Components;
import net.bodz.dist.ins.TestProject;

import org.eclipse.swt.SWT;
import org.junit.Test;

public class SelectComponentsDialogTest {

    @Test
    public void test() throws Exception {
        TestProject project = new TestProject();
        Components components = Components.collect(project);
        Component[] cv = components.values().toArray(new Component[0]);
        SelectComponentsDialog dialog = new SelectComponentsDialog(null, SWT.NONE,
                "Select Components Test", "Please select components:", cv); //$NON-NLS-1$ //$NON-NLS-2$
        Collection<Component> selection = dialog.open();
        if (selection == null)
            System.out.println("Canceled"); //$NON-NLS-1$
        else
            for (Component c : selection) {
                System.out.printf("Selected: %s - %s\n", c.getId(), c.getText()); //$NON-NLS-1$
            }
    }

}
