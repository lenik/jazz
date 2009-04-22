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

    public InstallComposite(ISession session, Composite parent, int style)
            throws UIException {
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
        pageFlow.putLink("summary/next", "progress");
        pageFlow.putLink("progress/next", "done");
        pageFlow.putLink("progress/cancel", "canceled");

        Project start = session.getProject();
        pageFlow.putLink("start", new NextPage(start));
        pageFlow.set("start");
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

        TreePosition<Component> start;

        public NextPage(Component root) {
            this(new TreePosition<Component>(root, 0));
        }

        public NextPage(TreePosition<Component> start) {
            this.start = start;
        }

        /**
         * {@link NextPage#toString()} is evaluated when dereference the symlink
         * to the next page.
         */
        @Override
        public String toString() {
            LinkedStack<TreePosition<Component>> posBuf = new LinkedStack<TreePosition<Component>>();
            Iterator<Component> iter = start.iterator(hasConfigPred, posBuf);
            iter.next(); // skip start itself.
            posBuf.pop(); // skip start address
            if (!iter.hasNext())
                return "summary";

            final Component nextComponent = iter.next();
            final TreePosition<Component> nextPosition = posBuf.pop();
            String pageId = "Config(" + nextComponent.getId() + ")";

            if (!isPageDefined(pageId))
                definePage(pageId, new PageFactory() {
                    @Override
                    public PageComposite create(Composite parent) {
                        return nextComponent.createConfig(session, parent,
                                SWT.NONE);
                    }
                });

            SymlinkPageFlow pageFlow = getPageFlow();
            pageFlow.putLink(pageId, "next", new NextPage(nextPosition));
            return pageId;
        }

    }

}
