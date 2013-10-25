package net.bodz.pkg.sisi.page;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.swt.c.pageflow.IBook;
import net.bodz.swt.c.pageflow.IPage;
import net.bodz.swt.c.pageflow.NavigatorComposite;
import net.bodz.swt.c.pageflow.SimpleBook;

public class SisiNavigator
        extends NavigatorComposite {

    protected final ISisProject project;

    public SisiNavigator(ISisProject project, Composite parent, int style)
            throws GUIException {
        super(createBook(project), parent, style);
        this.project = project;

        // Pre-load the progress page.
        // progressPage = (ProgressPage) getPageFlow().getPage("progress");
        // if (progressPage == null)
        // throw new UIException("Failed to load progress page");
    }

    static IBook createBook(final ISisProject project)
            throws GUIException {
        SimpleBook book = new SimpleBook();
        book.add(SummaryPage.class, new SummaryPage(project));
        book.add(ProgressPage.class, new ProgressPage(project));
        book.add(DonePage.class, new DonePage(project));
        book.add(CanceledPage.class, new CanceledPage(project));

        book.setFirst(project.getFollowing(project).getId());

        class ConfigBook
                extends SimpleBook {

            public ConfigBook(IBook next) {
                super(next);
            }

            @Override
            protected boolean _contains(PathEntries path) {
                return _getPage(path) != null;
            }

            @Override
            protected IPage _getPage(PathEntries pagePath) {
                IPage page = super._getPage(pagePath);
                if (page != null)
                    return page;

                String pathStr = pagePath.getPath();

                if (pathStr.startsWith(ConfigPage.CPrefix)) {
                    String id = pathStr.substring(ConfigPage.CPrefix.length());
                    ISisComponent component = project.getComponent(id);
                    if (component == null)
                        throw new NullPointerException("null component: " + id);

                    if (component.isSelected() && component.isShowConfig()) {
                        page = component.getConfigPage();
                        super.add(pagePath, page);
                    }
                }
                return page;
            }

        }

        IBook configBook = new ConfigBook(book);
        return configBook;
    }

}
