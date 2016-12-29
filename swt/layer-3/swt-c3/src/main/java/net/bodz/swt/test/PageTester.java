package net.bodz.swt.test;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.swt.c.pageflow.*;
import net.bodz.swt.c.test.WidgetTester;

public class PageTester
        extends WidgetTester {

    private NavigatorComposite navigator;
    private SimpleBook book;

    static final PathEntries START = new PathEntries("start");
    static final PathEntries DEBUG = new PathEntries("debug");

    public PageTester() {
        book = new SimpleBook();
        book.setFirst(START);
        book.add(DEBUG, new DebugPage());
        book.addMethod(new PageMethod(DEBUG.getPath(), "Debug"));
    }

    public PageTester(IPage pageToTest) {
        this();
        setTestPage(pageToTest);
    }

    public void setTestPage(IPage pageToTest) {
        book.add(START, pageToTest);
    }

    @Override
    public void run() {
        navigator = new NavigatorComposite(book, body, SWT.NONE);
        navigator.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                System.out.println("Exit from: " + e.path);
                shell.dispose();
            }
        });
        super.run();
    }

    static class DebugPage
            extends AbstractPage {

        Label prevLabel;
        Label infoLabel;
        Text text;

        public DebugPage() {
            addMethod(new PageMethod("quit"));
        }

        @Override
        protected void createContents(Composite holder) {
            holder.setLayout(new GridLayout(1, false));
            prevLabel = new Label(holder, SWT.NONE);
            prevLabel.setText(tr._("Where do you come from?"));

            infoLabel = new Label(holder, SWT.NONE);

            text = new Text(holder, SWT.BORDER | SWT.MULTI | SWT.READ_ONLY | SWT.V_SCROLL);
            GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
            text.setLayoutData(gridData);
        }

        @Override
        public PathEntries service(ServiceContext context)
                throws PageException {
            IBook book = context.getPageContext().getBook();
            PathEntries referrer = context.getReferrerPath();
            prevLabel.setText(GUINLS.format("PageTestApp.youFromA_s", referrer));

            // debug request must come from an existing page.
            if (referrer == null)
                throw new NullPointerException("referrer");

            IPage referrerPage = book.getPage(referrer);
            if (referrerPage == null)
                throw new IllegalUsageException(tr._("bad address: ") + referrer);

            String type = referrerPage.getClass().getName();
            int hash = System.identityHashCode(referrerPage);
            infoLabel.setText(tr._("Page(") + type + ") @" + Integer.toHexString(hash) + ": ");

            String s = referrerPage.toString();
            text.setText(s);
            return null;
        }

    }

}
