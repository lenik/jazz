package net.bodz.dist.ins;

import net.bodz.bas.types.TreePath;
import net.bodz.bas.ui.UIException;
import net.bodz.swt.gui.pfl.Book;
import net.bodz.swt.gui.pfl.NavigatorComposite;
import net.bodz.swt.gui.pfl.Page;
import net.bodz.swt.gui.pfl.SimpleBook;

import org.eclipse.swt.widgets.Composite;

/**
 * @test {@link InstallCompositeTest}
 */
public class InstallComposite extends NavigatorComposite {

    protected final ISession session;

    public InstallComposite(ISession session, Composite parent, int style) throws UIException {
        super(createBook(session), parent, style);
        this.session = session;
        // Pre-load the progress page.
        // progressPage = (ProgressPage) getPageFlow().getPage("progress");
        // if (progressPage == null)
        // throw new UIException("Failed to load progress page");
    }

    static Book createBook(final ISession session) throws UIException {
        SimpleBook book = new SimpleBook();
        book.add(SummaryPage.class, new SummaryPage(session));
        book.add(ProgressPage.class, new ProgressPage(session));
        book.add(DonePage.class, new DonePage(session));
        book.add(CanceledPage.class, new CanceledPage(session));

        Project start = session.getProject();
        String startId = ConfigPage.getNextConfigId(session, start.getId());
        String startPath = ConfigPage.CPrefix + startId;
        book.setFirst(startPath);

        class ConfigBook extends SimpleBook {

            public ConfigBook(Book next) {
                super(next);
            }

            @Override
            protected boolean _contains(TreePath path) {
                return _getPage(path) != null;
            }

            @Override
            protected Page _getPage(TreePath path) {
                Page page = super._getPage(path);
                if (page == null) {
                    String s = path.getPath();
                    if (s.startsWith(ConfigPage.CPrefix)) {
                        String id = s.substring(ConfigPage.CPrefix.length());
                        Component c = session.getComponent(id);
                        if (c == null)
                            throw new NullPointerException("null component: " + id);
                        Scheme scheme = session.getScheme();
                        if (c.hasConfig())
                            if (scheme == null || scheme.showConfig(c)) {
                                page = c.createConfig(session);
                                super.add(path, page);
                            }
                    }
                }
                return page;
            }

        }

        Book configBook = new ConfigBook(book);
        return configBook;
    }

}
