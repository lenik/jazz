package net.bodz.swt.gui;

import java.util.zip.CRC32;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

abstract class _WidgetCreator implements WidgetCreator {
}

class TextBuilder extends _WidgetCreator {

    @Override
    public Text create(Composite parent, int style) {
        return new Text(parent, style);
    }

}

public class WidgetCreators {
    
}
