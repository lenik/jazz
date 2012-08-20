package net.bodz.swt.auto.vt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import net.bodz.swt.auto.AbstractVisualType;

public class VTColor
        extends AbstractVisualType {

    static class LabelMouseAdapter
            extends MouseAdapter {
        private Label label;

        public LabelMouseAdapter(Label label) {
            this.label = label;
        }

        @Override
        public void mouseDown(MouseEvent e) {
            ColorDialog cd = new ColorDialog(label.getShell());
            RGB rgb = cd.open();
            if (rgb == null)
                return;
            label.setBackground(new Color(label.getDisplay(), rgb));
            label.setText(rgb.toString());
            RGB inversedRgb = new RGB(255 - rgb.red, 255 - rgb.green, 255 - rgb.blue);
            label.setForeground(new Color(label.getDisplay(), inversedRgb));
        }
    }

    @Override
    public Control createControl(Composite parent) {
        Label label = new Label(parent, SWT.NONE);
        label.addMouseListener(new LabelMouseAdapter(label));
        return label;
    }

    @Override
    public Object capture(Control source, Object data) {
        assert source instanceof Label;
        assert data instanceof Color;

        Label label = (Label) source;
        Color bg = label.getBackground();
        RGB rgb = bg.getRGB();

        // TODO -

        // Color color = (Color) data;
        Device colorDevice = null; // color.getDevice();
        return new Color(colorDevice, rgb);
    }

    @Override
    public void render(Object data, Control target) {
        assert target instanceof Label;
        assert data instanceof Color;

        Label label = (Label) target;
        Color color = (Color) data;

        RGB rgb = color.getRGB();

        Color bg = label.getBackground();
        if (bg == null)
            bg = new Color(label.getDisplay(), rgb);
        else
            // bg = new Color(bg.getDevice(), rgb);
            bg = new Color(null, rgb);

        label.setBackground(bg);
    }

    @Override
    public Class<?>[] supportTypes() {
        return SUPPORT_TYPES;
    }

    static Class<?>[] SUPPORT_TYPES = { Color.class, };

}
