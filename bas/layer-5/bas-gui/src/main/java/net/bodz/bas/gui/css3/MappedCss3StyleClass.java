package net.bodz.bas.gui.css3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.i18n.unit.Measure;
import net.bodz.bas.t.pojo.Pair;

public abstract class MappedCss3StyleClass
        extends AbstractPropertiesMapper
        implements ICss3StyleClass {

    protected OffsetType getOffsetTypeProperty(String key, OffsetType inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        if ("auto".equals(str))
            return OffsetType.auto;

        String num = StringPart.peekDecimal(str);
        String suffix = str.substring(num.length()).trim();

        if ("%".equals(suffix))
            return OffsetType.percentage;
        else
            // assume suffix == unit.
            return OffsetType.length;
    }

    protected Measure getOffsetProperty(String key, Measure inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;

        switch (str) {
        case "auto":
        case "none":
            return Measure.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str).trim();
        return Measure.parse(str);
    }

    protected void setOffsetProperty(String key, OffsetType offsetType, Measure offset) {
        Object property = null;
        if (offsetType != null)
            switch (offsetType) {
            case length:
                property = offset;
                break;
            case percentage:
                property = offset + "%";
                break;
            case auto:
            case none:
            default:
                property = offsetType;
            }
        setProperty(key, property);
    }

    protected IColor getColorProperty(String key, IColor inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        return null;
    }

    @Override
    public OffsetType getWidthType() {
        return getOffsetTypeProperty("width", getParent().getWidthType(), false);
    }

    @Override
    public OffsetType getHeightType() {
        return getOffsetTypeProperty("height", getParent().getHeightType(), false);
    }

    @Override
    public Measure getWidth() {
        return getOffsetProperty("width", getParent().getWidth(), false);
    }

    @Override
    public Measure getHeight() {
        return getOffsetProperty("height", getParent().getHeight(), false);
    }

    @Override
    public void setWidth(OffsetType widthType, Measure width) {
        setOffsetProperty("width", widthType, width);
    }

    @Override
    public void setHeight(OffsetType heightType, Measure height) {
        setOffsetProperty("height", heightType, height);
    }

    @Override
    public OffsetType getTopType() {
        return getOffsetTypeProperty("top", getParent().getTopType(), false);
    }

    @Override
    public OffsetType getRightType() {
        return getOffsetTypeProperty("right", getParent().getRightType(), false);
    }

    @Override
    public OffsetType getBottomType() {
        return getOffsetTypeProperty("bottom", getParent().getBottomType(), false);
    }

    @Override
    public OffsetType getLeftType() {
        return getOffsetTypeProperty("left", getParent().getLeftType(), false);
    }

    @Override
    public Measure getTop() {
        return getOffsetProperty("top", getParent().getTop(), false);
    }

    @Override
    public Measure getRight() {
        return getOffsetProperty("right", getParent().getRight(), false);

    }

    @Override
    public Measure getBottom() {
        return getOffsetProperty("bottom", getParent().getBottom(), false);
    }

    @Override
    public Measure getLeft() {
        return getOffsetProperty("left", getParent().getLeft(), false);

    }

    @Override
    public void setTop(OffsetType topType, Measure top) {
        setOffsetProperty("top", topType, top);
    }

    @Override
    public void setRight(OffsetType rightType, Measure right) {
        setOffsetProperty("right", rightType, right);
    }

    @Override
    public void setBottom(OffsetType bottomType, Measure bottom) {
        setOffsetProperty("bottom", bottomType, bottom);
    }

    @Override
    public void setLeft(OffsetType leftType, Measure left) {
        setOffsetProperty("left", leftType, left);
    }

    @Override
    public OffsetType getMinWidthType() {
        return getOffsetTypeProperty("min-width", getParent().getMinWidthType(), false);
    }

    @Override
    public OffsetType getMaxWidthType() {
        return getOffsetTypeProperty("max-width", getParent().getMaxWidthType(), false);
    }

    @Override
    public OffsetType getMinHeightType() {
        return getOffsetTypeProperty("min-height", getParent().getMinHeightType(), false);
    }

    @Override
    public OffsetType getMaxHeightType() {
        return getOffsetTypeProperty("max-height", getParent().getMaxHeightType(), false);
    }

    @Override
    public Measure getMinWidth() {
        return getOffsetProperty("min-width", getParent().getMinWidth(), false);
    }

    @Override
    public Measure getMaxWidth() {
        return getOffsetProperty("max-width", getParent().getMaxWidth(), false);
    }

    @Override
    public Measure getMinHeight() {
        return getOffsetProperty("min-height", getParent().getMinHeight(), false);
    }

    @Override
    public Measure getMaxHeight() {
        return getOffsetProperty("max-height", getParent().getMaxHeight(), false);
    }

    @Override
    public void setMinWidth(OffsetType minWidthType, Measure minWidth) {
        setOffsetProperty("min-width", minWidthType, minWidth);
    }

    @Override
    public void setMaxWidth(OffsetType maxWidthType, Measure maxWidth) {
        setOffsetProperty("max-width", maxWidthType, maxWidth);
    }

    @Override
    public void setMinHeight(OffsetType minHeightType, Measure minHeight) {
        setOffsetProperty("min-height", minHeightType, minHeight);
    }

    @Override
    public void setMaxHeight(OffsetType maxHeightType, Measure maxHeight) {
        setOffsetProperty("max-height", maxHeightType, maxHeight);
    }

    @Override
    public IColor getColor() {
        return getColorProperty("color", getParent().getColor(), true);
    }

    @Override
    public void setColor(IColor color) {
        setProperty("color", color);
    }

    @Override
    public IColor getBackgroundColor() {
        return getColorProperty("background-color", getParent().getBackgroundColor(), false);
    }

    @Override
    public void setBackgroundColor(IColor backgroundColor) {
        setProperty("background-color", backgroundColor);
    }

    @Override
    public BackgroundImageType getBackgroundImageType() {
        String str = getProperty("background-image");
        if (str == null)
            return null;

        switch (str) {
        case "inherited":
            return getParent().getBackgroundImageType();
        case "none":
            return BackgroundImageType.none;
        }

        if (str.startsWith("uri(") && str.endsWith(")"))
            return BackgroundImageType.uri;

        throw new IllegalUsageException("Illegal background-image: " + str);
    }

    @Override
    public String getBackgroundImage() {
        String str = getProperty("background-image");
        if (str == null)
            return null;

        switch (str) {
        case "inherited":
            return getParent().getBackgroundImage();
        case "none":
            return "";
        }

        if (str.startsWith("uri(") && str.endsWith(")"))
            return str.substring(4, str.length() - 1);

        throw new IllegalUsageException("Illegal background-image: " + str);
    }

    @Override
    public void setBackgroundImage(BackgroundImageType backgroundImageType, String backgroundImage) {
        Object property = null;
        switch (backgroundImageType) {
        case uri:
            property = "uri(\"" + backgroundImage + "\")";
            break;
        case none:
        default:
            property = "none";
        }
        setProperty("background-image", property);
    }

    @Override
    public BackgroundRepeatMode getBackgroundRepeat() {
        return getEnumProperty("background-repeat", BackgroundRepeatMode.class, //
                getParent().getBackgroundRepeat(), false);
    }

    @Override
    public void setBackgroundRepeat(BackgroundRepeatMode backgroundRepeat) {
        setProperty("background-repeat", backgroundRepeat);
    }

    @Override
    public BackgroundAttachmentMode getBackgroundAttachment() {
        return getEnumProperty("background-attachment", BackgroundAttachmentMode.class, //
                getParent().getBackgroundAttachment(), false);
    }

    @Override
    public void setBackgroundAttachment(BackgroundAttachmentMode backgroundAttachment) {
        setProperty("background-attachment", backgroundAttachment);
    }

    @Override
    public BackgroundPositionType getBackgroundPositionXType() {
        String str = getProperty("background-position");
        if (str == null || "inherited".equals(str))
            return getParent().getBackgroundPositionXType();

        str = StringPart.before(str, ';', str).trim();

        switch (str) {
        case "left":
            return BackgroundPositionType.left;
        case "right":
            return BackgroundPositionType.right;
        case "center":
        case "":
            return BackgroundPositionType.center;
        }

        if (str.endsWith("%"))
            return BackgroundPositionType.percentage;
        else
            // assume endsWith(unit)
            return BackgroundPositionType.length;
    }

    @Override
    public BackgroundPositionType getBackgroundPositionYType() {
        String str = getProperty("background-position");
        if (str == null || "inherited".equals(str))
            return getParent().getBackgroundPositionYType();

        str = StringPart.after(str, ';', null);
        if (str == null)
            return BackgroundPositionType.center;
        str = str.trim();

        switch (str) {
        case "top":
            return BackgroundPositionType.top;
        case "bottom":
            return BackgroundPositionType.bottom;
        case "center":
        case "":
            return BackgroundPositionType.center;
        }

        if (str.endsWith("%"))
            return BackgroundPositionType.percentage;
        else
            // assume endsWith(unit)
            return BackgroundPositionType.length;
    }

    @Override
    public Measure getBackgroundPositionX() {
        String str = getProperty("background-position");
        if (str == null || "inherited".equals(str))
            return getParent().getBackgroundPositionX();

        str = StringPart.before(str, ';', str).trim();

        switch (str) {
        case "left":
        case "right":
        case "center":
        case "":
            return Measure.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Measure.parse(str);
    }

    @Override
    public Measure getBackgroundPositionY() {
        String str = getProperty("background-position");
        if (str == null || "inherited".equals(str))
            return getParent().getBackgroundPositionY();

        str = StringPart.after(str, ';', null);
        if (str == null)
            return Measure.NaN;
        str = str.trim();

        switch (str) {
        case "top":
        case "bottom":
        case "center":
        case "":
            return Measure.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Measure.parse(str);
    }

    @Override
    public void setBackgroundPosition(BackgroundPositionType backgroundPositionXType, Measure backgroundPositionX,
            BackgroundPositionType backgroundPositionYType, Measure backgroundPositionY) {
        String xstr = null, ystr = null;
        if (backgroundPositionXType != null)
            switch (backgroundPositionXType) {
            case length:
                xstr = backgroundPositionX.toString();
                break;
            case percentage:
                xstr = backgroundPositionX.getValue() + "%";
                break;
            case center:
                xstr = "";
                break;
            case left:
            case right:
            default:
                xstr = backgroundPositionXType.toString();
            }

        if (backgroundPositionYType != null)
            switch (backgroundPositionYType) {
            case length:
                ystr = backgroundPositionY.toString();
                break;
            case percentage:
                ystr = backgroundPositionY.getValue() + "%";
                break;
            case center:
                ystr = "";
            case top:
            case bottom:
            default:
                ystr = backgroundPositionYType.toString();
            }

        String str;
        if (xstr == null) {
            if (ystr == null)
                str = null;
            else
                str = "; " + ystr;
        } else {
            if (ystr == null)
                str = xstr;
            else
                str = xstr + "; " + ystr;
        }
        setProperty("background-position", str);
    }

    @Override
    public MeasureBox getMargin() {
        return null;
    }

    @Override
    public void setMargin(MeasureBox margin) {
    }

    @Override
    public MeasureBox getPadding() {
        return null;
    }

    @Override
    public void setPadding(MeasureBox padding) {
    }

    @Override
    public BorderBox getBorder() {
        return null;
    }

    @Override
    public void setBorder(BorderBox border) {
    }

    @Override
    public BorderCollapseMode getBorderCollapse() {
        return getEnumProperty("border-collapse", BorderCollapseMode.class, //
                getParent().getBorderCollapse(), true);
    }

    @Override
    public void setBorderCollapse(BorderCollapseMode borderCollapse) {
        setProperty("border-collapse", borderCollapse);
    }

    @Override
    public Measure getBorderSpacingHorizontal() {
        String str = getProperty("border-spacing");
        if (str == null)
            return null;
        if ("inherit".equals(str))
            return getParent().getBorderSpacingHorizontal();
        String[] array = str.split("\\s+");
        String hstr = array[0];
        return Measure.parse(hstr);
    }

    @Override
    public Measure getBorderSpacingVertical() {
        String str = getProperty("border-spacing");
        if (str == null)
            return null;
        if ("inherit".equals(str))
            return getParent().getBorderSpacingHorizontal();
        String[] array = str.split("\\s+");
        String vstr;
        if (array.length >= 2)
            vstr = array[1];
        else
            vstr = array[0];
        return Measure.parse(vstr);
    }

    @Override
    public void setBorderSpacing(Measure borderSpacingHorizontal, Measure borderSpacingVertical) {
        Object property = null;
        if (borderSpacingHorizontal != null) {
            String str = borderSpacingHorizontal.toString();
            if (borderSpacingVertical != null)
                str += " " + borderSpacingVertical;
            property = str;
        }
        setProperty("border-spacing", property);
    }

    @Override
    public OutlineColorMode getOutlineColorType() {
        return null;
    }

    @Override
    public void setOutlineColorType(OutlineColorMode outlineColorType) {
    }

    @Override
    public Border getOutline() {
        return null;
    }

    @Override
    public void setOutline(Border outline) {
    }

    @Override
    public Float getOpacity() {
        return getFloatProperty("opacity", getParent().getOpacity(), false);
    }

    @Override
    public void setOpacity(Float opacity) {
        setProperty("opacity", opacity);
    }

    @Override
    public ClearMode getClear() {
        return getEnumProperty("clear", ClearMode.class, getParent().getClear(), false);
    }

    @Override
    public void setClear(ClearMode clear) {
        setProperty("clear", clear);
    }

    @Override
    public DisplayMode getDisplay() {
        return getEnumProperty("display", DisplayMode.class, getParent().getDisplay(), false);
    }

    @Override
    public void setDisplay(DisplayMode display) {
        setProperty("display", display);
    }

    @Override
    public PositionMode getPosition() {
        return getEnumProperty("position", PositionMode.class, getParent().getPosition(), false);
    }

    @Override
    public void setPosition(PositionMode position) {
        setProperty("position", position);
    }

    @Override
    public FloatMode getFloat() {
        return getEnumProperty("float", FloatMode.class, getParent().getFloat(), false);
    }

    @Override
    public void setFloat(FloatMode float_) {
        setProperty("float", float_);
    }

    @Override
    public OverflowMode getOverflow() {
        return getEnumProperty("overflow", OverflowMode.class, getParent().getOverflow(), false);
    }

    @Override
    public void setOverflow(OverflowMode overflow) {
        setProperty("overflow", overflow);
    }

    @Override
    public VisibilityMode getVisibility() {
        return getEnumProperty("visibility", VisibilityMode.class, getParent().getVisibility(), true);
    }

    @Override
    public void setVisibility(VisibilityMode visibility) {
        setProperty("visibility", visibility);
    }

    @Override
    public ZIndexType getZIndexType() {
        String str = getProperty("z-index");
        if (str == null)
            return null;
        switch (str) {
        case "auto":
            return ZIndexType.auto;
        default:
            if (StringPred.isInteger(str))
                return ZIndexType.integer;
            else
                throw new IllegalUsageException("Not an integer: " + str);
        }
    }

    @Override
    public int getZIndex() {
        String str = getProperty("z-index");
        if (str == null)
            return 0;
        switch (str) {
        case "auto":
            return 0;
        default:
            if (StringPred.isInteger(str))
                return Integer.parseInt(str);
            else
                throw new IllegalUsageException("Not an integer: " + str);
        }
    }

    @Override
    public void setZIndex(ZIndexType zIndexType, int zIndex) {
        Object property = null;
        switch (zIndexType) {
        case integer:
            property = zIndex;
            break;
        case auto:
        default:
            property = zIndexType;
        }
        setProperty("z-index", property);
    }

    @Override
    public String getFontFamily() {
        return null;
    }

    @Override
    public void setFontFamily(String fontFamily) {
    }

    @Override
    public FontSizeType getFontSizeType() {
        return null;
    }

    @Override
    public Measure getFontSize() {
        return null;
    }

    @Override
    public void setFontSize(FontSizeType fontSizeType, Measure fontSize) {
    }

    @Override
    public FontStyleMode getFontStyle() {
        return getEnumProperty("font-style", FontStyleMode.class, getParent().getFontStyle(), true);
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        setProperty("font-style", fontStyle);
    }

    @Override
    public FontVariantMode getFontVariant() {
        return getEnumProperty("font-variant", FontVariantMode.class, getParent().getFontVariant(), true);
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        setProperty("font-variant", fontVariant);
    }

    @Override
    public FontWeightMode getFontWeight() {
        return null;
    }

    @Override
    public void setFontWeight(FontWeightMode fontWeight) {
    }

    @Override
    public DirectionMode getDirection() {
        return getEnumProperty("direction", DirectionMode.class, getParent().getDirection(), true);
    }

    @Override
    public void setDirection(DirectionMode direction) {
        setProperty("direction", direction);
    }

    @Override
    public LetterSpacingType getLetterSpacingType() {
        String str = getProperty("letter-spacing");
        if (str == null || "inherit".equals(str))
            return getParent().getLetterSpacingType();
        switch (str) {
        case "normal":
            return LetterSpacingType.normal;
        default:
            return LetterSpacingType.length;
        }
    }

    @Override
    public Measure getLetterSpacing() {
        String str = getProperty("letter-spacing");
        if (str == null || "inherit".equals(str))
            return getParent().getLetterSpacing();
        switch (str) {
        case "normal":
            return Measure.NaN;
        default:
            return Measure.parse(str);
        }
    }

    @Override
    public void setLetterSpacing(LetterSpacingType letterSpacingType, Measure letterSpacing) {
        Object property = null;
        if (letterSpacingType != null)
            switch (letterSpacingType) {
            case length:
                property = letterSpacing;
                break;
            case normal:
            default:
                property = letterSpacingType;
            }
        setProperty("letter-spacing", property);
    }

    @Override
    public LineHeightType getLineHeightType() {
        String str = getProperty("line-height");
        if (str == null || "inherit".equals(str))
            return getParent().getLineHeightType();

        switch (str) {
        case "normal":
            return LineHeightType.normal;
        }

        if (str.endsWith("%"))
            return LineHeightType.percentage;

        String num = StringPart.peekDecimal(str);
        if (num.length() == str.length())
            return LineHeightType.number;
        else
            return LineHeightType.length;
    }

    @Override
    public Measure getLineHeight() {
        String str = getProperty("line-height");
        if (str == null || "inherit".equals(str))
            return getParent().getLineHeight();

        switch (str) {
        case "normal":
            return Measure.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str);

        return Measure.parse(str);
    }

    @Override
    public void setLineHeight(LineHeightType lineHeightType, Measure lineHeight) {
        Object property = null;
        if (lineHeightType != null)
            switch (lineHeightType) {
            case number:
            case length:
                property = lineHeight;
                break;
            case percentage:
                property = lineHeight + "%";
                break;
            case normal:
            default:
                property = lineHeightType;
            }
        setProperty("line-height", property);
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        TextDecorationFlags textDecoration = getParent().getTextDecoration();

        String property = getProperty("text-decoration");
        if (property == null || "inherit".equals(property))
            return textDecoration;

        for (String token : property.split("\\s+")) {
            switch (token) {
            case "none":
                textDecoration = new TextDecorationFlags();
                break;
            case "underline":
                textDecoration.setUnderline(true);
                break;
            case "overline":
                textDecoration.setOverline(true);
                break;
            case "line-through":
                textDecoration.setLineThrough(true);
                break;
            }
        }
        return textDecoration;
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
        setProperty("text-decoration", textDecoration);
    }

    @Override
    public TextTransformMode getTextTransform() {
        return getEnumProperty("text-transform", TextTransformMode.class, getParent().getTextTransform(), true);
    }

    @Override
    public void setTextTransform(TextTransformMode textTransform) {
        setProperty("text-transform", textTransform);
    }

    @Override
    public TextIndentType getTextIndentType() {
        String str = getProperty("text-indent");
        if (str == null || "inherit".equals(str))
            return getParent().getTextIndentType();
        if (str.endsWith("%"))
            return TextIndentType.percentage;
        else
            return TextIndentType.length;
    }

    @Override
    public Measure getTextIndent() {
        String str = getProperty("text-indent");
        if (str == null || "inherit".equals(str))
            return getParent().getTextIndent();
        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Measure.parse(str);
    }

    @Override
    public void setTextIndent(TextIndentType textIndentType, Measure textIndent) {
        Object property = null;
        if (textIndentType != null)
            switch (textIndentType) {
            case percentage:
                property = textIndent + "%";
                break;
            case length:
                property = textIndent;
                break;
            default:
                property = textIndentType;
            }
        setProperty("text-indent", property);
    }

    @Override
    public TextAlignMode getTextAlign() {
        return getEnumProperty("text-align", TextAlignMode.class, getParent().getTextAlign(), true);
    }

    @Override
    public void setTextAlign(TextAlignMode textAlign) {
        setProperty("text-align", textAlign);
    }

    @Override
    public VerticalAlignType getVerticalAlignType() {
        String str = getProperty("vertical-align");
        if (str == null)
            return null;
        switch (str) {
        case "inherit":
            return getParent().getVerticalAlignType();
        case "baseline":
            return VerticalAlignType.baseline;
        case "middle":
            return VerticalAlignType.middle;
        case "sub":
            return VerticalAlignType.sub;
        case "super":
            return VerticalAlignType.super_;
        case "text-top":
            return VerticalAlignType.text_top;
        case "text-bottom":
            return VerticalAlignType.text_bottom;
        case "top":
            return VerticalAlignType.top;
        case "bottom":
            return VerticalAlignType.bottom;
        }
        if (str.endsWith("%"))
            return VerticalAlignType.percentage;
        else
            return VerticalAlignType.length;
    }

    @Override
    public Measure getVerticalAlign() {
        String str = getProperty("vertical-align");
        if (str == null)
            return null;
        switch (str) {
        case "inherit":
            return getParent().getVerticalAlign();
        case "baseline":
        case "middle":
        case "sub":
        case "super":
        case "text-top":
        case "text-bottom":
        case "top":
        case "bottom":
            return Measure.NaN;
        }
        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Measure.parse(str);
    }

    @Override
    public void setVerticalAlign(VerticalAlignType verticalAlignType, Measure verticalAlign) {
        Object property = null;
        if (verticalAlignType != null)
            switch (verticalAlignType) {
            case percentage:
                property = verticalAlign + "%";
                break;
            case length:
                property = verticalAlign;
                break;
            default:
                property = verticalAlignType;
            }
        setProperty("vertical-align", property);
    }

    @Override
    public QuotesType getQuotesType() {
        String str = getProperty("quotes");
        if (str == null || "inherit".equals(str))
            return getParent().getQuotesType();
        switch (str) {
        case "none":
            return QuotesType.none;
        default:
            return QuotesType.string_pairs;
        }
    }

    @Override
    public List<Pair<String, String>> getQuotes() {
        String str = getProperty("quotes");
        if (str == null || "inherit".equals(str))
            return getParent().getQuotes();
        switch (str) {
        case "none":
            return Collections.emptyList();
        default:
            List<Pair<String, String>> quotes = new ArrayList<Pair<String, String>>();
            String[] args = str.split("\\s+");
            for (int i = 0; i < args.length - 1; i += 2) {
                String open = args[i];
                String close = args[i + 1];
                quotes.add(Pair.of(open, close));
            }
            return quotes;
        }
    }

    @Override
    public void setQuotes(QuotesType quotesType, List<Pair<String, String>> quotes) {
        Object property = null;
        if (quotesType != null)
            switch (quotesType) {
            case string_pairs:
                StringBuilder buf = new StringBuilder();
                for (Pair<String, String> pair : quotes) {
                    String quote = StringQuote.qq(pair.getKey()) + " " + StringQuote.qq(pair.getValue());
                    if (buf.length() != 0)
                        buf.append(' ');
                    buf.append(quote);
                }
                break;
            case none:
            default:
                property = quotesType;
            }
        setProperty("quotes", property);
    }

    @Override
    public UnicodeBidiMode getUnicodeBidi() {
        return getEnumProperty("unicode-bidi", UnicodeBidiMode.class, getParent().getUnicodeBidi(), false);
    }

    @Override
    public void setUnicodeBidi(UnicodeBidiMode unicodeBidi) {
        setProperty("unicode-bidi", unicodeBidi);
    }

    @Override
    public WhiteSpaceMode getWhiteSpace() {
        return getEnumProperty("white-space", WhiteSpaceMode.class, getParent().getWhiteSpace(), true);
    }

    @Override
    public void setWhiteSpace(WhiteSpaceMode whiteSpace) {
        setProperty("white-space", whiteSpace);
    }

    @Override
    public WordSpacingType getWordSpacingType() {
        String str = getProperty("word-spacing");
        if (str == null)
            return null;
        if ("inherit".equals(str))
            return getParent().getWordSpacingType();
        switch (str) {
        case "normal":
            return WordSpacingType.normal;
        default:
            return WordSpacingType.length;
        }
    }

    @Override
    public Measure getWordSpacing() {
        String str = getProperty("word-spacing");
        if (str == null)
            return null;
        switch (str) {
        case "inherit":
            return getParent().getWordSpacing();
        case "normal":
            return Measure.NaN;
        default:
            return Measure.parse(str);
        }
    }

    @Override
    public void setWordSpacing(WordSpacingType wordSpacingType, Measure wordSpacing) {
        Object property = null;
        switch (wordSpacingType) {
        case length:
            property = wordSpacing;
            break;
        case normal:
        default:
            property = wordSpacingType;
        }
        setProperty("word-spacing", property);
    }

    @Override
    public ClipType getClipType() {
        String str = getProperty("clip");
        if (str == null)
            return null;
        switch (str) {
        case "inherit":
            return getParent().getClipType();
        case "auto":
            return ClipType.auto;
        default:
            return ClipType.shape;
        }
    }

    @Override
    public String getClip() {
        String str = getProperty("clip");
        if (str == null)
            return null;
        switch (str) {
        case "inherit":
            return getParent().getClip();
        case "auto":
            return "";
        default:
            return str;
        }
    }

    @Override
    public void setClip(ClipType clipType, String clip) {
        Object property = null;
        switch (clipType) {
        case shape:
            property = clip;
            break;
        case auto:
        default:
            property = clipType;
        }
        setProperty("clip", property);
    }

    @Override
    public CursorType getCursorType() {
        String str = getProperty("cursor");
        if (str == null || "inherit".equals(str))
            return getParent().getCursorType();

        if (str.startsWith("uri(") && str.endsWith(")"))
            return CursorType.uri;

        switch (str) {
        case "default":
            str = "default_";
            break;
        }
        CursorType cursorType = CursorType.valueOf(str);
        return cursorType;
    }

    @Override
    public String getCursor() {
        String str = getProperty("cursor");
        if (str == null || "inherit".equals(str))
            return getParent().getCursor();

        if (str.startsWith("uri(\"") && str.endsWith("\")")) {
            String uri = str.substring(5, str.length() - 2);
            return uri;
        }

        return "";
    }

    @Override
    public void setCursor(CursorType cursorType, String cursor) {
        Object property = null;
        switch (cursorType) {
        case uri:
            property = "uri(\"" + cursor + "\")";
            break;
        default:
            property = cursorType;
        }
        setProperty("cursor", property);
    }

    @Override
    public ListStyleTypeMode getListStyleType() {
        return getEnumProperty("list-style-type", ListStyleTypeMode.class, getParent().getListStyleType(), true);
    }

    @Override
    public void setListStyleType(ListStyleTypeMode listStyleType) {
        setProperty("list-style-type", listStyleType);
    }

    @Override
    public ListStyleImageType getListStyleImageType() {
        String str = getProperty("list-style-image");
        if (str == null)
            return getParent().getListStyleImageType();
        switch (str) {
        case "inherit":
            return getParent().getListStyleImageType();
        case "none":
            return ListStyleImageType.none;
        }

        if (str.startsWith("uri(\"") && str.endsWith("\")"))
            return ListStyleImageType.uri;

        throw new IllegalUsageException("Illegal list-style-image: " + str);
    }

    @Override
    public String getListStyleImage() {
        String str = getProperty("list-style-image");
        if (str == null)
            return getParent().getListStyleImage();
        switch (str) {
        case "inherit":
            return getParent().getListStyleImage();
        case "none":
            return "";
        }

        if (str.startsWith("uri(\"") && str.endsWith("\")")) {
            String uri = str.substring(5, str.length() - 2);
            return uri;
        }

        throw new IllegalUsageException("Illegal list-style-image: " + str);
    }

    @Override
    public void setListStyleImage(ListStyleImageType listStyleImageType, String listStyleImage) {
        Object property = null;
        switch (listStyleImageType) {
        case uri:
            property = "uri(\"" + listStyleImage + "\")";
            break;
        case none:
        default:
            property = listStyleImageType;
        }
        setProperty("list-style-image", property);
    }

    @Override
    public ListStylePositionMode getListStylePosition() {
        return getEnumProperty("list-style-position", ListStylePositionMode.class, getParent().getListStylePosition(),
                true);
    }

    @Override
    public void setListStylePosition(ListStylePositionMode listStylePosition) {
        setProperty("list-style-position", listStylePosition);
    }

    @Override
    public TableLayoutMode getTableLayout() {
        return getEnumProperty("table-layout", TableLayoutMode.class, getParent().getTableLayout(), false);
    }

    @Override
    public void setTableLayout(TableLayoutMode tableLayout) {
        setProperty("table-layout", tableLayout);
    }

    @Override
    public CaptionSideMode getCaptionSide() {
        return getEnumProperty("caption-side", CaptionSideMode.class, getParent().getCaptionSide(), true);
    }

    @Override
    public void setCaptionSide(CaptionSideMode captionSide) {
        setProperty("caption-side", captionSide);
    }

    @Override
    public EmptyCellsMode getEmptyCells() {
        return getEnumProperty("empty-cells", EmptyCellsMode.class, getParent().getEmptyCells(), true);
    }

    @Override
    public void setEmptyCells(EmptyCellsMode emptyCells) {
        setProperty("empty-cells", emptyCells);
    }

    @Override
    public ContentType getContentType() {
        return null;
    }

    @Override
    public List<ContentItem> getContentItems() {
        return null;
    }

    @Override
    public void setContent(ContentType contentType, List<ContentItem> contentItems) {
    }

    @Override
    public String getCounterIncrement() {
        return getProperty("counter-increment");
    }

    @Override
    public void setCounterIncrement(String counterIncrement) {
        setProperty("counter-increment", counterIncrement);
    }

    @Override
    public String getCounterReset() {
        return getProperty("counter-reset");
    }

    @Override
    public void setCounterReset(String counterReset) {
        setProperty("counter-reset", counterReset);
    }

    @Override
    public PageBreakMode getPageBreakAfter() {
        return getEnumProperty("page-break-after", PageBreakMode.class, getParent().getPageBreakAfter(), false);
    }

    @Override
    public void setPageBreakAfter(PageBreakMode pageBreakAfter) {
        setProperty("page-break-after", pageBreakAfter);
    }

    @Override
    public PageBreakMode getPageBreakBefore() {
        return getEnumProperty("page-break-before", PageBreakMode.class, getParent().getPageBreakBefore(), false);
    }

    @Override
    public void setPageBreakBefore(PageBreakMode pageBreakBefore) {
        setProperty("page-break-before", pageBreakBefore);
    }

    @Override
    public PageBreakMode getPageBreakInside() {
        return getEnumProperty("page-break-inside", PageBreakMode.class, getParent().getPageBreakInside(), false);
    }

    @Override
    public void setPageBreakInside(PageBreakMode pageBreakInside) {
        setProperty("page-break-inside", pageBreakInside);
    }

    @Override
    public Integer getOrphans() {
        return getIntegerProperty("orphans", getParent().getOrphans(), true);
    }

    @Override
    public void setOrphans(Integer orphans) {
        setProperty("orphans", orphans);
    }

    @Override
    public Integer getWidows() {
        return getIntegerProperty("widows", getParent().getWidows(), true);
    }

    @Override
    public void setWidows(Integer widows) {
        setProperty("widows", widows);
    }

}
