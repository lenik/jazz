package net.bodz.swt.gui.style;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

public abstract class AbstractSwtFont
        extends SwtFontDataMapper {

    private static final long serialVersionUID = 1L;

    private Device device;

    public AbstractSwtFont(Device device) {
        if (device == null)
            throw new NullPointerException("device");
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    public abstract Font getSwtFont();

    public abstract void setSwtFont(Font font);

    @Override
    public FontData getFontData() {
        Font font = getSwtFont();
        FontData[] fontDataArray = font.getFontData();
        return fontDataArray[0];
    }

    @Override
    public void setFontData(FontData fontData) {
        if (fontData == null)
            throw new NullPointerException("fontData");
        Font font = new Font(device, fontData);
        setSwtFont(font);
    }

}
