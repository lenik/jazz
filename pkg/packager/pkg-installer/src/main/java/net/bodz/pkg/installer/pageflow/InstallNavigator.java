package net.bodz.pkg.installer.pageflow;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.pkg.installer.IComponent;
import net.bodz.pkg.installer.IProject;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.IScheme;
import net.bodz.swt.c.pageflow.IBook;
import net.bodz.swt.c.pageflow.IPage;
import net.bodz.swt.c.pageflow.NavigatorComposite;
import net.bodz.swt.c.pageflow.SimpleBook;

public class InstallNavigator
        extends NavigatorComposite {

    protected final ISession session;

    public InstallNavigator(ISession session, Composite parent, int style)
            throws GUIException {
        super(createBook(session), parent, style);
        this.session = session;
        // Pre-load the progress page.
        // progressPage = (ProgressPage) getPageFlow().getPage("progress");
        // if (progressPage == null)
        // throw new UIException("Failed to load progress page");
    }

    static IBook createBook(final ISession session)
            throws GUIException {
        SimpleBook book = new SimpleBook();
        book.add(SummaryPage.class, new SummaryPage(session));
        book.add(ProgressPage.class, new ProgressPage(session));
        book.add(DonePage.class, new DonePage(session));
        book.add(CanceledPage.class, new CanceledPage(session));

        IProject start = session.getProject();
        String startId = ConfigPage.getNextConfigId(session, start.getId());
        String startPath = ConfigPage.CPrefix + startId;
        book.setFirst(startPath);

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
            protected IPage _getPage(PathEntries path) {
                IPage page = super._getPage(path);
                if (page == null) {
                    String s = path.getPath();
                    if (s.startsWith(ConfigPage.CPrefix)) {
                        String id = s.substring(ConfigPage.CPrefix.length());
                        IComponent c = session.getComponent(id);
                        if (c == null)
                            throw new NullPointerException("null component: " + id);
                        IScheme scheme = session.getScheme();
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

        IBook configBook = new ConfigBook(book);
        return configBook;
    }

}
