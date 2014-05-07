package net.bodz.bas.ui.style;

import java.util.Map;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.i18n.unit.std.ILength;
import net.bodz.bas.ui.css3.ICss3StyleDeclaration;
import net.bodz.bas.ui.css3.MappedCss3StyleDeclaration;
import net.bodz.bas.ui.css3.property.FontUseMode;
import net.bodz.bas.ui.style.property.LineCapMode;
import net.bodz.bas.ui.style.property.LineJoinMode;

public abstract class MappedUiElementStyleDeclaration
        extends MappedCss3StyleDeclaration
        implements IUiElementStyleDeclaration, IFontType, IStrokeType, IFillType {

    private static final long serialVersionUID = 1L;

    public MappedUiElementStyleDeclaration() {
        super();
    }

    public MappedUiElementStyleDeclaration(Map<String, String> _orig) {
        super(_orig);
    }

    /** ⇱ Implementation Of {@link IFontType}. */
    /* _____________________________ */static section.iface __FONT_TYPE__;

    @Override
    public FontUseMode getFontUse() {
        return parseEnum(get("font-use"), FontUseMode.class);
    }

    public void setFontUse(FontUseMode mode) {
        set("font-use", mode);
    }

    /** ⇱ Implementation Of {@link IStrokeType}. */
    /* _____________________________ */static section.iface __STROKE_TYPE__;

    @Override
    public ILength getLineWidth() {
        return parseCssLength(get("line-width"));
    }

    public void setLineWidth(ILength lineWidth) {
        set("line-width", lineWidth);
    }

    @Override
    public LineCapMode getLineCap() {
        return parseEnum(get("line-cap"), LineCapMode.class);
    }

    public void setLineCap(LineCapMode lineCap) {
        set("line-cap", lineCap);
    }

    @Override
    public LineJoinMode getLineJoin() {
        return parseEnum(get("line-join"), LineJoinMode.class);
    }

    public void setLineJoin(LineJoinMode lineJoin) {
        set("line-join", lineJoin);
    }

    @Override
    public int[] getDashPattern() {
        String str = get("dash-pattern");
        if (str == null)
            return null;
        String[] sv = str.split(",");
        int[] iv = new int[sv.length];
        for (int i = 0; i < sv.length; i++)
            iv[i] = Integer.parseInt(sv[i]);
        return iv;
    }

    public void setDashPattern(int[] dashPattern) {
        String str;
        if (dashPattern == null)
            str = null;
        else
            str = StringArray.join(",", dashPattern);
        set("dash-pattern", str);
    }

    /** ⇱ Implementation Of {@link IFillType}. */
    /* _____________________________ */static section.iface __FILL_TYPE__;

    // TODO
    @Override
    public FillPatternType getFillPatternType() {
        return parseEnum(get("fill-pattern-type"), FillPatternType.class);
    }

    // TODO
    @Override
    public String getFillPattern() {
        return get("fill-pattern");
    }

    // TODO
    public void setFillPattern(FillPatternType fillPatternType, String fillPattern) {
        set("fill-pattern", fillPatternType + " " + fillPattern);
    }

    /** ⇱ Implementation Of {@link IUiElementStyleDeclaration}. */
    /* _____________________________ */static section.iface __GUI_STYLE__;

    @Override
    public IUiElementStyleDeclaration getParent() {
        return (IUiElementStyleDeclaration) super.getParent();
    }

    @Override
    public void setParent(ICss3StyleDeclaration parent) {
        if (parent != null)
            if (!(parent instanceof IUiElementStyleDeclaration))
                throw new IllegalArgumentException("Invalid parent: " + parent);
        super.setParent(parent);
    }

    @Override
    public IFontType getFontType() {
        return this;
    }

    @Override
    public void setFontType(IFontType fontType) {
        if (fontType == null) {
            setFontUse(null);
            setFontFamily(null);
            setFontSize(null);
            setFontStyle(null);
            setFontVariant(null);
            setFontWeight(null);
            setTextDecoration(null);
        } else {
            setFontUse(fontType.getFontUse());
            setFontFamily(fontType.getFontFamily());
            setFontSize(fontType.getFontSize());
            setFontStyle(fontType.getFontStyle());
            setFontVariant(fontType.getFontVariant());
            setFontWeight(fontType.getFontWeight());
            setTextDecoration(fontType.getTextDecoration());
        }
    }

    @Override
    public IStrokeType getStrokeType() {
        return this;
    }

    @Override
    public void setStrokeType(IStrokeType strokeType) {
        if (strokeType == null) {
            setLineWidth(null);
            setLineCap(null);
            setLineJoin(null);
            setDashPattern(null);
        } else {
            setLineWidth(strokeType.getLineWidth());
            setLineCap(strokeType.getLineCap());
            setLineJoin(strokeType.getLineJoin());
            setDashPattern(strokeType.getDashPattern());
        }
    }

    @Override
    public IFillType getFillType() {
        return this;
    }

    @Override
    public void setFillType(IFillType fillType) {
        if (fillType == null) {
            setFillPattern(null, null);
        } else {
            setFillPattern(fillType.getFillPatternType(), fillType.getFillPattern());
        }
    }

    @Override
    public IImageData getImage(ImageUsage usage) {
        throw new NotImplementedException();
    }

    @Override
    public void setImage(ImageUsage usage, IImageData imageData) {
        throw new NotImplementedException();
    }

    @Override
    public String getGroup() {
        return get("place");
    }

    /** ⇱ Implementaton Of {@link IInputBehaviorDeclaration}. */
    /* _____________________________ */static section.iface __INPUT_BEHAVIOR__;

    @Override
    public Boolean getEnabled() {
        return parseBoolean(get("enabled"));
    }

    @Override
    public void setEnabled(Boolean enabled) {
        set("enabled", enabled);
    }

    @Override
    public Boolean getReadOnly() {
        return parseBoolean(get("read-only"));
    }

    @Override
    public void setReadOnly(Boolean readOnly) {
        set("read-only", readOnly);
    }

    @Override
    public Integer getTabOrder() {
        return parseInteger(get("tab-order"));
    }

    @Override
    public void setTabOrder(Integer tabOrder) {
        set("tab-order", tabOrder);
    }

    @Override
    public Integer getMaxLength() {
        return parseInteger(get("max-length"));
    }

    @Override
    public void setMaxLength(Integer maxLength) {
        set("max-length", maxLength);
    }

    @Override
    public Character getEchoChar() {
        return parseChar(get("echo-char"));
    }

    @Override
    public void setEchoChar(Character echoChar) {
        set("echo-char", echoChar);
    }

}
