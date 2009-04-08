package net.bodz.dist.ins;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.a.DisplayName;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.gui.GUIException;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.dist.ins.builtins.ChooseSchemePage;
import net.bodz.dist.ins.builtins.CustomPage;
import net.bodz.dist.ins.builtins.LicensePage;
import net.bodz.dist.ins.builtins.LogoPage;
import net.bodz.swt.gui.BasicGUI;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageFactory;
import net.bodz.swt.gui.pfl.SymlinkPageFlow;
import net.bodz.swt.gui.pfl.WizardComposite;
import net.bodz.swt.gui.pfl.WizardExitEvent;
import net.bodz.swt.gui.pfl.WizardExitListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

@DisplayName("boDz Product Installer")
// @Icon("icon.gif")
@PreferredSize(width = 500, height = 400)
@RcsKeywords(id = "$Id: QuickTone.java 8 2009-03-29 09:01:53Z Shecti $")
@Version( { 1, 0 })
public class Installer extends BasicGUI {

    @Option(alias = "c")
    Class<?>                majorClass;

    IProject                project;
    ISession                session;

    private WizardComposite wizard;

    public Installer() {
    }

    public Installer(IProject project) {
        this.project = project;
    }

    @Override
    protected void _boot() throws Throwable {
        if (majorClass != null) {
            if (project == null) {
                _Project projByClass = new _Project(majorClass);
                this.project = projByClass;
            }
        }
        if (project == null)
            throw new IllegalUsageError("no config specified");
    }

    @Override
    protected void createInitialView(final Composite comp) throws GUIException,
            SWTException {
        wizard = new WizardComposite(comp, SWT.NONE, false);
        wizard.definePage("logo", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                ClassInfo installInfo = Installer.this._loadClassInfo();
                return new LogoPage(session, parent, SWT.NONE, installInfo);
            }
        });
        wizard.definePage("license", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new LicensePage(session, parent, SWT.NONE);
            }
        });
        wizard.definePage("type", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new ChooseSchemePage(session, parent, SWT.NONE);
            }
        });
        wizard.definePage("components", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new CustomPage(session, parent, SWT.NONE);
            }
        });
        wizard.definePage("progress", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new ProgressPage(session, parent, SWT.NONE);
            }
        });
        wizard.definePage("done", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new DonePage(session, parent, SWT.NONE);
            }
        });
        wizard.definePage("canceled", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new CanceledPage(session, parent, SWT.NONE);
            }
        });
        wizard.addExitListener(new WizardExitListener() {
            @Override
            public void wizardExit(WizardExitEvent e) {
                comp.getShell().dispose();
            }
        });
        SymlinkPageFlow pageFlow = wizard.getPageFlow();
        URL sfl = Files.classData(getClass(), "sfl");
        try {
            String xml = Files.readAll(sfl, "utf-8");
            pageFlow.loadXML(xml);
        } catch (IOException e) {
            throw new GUIException(e);
        } catch (ParseException e) {
            throw new GUIException(e);
        }
        pageFlow.set("logo");
    }

    @Override
    protected String getTitle() {
        String caption = project.getCaption();
        String version = project.getVersion();
        return caption + " Installer " + version;
    }

    @Override
    protected String getCopyrightString() {
        ClassInfo info = _loadClassInfo();
        return "boDz Product Installer " + info.getVersionString();
    }

    public static void main(String[] args) throws Throwable {
        new Installer().run(args);
    }

}
