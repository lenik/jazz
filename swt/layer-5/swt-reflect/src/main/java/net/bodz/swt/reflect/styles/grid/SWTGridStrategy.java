package net.bodz.swt.reflect.styles.grid;

import static net.bodz.swt.nls.GUINLS.GUINLS;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.reflect.*;
import net.bodz.swt.reflect.GUIStructs.GUICallMeta;
import net.bodz.swt.reflect.GUIStructs.ParameterMeta;
import net.bodz.swt.reflect.GUIStructs.RetvalMeta;
import net.bodz.swt.reflect.GUIVars.GUIFieldMeta;
import net.bodz.swt.reflect.GUIVars.GUIPropertyMeta;
import net.bodz.swt.reflect.styles.base.SWTStrategy;
import net.bodz.swt.resources.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class SWTGridStrategy
        extends SWTStrategy {

    private static final long serialVersionUID = -6476584130668546414L;

    protected static class GridConfig
            extends Config {

        public int margin = 3;

        public static GridConfig getDefault() {
            return instance;
        }

        private static GridConfig instance = new GridConfig();

    }

    protected final GridConfig config;

    public SWTGridStrategy(GridConfig config) {
        this.config = config;
    }

    public SWTGridStrategy() {
        this(GridConfig.getDefault());
    }

    @Override
    protected void setup() {
        super.setup();
        put(Object.class, new R_Object(this));
        put(CallObject.class, new R_CallObject(this));
    }

    private static MIcon fieldIcons;
    private static MIcon propertyIcons;
    private static MIcon methodIcons;
    private static MIcon parameterIcons;
    private static MIcon retvalIcons;
    static {
        fieldIcons = new MIcon(
                //
                "/icons/full/obj16/field_default_obj.gif", "/icons/full/obj16/field_public_obj.gif",
                "/icons/full/obj16/field_protected_obj.gif", "/icons/full/obj16/field_private_obj.gif");
        methodIcons = new MIcon(
                //
                "/icons/full/obj16/methdef_obj.gif", "/icons/full/obj16/methpub_obj.gif",
                "/icons/full/obj16/methpro_obj.gif", "/icons/full/obj16/methpri_obj.gif");
        propertyIcons = methodIcons;
        parameterIcons = new MIcon("/icons/full/eview16/variable_view.gif");
        retvalIcons = new MIcon("/icons/full/obj16/field_default_obj.gif");
    }

    public Composite renderStruct(final SWTRenderContext rc, GUIStruct struct, Composite parent, int style)
            throws RenderException, SWTException {
        Composite grid = new Composite(parent, style);
        // icon label control
        GridLayout gridLayout = new GridLayout(3, false);
        grid.setLayout(gridLayout);
        for (GUIVar<?> childvar : struct)
            renderChild(rc, grid, childvar);
        return grid;
    }

    void renderChild(final SWTRenderContext rc, Composite grid, GUIVar<?> var)
            throws RenderException, SWTException {
        GUIVarMeta meta = var.getMeta();
        String name = meta.getName();
        GUIHint hint = meta.getHint();

        // Column #1
        Label iconLabel = new Label(grid, SWT.NONE);
        try {
            Image icon = hint == null ? null : hint.getIcon();
            if (icon == null) {
                String iconPath;
                int modifiers = meta.getModifiers();
                if (meta instanceof GUIFieldMeta)
                    iconPath = fieldIcons.getMod(modifiers);
                else if (meta instanceof GUIPropertyMeta)
                    iconPath = propertyIcons.getMod(modifiers);
                else if (meta instanceof GUICallMeta)
                    iconPath = methodIcons.getMod(modifiers);
                else if (meta instanceof ParameterMeta)
                    iconPath = parameterIcons.getMod(modifiers);
                else if (meta instanceof RetvalMeta)
                    iconPath = retvalIcons.getMod(modifiers);
                else
                    iconPath = config.defaultIcon;
                icon = SWTResources.getImageRes(iconPath);
            }
            iconLabel.setImage(icon);
        } catch (CreateException e) {
            throw new RenderException(GUINLS.getString("SWTGridStrategy.failedToRenderIcon"), e);
        }

        // Column #2
        Label label = new Label(grid, SWT.NONE);
        String labelString = hint == null ? null : hint.getLabel();
        if (labelString == null)
            labelString = name;
        if (labelString != null)
            label.setText(labelString);

        // Column #3
        Control child;
        child = SWTGridStrategy.this.render(rc, var, grid, styleFx(SWT.NONE, hint));

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

        rc.addEffects(child, var);
    }

}
