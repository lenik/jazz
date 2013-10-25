package net.bodz.pkg.sis.c;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

import net.bodz.bas.gui.err.GUIValidationException;
import net.bodz.bas.gui.err.QuietValidationException;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.bas.t.tree.IQuadState;
import net.bodz.bas.t.tree.QuadStateTreeItems;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.pkg.sis.SisVariable;
import net.bodz.pkg.sisi.page.ConfigPage;
import net.bodz.swt.c.composite.WindowComposite;
import net.bodz.swt.c.pageflow.IPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.ServiceContext;
import net.bodz.swt.c.resources.SWTResources;

public class CustomComponents
        extends ConfigComponent {

    private static final long serialVersionUID = 1L;

    public CustomComponents(ISisComponent parent) {
        super(parent, null);
    }

    @Override
    public ConfigPage createConfigPage() {
        return new CustomComponentsPage(this);
    }

}

class CustomComponentsPage
        extends ConfigPage
        implements IQuadState {

    boolean showRoot = true;

    private Label descriptionLabel;
    private SisComponentTree tree;
    private Composite statusbar;
    private Label sizeLabel;
    private WindowComposite baseDirsComp;
    private List<Text> dirTexts;

    public CustomComponentsPage(ISisComponent owner) {
        super(owner);
    }

    /** ⇱ Implementation Of {@link IPage}. */
    /* _____________________________ */static section.iface __PAGE_UI__;

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return tr._("Custom Components");
    }

    @Override
    protected void createContents(Composite holder)
            throws PageException {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 1;
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 3;
        holder.setLayout(gridLayout);

        final Composite infobar = new Composite(holder, SWT.NONE);
        final GridData infobar_gd = new GridData(SWT.LEFT, SWT.FILL, false, true);
        infobar_gd.widthHint = 100;
        infobar.setLayoutData(infobar_gd);
        infobar.setLayout(new FillLayout());

        descriptionLabel = new Label(infobar, SWT.WRAP);
        descriptionLabel.setText("DESCRIPTION");

        Label vt = new Label(holder, SWT.SEPARATOR | SWT.VERTICAL);
        vt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

        tree = new SisComponentTree(holder, SWT.CHECK, descriptionLabel);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Label hr = new Label(holder, SWT.SEPARATOR | SWT.HORIZONTAL);
        hr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

        baseDirsComp = new WindowComposite(holder, SWT.NONE, false, holder) {
            @Override
            protected void createContents(Composite parent, int style) {
                createDirCompContents(parent, style);
            }
        };

        baseDirsComp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
        baseDirsComp.setText(tr._("Install Locations:"));

        Label hr2 = new Label(holder, SWT.SEPARATOR | SWT.HORIZONTAL);
        hr2.setLayoutData(//
        new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

        statusbar = new Composite(holder, SWT.NONE);
        statusbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
        final GridLayout gridLayout_statusbar = new GridLayout();
        gridLayout_statusbar.numColumns = 2;
        gridLayout_statusbar.marginHeight = 0;
        gridLayout_statusbar.marginWidth = 0;
        statusbar.setLayout(gridLayout_statusbar);

        final Label installSizeLabel = new Label(statusbar, SWT.NONE);
        installSizeLabel.setText(tr._("Install Size: "));

        sizeLabel = new Label(statusbar, SWT.NONE);
    }

    protected void createDirCompContents(final Composite parent, int style) {
        final Image folderImage = SWTResources.getImageRes("/com/sun/java/swing/plaf/windows/icons/Directory.gif");

        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        parent.setLayout(gridLayout);

        Map<String, SisVariable> varMap = getProject().getVariableMap();
        dirTexts = new ArrayList<Text>();
        for (Map.Entry<String, SisVariable> e : varMap.entrySet()) {
            String varName = e.getKey();
            final SisVariable var = e.getValue();
            if (var.getValueType() != File.class)
                continue;

            new Label(parent, SWT.NONE).setImage(folderImage);

            final Label infoLabel = new Label(parent, SWT.NONE);
            String s = var.getLabel().toString();
            if (var.getDescription() != null)
                s += ": " + var.getDescription();
            infoLabel.setText(s);

            GridData infoLabel_gd = new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1);
            infoLabel.setLayoutData(infoLabel_gd);

            new Label(parent, SWT.NONE);

            final Text dirText = new Text(parent, SWT.BORDER);
            dirTexts.add(dirText);
            dirText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            File fileValue = (File) var.getValue();
            if (fileValue == null)
                throw new NullPointerException(tr._("file variable is set to null in session: ") + varName);

            dirText.setData(varName);
            dirText.setText(fileValue.getPath());

            final Button browseButton = new Button(parent, SWT.NONE);
            browseButton.setText("...");
            browseButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.NONE);
                    dialog.setText(tr._("Select the directory for ") + var.getLabel());
                    String dir = dialog.open();
                    if (dir != null)
                        dirText.setText(dir);
                }
            });
        }
    }

    /** ⇱ Implementation Of {@link IPage}. */
    /* _____________________________ */static section.iface __PAGE_FN__;

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        // refreshBaseDirs();

        // Refresh tree.
        tree.removeAll();
        // cItemMap = new HashMap<IComponent, TreeItem>();
        ISisProject project = getProject();
        if (showRoot) {
            TreeItem root = new TreeItem(tree, SWT.NONE);
            tree.createTree(root, project);
        } else {
            Collection<? extends ISisComponent> topNodes = project.getChildren();
            for (ISisComponent c : topNodes) {
                TreeItem item = new TreeItem(tree, SWT.NONE);
                tree.createTree(item, c);
            }
        }

        return null;
    }

    @Override
    public void validate()
            throws GUIValidationException {
        ISisProject project = getProject();

        List<ISisComponent> selection = new ArrayList<ISisComponent>();
        for (ISisComponent c : project.descendants())
            if (c.isSelected())
                selection.add(c);

        List<ISisComponent> missingList = project.getMissingDependencies(selection);
        if (!missingList.isEmpty()) {
            SelectComponentsDialog dialog = new SelectComponentsDialog(pageContainer.getShell(), SWT.NONE,
                    tr._("Check missing components"),
                    tr._("The following missing components are required due to dependancy:"), missingList);

            List<ISisComponent> addList = dialog.open();
            if (addList == null)
                throw new QuietValidationException(tree);

            for (ISisComponent c : addList) {
                TreeItem item = (TreeItem) tree.getTreeItem(c);
                c.setSelected(true);
                QuadStateTreeItems.setState(item, ONE);
            }
        }

        for (Text dirText : dirTexts) {
            String varName = (String) dirText.getData();
            String path = dirText.getText();
            File dirFile = new File(path);
            if (dirFile.isFile())
                throw new GUIValidationException(dirText, tr._("file exists"));
            else if (!dirFile.exists()) {
                boolean confirmed = userDialogs.confirm(tr._("Create the directory?"),
                        tr._("CustomPage.confirmMkdir", dirFile));
                if (!confirmed) {
                    throw new QuietValidationException(dirText);
                } else if (!dirFile.mkdirs())
                    throw new GUIValidationException(dirText, tr._("Can\'t create ") + dirFile);
            }
            project.setValue(varName, dirFile);
        }
    }

}
