package net.bodz.swt.controls;

import java.util.ArrayList;
import java.util.List;

import net.bodz.swt.controls.helper.StackComposite;
import net.bodz.swt.layouts.BorderLayout;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @TestBy DetailCompositeTest
 */
public class DetailComposite extends Composite {

    private boolean            expanded;
    private Image              expandedImage;
    private Image              collaspedImage;

    protected Composite        topBar;
    protected Label            imageLabel;
    protected Label            textLabel;
    protected ToolBar          toolBar;
    private ToolItem           switcherItem;

    protected StackComposite   stack;
    protected Composite        hidden;
    protected Composite        body;

    List<DetailSwitchListener> switchListeners;

    public DetailComposite(Composite parent, int style) {
        this(parent, style, false, null);
    }

    public DetailComposite(Composite parent, int style, boolean expanded,
            final Composite fitParent) {
        super(parent, style);
        setLayout(new BorderLayout(0, 0));

        this.expandedImage = SWTResources
                .getImageRes("/icons/full/elcl16/thin_min_view.gif");
        this.collaspedImage = SWTResources
                .getImageRes("/icons/full/elcl16/thin_max_view.gif");

        topBar = new Composite(this, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 3;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.numColumns = 3;
        topBar.setLayout(gridLayout);
        topBar.setLayoutData(BorderLayout.NORTH);

        imageLabel = new Label(topBar, SWT.NONE);
        textLabel = new Label(topBar, SWT.NONE);

        toolBar = new ToolBar(topBar, SWT.WRAP | SWT.FLAT);
        toolBar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

        switcherItem = new ToolItem(toolBar, SWT.PUSH);
        switcherItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setExpanded(!DetailComposite.this.expanded);
            }
        });

        stack = new StackComposite(this, SWT.BORDER);
        stack.setLayoutData(BorderLayout.CENTER);

        hidden = null; // new EmptyComposite(stack, SWT.NONE);

        body = new Composite(stack, SWT.NONE);
        body.setLayout(new FillLayout());

        createContents(body, SWT.NONE);

        this.expanded = !expanded;
        setExpanded(expanded);

        if (fitParent != null)
            addDetailSwitchListener(new DetailSwitchListener() {
                @Override
                public void detailSwitch(DetailSwitchEvent e) {
                    fitParent.layout();
                }
            });
    }

    protected void createContents(Composite parent, int style) {
    }

    protected ToolItem addToolItem(int style) {
        ToolItem toolItem = new ToolItem(toolBar, style, 0);
        return toolItem;
    }

    public Image getImage() {
        return imageLabel.getImage();
    }

    public void setImage(Image image) {
        imageLabel.setImage(image);
    }

    public String getText() {
        return textLabel.getText();
    }

    public void setText(String caption) {
        textLabel.setText(caption);
    }

    public void setExpanded(boolean expanded) {
        if (expanded != this.expanded) {
            this.expanded = expanded;
            if (expanded) {
                switcherItem.setImage(expandedImage);
                stack.bringFront(body);
            } else {
                switcherItem.setImage(collaspedImage);
                stack.bringFront(hidden);
            }
            if (switchListeners != null) {
                DetailSwitchEvent e = new DetailSwitchEvent(this, expanded);
                for (DetailSwitchListener l : switchListeners)
                    l.detailSwitch(e);
            }
        }
    }

    public void addDetailSwitchListener(DetailSwitchListener l) {
        if (switchListeners == null)
            switchListeners = new ArrayList<DetailSwitchListener>(1);
        switchListeners.add(l);
    }

    public void removeDetailSwitchListener(DetailSwitchListener l) {
        if (switchListeners != null)
            switchListeners.remove(l);
    }

}
