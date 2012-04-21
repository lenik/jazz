package net.bodz.swt.widgets.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Syscolors {

    public final Color white;
    public final Color black;
    public final Color red;
    public final Color darkRed;
    public final Color green;
    public final Color darkGreen;
    public final Color yellow;
    public final Color darkYellow;
    public final Color blue;
    public final Color darkBlue;
    public final Color magenta;
    public final Color darkMagenta;
    public final Color cyan;
    public final Color darkCyan;
    public final Color gray;
    public final Color darkGray;

    public final Color widgetDarkShadow;
    public final Color widgetNormalShadow;
    public final Color widgetLightShadow;
    public final Color widgetHighlightShadow;
    public final Color widgetForeground;
    public final Color widgetBackground;
    public final Color widgetBorder;
    public final Color listForeground;
    public final Color listBackground;
    public final Color listSelection;
    public final Color listSelectionText;
    public final Color infoForeground;
    public final Color infoBackground;
    public final Color titleForeground;
    public final Color titleBackground;
    public final Color titleBackgroundGradient;
    public final Color titleInactiveForeground;
    public final Color titleInactiveBackground;
    public final Color titleInactiveBackgroundGradient;

    public Syscolors(Display display) {
        if (display == null)
            throw new NullPointerException("display");

        white = display.getSystemColor(SWT.COLOR_WHITE);
        black = display.getSystemColor(SWT.COLOR_BLACK);
        red = display.getSystemColor(SWT.COLOR_RED);
        darkRed = display.getSystemColor(SWT.COLOR_DARK_RED);
        green = display.getSystemColor(SWT.COLOR_GREEN);
        darkGreen = display.getSystemColor(SWT.COLOR_DARK_GREEN);
        yellow = display.getSystemColor(SWT.COLOR_YELLOW);
        darkYellow = display.getSystemColor(SWT.COLOR_DARK_YELLOW);
        blue = display.getSystemColor(SWT.COLOR_BLUE);
        darkBlue = display.getSystemColor(SWT.COLOR_DARK_BLUE);
        magenta = display.getSystemColor(SWT.COLOR_MAGENTA);
        darkMagenta = display.getSystemColor(SWT.COLOR_DARK_MAGENTA);
        cyan = display.getSystemColor(SWT.COLOR_CYAN);
        darkCyan = display.getSystemColor(SWT.COLOR_DARK_CYAN);
        gray = display.getSystemColor(SWT.COLOR_GRAY);
        darkGray = display.getSystemColor(SWT.COLOR_DARK_GRAY);

        widgetDarkShadow = display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
        widgetNormalShadow = display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
        widgetLightShadow = display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
        widgetHighlightShadow = display.getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW);
        widgetForeground = display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
        widgetBackground = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
        widgetBorder = display.getSystemColor(SWT.COLOR_WIDGET_BORDER);
        listForeground = display.getSystemColor(SWT.COLOR_LIST_FOREGROUND);
        listBackground = display.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
        listSelection = display.getSystemColor(SWT.COLOR_LIST_SELECTION);
        listSelectionText = display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT);
        infoForeground = display.getSystemColor(SWT.COLOR_INFO_FOREGROUND);
        infoBackground = display.getSystemColor(SWT.COLOR_INFO_BACKGROUND);
        titleForeground = display.getSystemColor(SWT.COLOR_TITLE_FOREGROUND);
        titleBackground = display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND);
        titleBackgroundGradient = display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT);
        titleInactiveForeground = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND);
        titleInactiveBackground = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);
        titleInactiveBackgroundGradient = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);
    }

}
