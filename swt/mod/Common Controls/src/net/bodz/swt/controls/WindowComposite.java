package net.bodz.swt.controls;

import java.util.ArrayList;
import java.util.List;

import net.bodz.swt.controls.helper.StackComposite;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @test {@link WindowCompositeTest}
 */
public class WindowComposite extends Composite {

    private boolean            expanded;
    private Image              expandedImage;
    private Image              collaspedImage;

    private boolean            useSystemStyle = false;
    private Composite          titleBar;
    protected Label            imageLabel;
    protected Label            titleLabel;
    protected ToolBar          toolBar;
    private ToolItem           switcherItem;

    private StackComposite     stack;
    private Composite          hidden;
    private Composite          visible;
    protected Composite        body;

    List<DetailSwitchListener> switchListeners;

    public WindowComposite(Composite parent, int style) {
        this(parent, style, false, null);
    }

    public WindowComposite(Composite parent, int style, boolean expanded,
            final Composite fitParent) {
        super(parent, style | SWT.BORDER);
        GridLayout gridLayout = new GridLayout(1, false);
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        setLayout(gridLayout);

        this.expandedImage = SWTResources
                .getImageRes("/icons/full/elcl16/thin_min_view.gif"); //$NON-NLS-1$
        this.collaspedImage = SWTResources
                .getImageRes("/icons/full/elcl16/thin_max_view.gif"); //$NON-NLS-1$

        titleBar = new Composite(this, SWT.NONE);
        final GridLayout titleLayout = new GridLayout();
        titleLayout.horizontalSpacing = 2;
        titleLayout.marginWidth = 2;
        titleLayout.marginHeight = 2;
        titleLayout.numColumns = 4;
        titleBar.setLayout(titleLayout);
        titleBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        imageLabel = new Label(titleBar, SWT.NONE);
        titleLabel = new Label(titleBar, SWT.NONE);

        if (useSystemStyle) {
            titleBar.setBackgroundMode(SWT.INHERIT_DEFAULT);
            titleBar.setBackground(getDisplay().getSystemColor(
                    SWT.COLOR_TITLE_BACKGROUND));
            titleLabel.setForeground(getDisplay().getSystemColor(
                    SWT.COLOR_TITLE_FOREGROUND));
            Font titleFont = titleLabel.getFont();
            FontData[] fontData = titleFont.getFontData();
            for (FontData fd : fontData)
                fd.setStyle(fd.getStyle() | SWT.BOLD);
            titleFont.dispose();
            titleLabel.setFont(new Font(getDisplay(), fontData));
        }

        toolBar = new ToolBar(titleBar, SWT.FLAT); // SWT.WRAP
        toolBar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

        final ToolBar _toolBar = new ToolBar(titleBar, SWT.FLAT);
        switcherItem = new ToolItem(_toolBar, SWT.PUSH);
        switcherItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setExpanded(!WindowComposite.this.expanded);
            }
        });

        stack = new StackComposite(this, SWT.NONE);
        stack.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        hidden = null; // new EmptyComposite(stack, SWT.NONE);

        visible = new Composite(stack, SWT.NONE);
        GridLayout contentsLayout = new GridLayout(1, false);
        contentsLayout.marginWidth = 0;
        contentsLayout.marginHeight = 0;
        contentsLayout.horizontalSpacing = 0;
        contentsLayout.verticalSpacing = 0;
        visible.setLayout(contentsLayout);

        final Label hr = new Label(visible, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridData hrData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        hr.setLayoutData(hrData);

        body = new Composite(visible, SWT.NONE);
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
        return titleLabel.getText();
    }

    public void setText(String caption) {
        titleLabel.setText(caption);
    }

    public void setExpanded(boolean expanded) {
        if (expanded != this.expanded) {
            this.expanded = expanded;
            if (expanded) {
                switcherItem.setImage(expandedImage);
                stack.bringFront(visible);
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
