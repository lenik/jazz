package net.bodz.dist.ins;

import java.util.Iterator;

import net.bodz.bas.lang.Pred1;
import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.TreePosition;
import net.bodz.bas.ui.UIException;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageFactory;
import net.bodz.swt.gui.pfl.SymlinkPageFlow;
import net.bodz.swt.gui.pfl.WizardComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @test {@link InstallCompositeTest}
 */
public class InstallComposite extends WizardComposite {

    protected final ISession session;

    public InstallComposite(ISession session, Composite parent, int style) throws UIException {
        super(parent, style, false);
        this.session = session;
        createPages();
        // Pre-load the progress page.
        // progressPage = (ProgressPage) getPageFlow().getPage("progress");
        // if (progressPage == null)
        // throw new UIException("Failed to load progress page");
    }

    void createPages() throws UIException {
        definePage("summary", new PageFactory() {//$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return new SummaryPage(session, parent, SWT.NONE);
                    }
                });
        definePage("progress", new PageFactory() { //$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return new ProgressPage(session, parent, SWT.NONE);
                    }
                });
        definePage("done", new PageFactory() { //$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return new DonePage(session, parent, SWT.NONE);
                    }
                });
        definePage("canceled", new PageFactory() { //$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return new CanceledPage(session, parent, SWT.NONE);
                    }
                });
        SymlinkPageFlow pageFlow = getPageFlow();
        pageFlow.putLink("summary/next", "progress"); //$NON-NLS-1$ //$NON-NLS-2$
        pageFlow.putLink("progress/next", "done"); //$NON-NLS-1$ //$NON-NLS-2$
        pageFlow.putLink("progress/cancel", "canceled"); //$NON-NLS-1$ //$NON-NLS-2$

        Project start = session.getProject();
        pageFlow.putLink("start", new NextPage(start)); //$NON-NLS-1$
        pageFlow.set("start"); //$NON-NLS-1$
    }

    class HasConfigPred extends Pred1<Component> {
        @Override
        public boolean test(Component a) {
            if (a.hasConfig()) {
                Scheme scheme = session.getScheme();
                if (scheme == null || scheme.showConfig(a))
                    return true;
            }
            return false;
        }
    }

    final HasConfigPred hasConfigPred = new HasConfigPred();

    class NextPage {

        final Component               startComponent;
        final TreePosition<Component> startPosition;

        public NextPage(Component startComponent) {
            this(startComponent, new TreePosition<Component>(startComponent, 0));
        }

        public NextPage(Component startComponent, TreePosition<Component> start) {
            this.startComponent = startComponent;
            this.startPosition = start;
        }

        /**
         * {@link NextPage#toString()} is evaluated when dereference the symlink
         * to the next page.
         */
        @Override
        public String toString() {
            LinkedStack<TreePosition<Component>> posBuf = new LinkedStack<TreePosition<Component>>();
            Iterator<Component> iter = startPosition.iterator(hasConfigPred, posBuf);
            if (!iter.hasNext())
                return "summary"; //$NON-NLS-1$
            Component nextComponent = iter.next();
            TreePosition<Component> nextPosition = posBuf.pop();
            if (nextComponent == startComponent) { // start is a page-node, skip
                // start.
                if (!iter.hasNext())
                    return "summary"; //$NON-NLS-1$
                nextComponent = iter.next();
                nextPosition = posBuf.pop();
            }

            String nextPageId = "Config(" + nextComponent.getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            if (!isPageDefined(nextPageId)) {
                final Component c = nextComponent;
                definePage(nextPageId, new PageFactory() {
                    @Override
                    public PageComposite create(Composite parent) {
                        return c.createConfig(session, parent, SWT.NONE);
                    }
                });
            }

            SymlinkPageFlow pageFlow = getPageFlow();
            pageFlow.putLink(nextPageId, "next", new NextPage(nextComponent, nextPosition)); //$NON-NLS-1$
            return nextPageId;
        }

    }

}
