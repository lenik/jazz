package net.bodz.swt.editors.objecteditor;

import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PropertiesComposite extends Composite {

    /**
     * Create the composite
     * 
     * @param parent
     * @param style
     */
    public PropertiesComposite(Composite parent, int style) {
        super(parent, style);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        setLayout(gridLayout);

        final Button button = new Button(this, SWT.TOGGLE);
        button
                .setImage(SWTResources
                        .getImageRes(PropertiesComposite.class,
                                "/com/sun/java/swing/plaf/motif/icons/ScrollRightArrowActive.gif"));
        button.setText("Toggle Button");

        final Label label = new Label(this, SWT.NONE);
        label.setText("Label");

        final Composite composite = new Composite(this, SWT.NONE);
        composite.setLayout(new GridLayout());

        final Composite composite_1 = new Composite(this, SWT.NONE);

        final Button button_1 = new Button(composite_1, SWT.NONE);
        button_1.setText("button");
        new Label(this, SWT.NONE);
        new Label(this, SWT.NONE);
    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

}
