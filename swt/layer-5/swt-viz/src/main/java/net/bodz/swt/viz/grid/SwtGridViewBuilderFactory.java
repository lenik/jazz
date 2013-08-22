package net.bodz.swt.viz.grid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.gui.style.IImageData;
import net.bodz.bas.gui.style.ImageUsage;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IRefcomp;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.gui.style.SwtImageMapper;
import net.bodz.swt.viz.AbstractSwtViewBuilderFactory;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.form.vbo.InvocationVbo;
import net.bodz.swt.viz.util.ModifierIcon;

public class SwtGridViewBuilderFactory
        extends AbstractSwtViewBuilderFactory {

    protected final GridConfig config;

    public SwtGridViewBuilderFactory(GridConfig config) {
        this.config = config;
    }

    public SwtGridViewBuilderFactory() {
        this(GridConfig.getDefault());
    }

    @Override
    protected void setup() {
        super.setup();
        typeMap.put(Object.class, new ObjectVbo(this));
        typeMap.put(Invocation.class, new InvocationVbo(this));
    }

    private static ModifierIcon fieldIcons;
    private static ModifierIcon propertyIcons;
    private static ModifierIcon methodIcons;
    private static ModifierIcon parameterIcons;
    private static ModifierIcon retvalIcons;
    static {
        fieldIcons = new ModifierIcon(
                //
                "/icons/full/obj16/field_default_obj.gif", "/icons/full/obj16/field_public_obj.gif",
                "/icons/full/obj16/field_protected_obj.gif", "/icons/full/obj16/field_private_obj.gif");
        methodIcons = new ModifierIcon(
                //
                "/icons/full/obj16/methdef_obj.gif", "/icons/full/obj16/methpub_obj.gif",
                "/icons/full/obj16/methpro_obj.gif", "/icons/full/obj16/methpri_obj.gif");
        propertyIcons = methodIcons;
        parameterIcons = new ModifierIcon("/icons/full/eview16/variable_view.gif");
        retvalIcons = new ModifierIcon("/icons/full/obj16/field_default_obj.gif");
    }

    public Composite renderStruct(final SwtRenderContext rc, IRefcomp<?> struct, Composite parent, int _style)
            throws ViewBuilderException, SWTException {
        Composite grid = new Composite(parent, _style);
        // icon label control
        GridLayout gridLayout = new GridLayout(3, false);
        grid.setLayout(gridLayout);
        for (IRefEntry<?> ent : struct.getRefEntries()) {
            ISwtControlStyleDeclaration styleDecl = null;
            if (ent instanceof ISwtGUIRefEntry<?>)
                styleDecl = ((ISwtGUIRefEntry<?>) ent).getStyle();
            _renderChild(rc, grid, ent, styleDecl);
        }
        return grid;
    }

    void _renderChild(final SwtRenderContext rc, Composite grid, IRefEntry<?> entry,
            ISwtControlStyleDeclaration styleDecl)
            throws ViewBuilderException, SWTException {
        String name = entry.getName();

        // Column #1
        Label iconLabel = new Label(grid, SWT.NONE);

        Image iconImage = null;
        IImageData iconImageData = styleDecl == null ? null : styleDecl.getImage(ImageUsage.NORMAL);
        Display device = grid.getDisplay();

        try {
            if (iconImageData != null) {
                ImageData _imageData = SwtImageMapper.convert(iconImageData);
                iconImage = new Image(device, _imageData);
            } else {
                String iconPath;
                int modifiers = entry.getModifiers();
                if (descriptor instanceof GUIFieldMeta)
                    iconPath = fieldIcons.getMod(modifiers);
                else if (descriptor instanceof GUIPropertyMeta)
                    iconPath = propertyIcons.getMod(modifiers);
                else if (descriptor instanceof GUICallMeta)
                    iconPath = methodIcons.getMod(modifiers);
                else if (descriptor instanceof ParameterMeta)
                    iconPath = parameterIcons.getMod(modifiers);
                else if (descriptor instanceof RetvalMeta)
                    iconPath = retvalIcons.getMod(modifiers);
                else
                    iconPath = config.defaultIcon;

                iconImage = SWTResources.getImageRes(iconPath);
            }
        } catch (CreateException e) {
            throw new ViewBuilderException(tr._("Failed to render icon"), e);
        }
        iconLabel.setImage(iconImage);

        // Column #2
        Label label = new Label(grid, SWT.NONE);
        iString iLabel = entry.getLabel();
        if (iLabel == null)
            label.setText(name);
        else
            label.setText(iLabel.toString());

        // Column #3
        Control child;
        child = SwtGridViewBuilderFactory.this.render(rc, entry, grid, styleFx(SWT.NONE, styleDecl));

        Point iconz = iconLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Point labelz = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Point childz = child.computeSize(SWT.DEFAULT, SWT.DEFAULT);

        GridData icond = new GridData(SWT.CENTER, SWT.TOP, false, false);
        GridData labeld = new GridData(SWT.LEFT, SWT.TOP, false, false);
        GridData childd = new GridData(SWT.FILL, SWT.TOP, true, false);

        int labelH = Math.max(iconz.y, labelz.y);
        if (labelH >= childz.y) {
            icond.verticalAlignment = SWT.CENTER;
            labeld.verticalAlignment = SWT.CENTER;
            childd.verticalAlignment = SWT.CENTER;
        }
        iconLabel.setLayoutData(icond);
        label.setLayoutData(labeld);
        child.setLayoutData(childd);

        rc.addEffects(child, styleDecl);
    }

}
