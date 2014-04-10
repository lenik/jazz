package net.bodz.bas.gui.css3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.t.pojo.Pair;

public abstract class MappedCss3StyleDeclaration
        extends AbstractCss3Properties
        implements ICss3StyleDeclaration {

    private static final long serialVersionUID = 1L;

    public MappedCss3StyleDeclaration() {
        this(new LinkedHashMap<String, String>());
    }

    public MappedCss3StyleDeclaration(Map<String, String> _orig) {
        super(_orig);
    }

    @Override
    public ICss3StyleDeclaration getParent() {
        return null;
    }

    public void setParent(ICss3StyleDeclaration parent) {
    }

    @Override
    public String get(String key, int maxInherits) {
        String property = get(key);

        boolean inherited = false;
        if (property == null) {
            if (maxInherits != 0)
                maxInherits--;
            inherited = true;
        } else if (property.equals("inherit")) {
            inherited = true;
        }

        if (!inherited)
            return property;

        ICss3StyleDeclaration parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.get(key, maxInherits);
    }

    public String getInherited(String key) {
        return get(key, -1);
    }

    public String getInheritable(String key) {
        return get(key, 0);
    }

    public void set(String key, Object value) {
        if (value == null)
            remove(key);
        else
            put(key, value.toString());
    }

    @Override
    public ICss3Length getWidth() {
        return parseCssLength(get("width"));
    }

    @Override
    public ICss3Length getHeight() {
        return parseCssLength(get("height"));
    }

    @Override
    public void setWidth(ICss3Length width) {
        set("width", width);
    }

    @Override
    public void setHeight(ICss3Length height) {
        set("height", height);
    }

    @Override
    public ICss3Length getTop() {
        return parseCssLength(get("top"));
    }

    @Override
    public ICss3Length getRight() {
        return parseCssLength(get("right"));

    }

    @Override
    public ICss3Length getBottom() {
        return parseCssLength(get("bottom"));
    }

    @Override
    public ICss3Length getLeft() {
        return parseCssLength(get("left"));

    }

    @Override
    public void setTop(ICss3Length top) {
        set("top", top);
    }

    @Override
    public void setRight(ICss3Length right) {
        set("right", right);
    }

    @Override
    public void setBottom(ICss3Length bottom) {
        set("bottom", bottom);
    }

    @Override
    public void setLeft(ICss3Length left) {
        set("left", left);
    }

    @Override
    public ICss3Length getMinWidth() {
        return parseCssLength(get("min-width"));
    }

    @Override
    public ICss3Length getMaxWidth() {
        return parseCssLength(get("max-width"));
    }

    @Override
    public ICss3Length getMinHeight() {
        return parseCssLength(get("min-height"));
    }

    @Override
    public ICss3Length getMaxHeight() {
        return parseCssLength(get("max-height"));
    }

    @Override
    public void setMinWidth(ICss3Length minWidth) {
        set("min-width", minWidth);
    }

    @Override
    public void setMaxWidth(ICss3Length maxWidth) {
        set("max-width", maxWidth);
    }

    @Override
    public void setMinHeight(ICss3Length minHeight) {
        set("min-height", minHeight);
    }

    @Override
    public void setMaxHeight(ICss3Length maxHeight) {
        set("max-height", maxHeight);
    }

    @Override
    public IColor getColor() {
        return parseColor(get("color"));
    }

    @Override
    public void setColor(IColor color) {
        set("color", color);
    }

    @Override
    public IColor getBackgroundColor() {
        return parseColor(get("background-color"));
    }

    @Override
    public void setBackgroundColor(IColor backgroundColor) {
        set("background-color", backgroundColor);
    }

    @Override
    public ImageType getBackgroundImageType() {
        String str = getInherited("background-image");
        if (str == null)
            return null;

        switch (str) {
        case "inherited":
            return getParent().getBackgroundImageType();
        case "none":
            return ImageType.none;
        }

        if (str.startsWith("uri(") && str.endsWith(")"))
            return ImageType.uri;

        throw new IllegalUsageException("Illegal background-image: " + str);
    }

    @Override
    public String getBackgroundImage() {
        String str = getInherited("background-image");
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
    public void setBackgroundImage(ImageType backgroundImageType, String backgroundImage) {
        Object property = null;
        switch (backgroundImageType) {
        case uri:
            property = "uri(\"" + backgroundImage + "\")";
            break;
        case none:
        default:
            property = "none";
        }
        set("background-image", property);
    }

    @Override
    public BackgroundRepeatMode getBackgroundRepeat() {
        return parseEnum(get("background-repeat"), BackgroundRepeatMode.class);
    }

    @Override
    public void setBackgroundRepeat(BackgroundRepeatMode backgroundRepeat) {
        set("background-repeat", backgroundRepeat);
    }

    @Override
    public BackgroundAttachmentMode getBackgroundAttachment() {
        return parseEnum(get("background-attachment"), BackgroundAttachmentMode.class);
    }

    @Override
    public void setBackgroundAttachment(BackgroundAttachmentMode backgroundAttachment) {
        set("background-attachment", backgroundAttachment);
    }

    @Override
    public ICss3Length getBackgroundPositionX() {
        String str = getInherited("background-position-x");
        if (str == null || "inherited".equals(str))
            return getParent().getBackgroundPositionX();

        str = StringPart.before(str, ';', str).trim();

        switch (str) {
        case "left":
        case "right":
        case "center":
        case "":
            return Css3Length.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Css3Length.parseOrNaN(str);
    }

    @Override
    public ICss3Length getBackgroundPositionY() {
        String str = get("background-position-y");
        if (str == null || "inherited".equals(str))
            return getParent().getBackgroundPositionY();

        str = StringPart.after(str, ';', null);
        if (str == null)
            return Css3Length.NaN;
        str = str.trim();

        switch (str) {
        case "top":
        case "bottom":
        case "center":
        case "":
            return Css3Length.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Css3Length.parseOrNaN(str);
    }

    @Override
    public void setBackgroundPositionX(ICss3Length backgroundPositionX) {
    }

    @Override
    public void setBackgroundPositionY(ICss3Length backgroundPositionY) {
    }

    @Override
    public void setBackgroundPosition(ICss3Length backgroundPositionX, ICss3Length backgroundPositionY) {
        String str = null;
        if (backgroundPositionX != null || backgroundPositionY != null) {
            str = "";
            if (backgroundPositionX != null)
                str = backgroundPositionX.toString();
            str += ";";
            if (backgroundPositionY != null)
                str += backgroundPositionY.toString();
        }
        set("background-position", str);
    }

    @Override
    public ICss3Length getMargin() {
        return null;
    }

    @Override
    public void setMargin(ICss3Length margin) {
        set("margin", margin);
    }

    @Override
    public ICss3Length getMarginTop() {
        return parseCssLength(get("margin-top"));
    }

    @Override
    public void setMarginTop(ICss3Length marginTop) {
        set("margin-top", marginTop);
    }

    @Override
    public ICss3Length getMarginRight() {
        return null;
    }

    @Override
    public void setMarginRight(ICss3Length marginRight) {
        set("margin-right", marginRight);
    }

    @Override
    public ICss3Length getMarginBottom() {
        return null;
    }

    @Override
    public void setMarginBottom(ICss3Length marginBottom) {
        set("margin-bottom", marginBottom);
    }

    @Override
    public ICss3Length getMarginLeft() {
        return null;
    }

    @Override
    public void setMarginLeft(ICss3Length marginLeft) {
        set("margin-left", marginLeft);
    }

    @Override
    public ICss3Length getPadding() {
        return null;
    }

    @Override
    public void setPadding(ICss3Length padding) {
        set("padding", padding);
    }

    @Override
    public ICss3Length getPaddingTop() {
        return null;
    }

    @Override
    public void setPaddingTop(ICss3Length paddingTop) {
        set("padding-top", paddingTop);
    }

    @Override
    public ICss3Length getPaddingRight() {
        return null;
    }

    @Override
    public void setPaddingRight(ICss3Length paddingRight) {
        set("padding-right", paddingRight);
    }

    @Override
    public ICss3Length getPaddingBottom() {
        return null;
    }

    @Override
    public void setPaddingBottom(ICss3Length paddingBottom) {
        set("padding-bottom", paddingBottom);
    }

    @Override
    public ICss3Length getPaddingLeft() {
        return null;
    }

    @Override
    public void setPaddingLeft(ICss3Length paddingLeft) {
        set("padding-left", paddingLeft);
    }

    @Override
    public Border getBorder() {
        return null;
    }

    @Override
    public void setBorder(Border border) {
        set("border", border);
    }

    @Override
    public Border getBorderTop() {
        return null;
    }

    @Override
    public void setBorderTop(Border borderTop) {
        set("border-top", borderTop);
    }

    @Override
    public Border getBorderRight() {
        return null;
    }

    @Override
    public void setBorderRight(Border borderRight) {
        set("border-right", borderRight);
    }

    @Override
    public Border getBorderBottom() {
        return null;
    }

    @Override
    public void setBorderBottom(Border borderBottom) {
        set("border-bottom", borderBottom);
    }

    @Override
    public Border getBorderLeft() {
        return null;
    }

    @Override
    public void setBorderLeft(Border borderLeft) {
        set("border-left", borderLeft);
    }

    @Override
    public ICss3Length getBorderRadius() {
        return parseCssLength(get("border-radius"));
    }

    @Override
    public void setBorderRadius(ICss3Length borderRadius) {
        set("border-radius", borderRadius);
    }

    @Override
    public ICss3Length getBorderTopLeftRadius() {
        return parseCssLength(get("border-top-left-radius"));
    }

    @Override
    public void setBorderTopLeftRadius(ICss3Length borderTopLeftRadius) {
        set("border-top-left-radius", borderTopLeftRadius);
    }

    @Override
    public ICss3Length getBorderTopRightRadius() {
        return parseCssLength(get("border-top-right-radius"));
    }

    @Override
    public void setBorderTopRightRadius(ICss3Length borderTopRightRadius) {
        set("border-top-right-radius", borderTopRightRadius);
    }

    @Override
    public ICss3Length getBorderBottomRightRadius() {
        return parseCssLength(get("border-bottom-right-radius"));
    }

    @Override
    public void setBorderBottomRightRadius(ICss3Length borderBottomRightRadius) {
        set("border-bottom-right-radius", borderBottomRightRadius);
    }

    @Override
    public ICss3Length getBorderBottomLeftRadius() {
        return parseCssLength(get("border-bottom-left-radius"));
    }

    @Override
    public void setBorderBottomLeftRadius(ICss3Length borderBottomLeftRadius) {
        set("border-bottom-left-radius", borderBottomLeftRadius);
    }

    @Override
    public BorderCollapseMode getBorderCollapse() {
        return parseEnum(get("border-collapse"), BorderCollapseMode.class);
    }

    @Override
    public void setBorderCollapse(BorderCollapseMode borderCollapse) {
        set("border-collapse", borderCollapse);
    }

    @Override
    public ICss3Length getBorderHorizontalSpacing() {
        String str = get("border-spacing");
        if (str == null)
            return null;
        if ("inherit".equals(str))
            return getParent().getBorderHorizontalSpacing();
        String[] array = str.split("\\s+");
        String hstr = array[0];
        return Css3Length.parseOrNaN(hstr);
    }

    @Override
    public ICss3Length getBorderVerticalSpacing() {
        String str = get("border-spacing");
        if (str == null)
            return null;
        if ("inherit".equals(str))
            return getParent().getBorderHorizontalSpacing();
        String[] array = str.split("\\s+");
        String vstr;
        if (array.length >= 2)
            vstr = array[1];
        else
            vstr = array[0];
        return Css3Length.parseOrNaN(vstr);
    }

    @Override
    public void setBorderSpacing(ICss3Length borderSpacingHorizontal, ICss3Length borderSpacingVertical) {
        Object property = null;
        if (borderSpacingHorizontal != null) {
            String str = borderSpacingHorizontal.toString();
            if (borderSpacingVertical != null)
                str += " " + borderSpacingVertical;
            property = str;
        }
        set("border-spacing", property);
    }

    @Override
    public Border getOutline() {
        return null;
    }

    @Override
    public boolean isOutlineInvert() {
        String outlineColor = get("outline-color");
        return "invert".equals(outlineColor);
    }

    @Override
    public void setOutline(Border outline, boolean invert) {
        BorderStyleMode outlineStyle = null;
        ICss3Length outlineWidth = null;
        IColor outlineColor = null;

        if (outline != null) {
            outlineStyle = outline.getStyle();
            outlineWidth = outline.getWidth();
            outlineColor = outline.getColor();
        }

        set("outline-style", outlineStyle);

        set("outline-width", outlineWidth);

        if (invert)
            set("outline-color", "invert");
        else if (outlineColor != null)
            set("outline-color", outlineColor);
    }

    @Override
    public ICss3Length getOutlineOffset() {
        return parseCssLength(get("outline-offset"));
    }

    @Override
    public void setOutlineOffset(ICss3Length outlineOffset) {
        set("outline-offset", outlineOffset);
    }

    @Override
    public Float getOpacity() {
        return parseFloat(get("opacity"));
    }

    @Override
    public void setOpacity(Float opacity) {
        set("opacity", opacity);
    }

    @Override
    public ClearMode getClear() {
        return parseEnum(get("clear"), ClearMode.class);
    }

    @Override
    public void setClear(ClearMode clear) {
        set("clear", clear);
    }

    @Override
    public DisplayMode getDisplay() {
        return parseEnum(get("display"), DisplayMode.class);
    }

    @Override
    public void setDisplay(DisplayMode display) {
        set("display", display);
    }

    @Override
    public PositionMode getPosition() {
        return parseEnum(get("position"), PositionMode.class);
    }

    @Override
    public void setPosition(PositionMode position) {
        set("position", position);
    }

    @Override
    public FloatMode getFloat() {
        return parseEnum(get("float"), FloatMode.class);
    }

    @Override
    public void setFloat(FloatMode float_) {
        set("float", float_);
    }

    @Override
    public OverflowMode getOverflow() {
        return parseEnum(get("overflow"), OverflowMode.class);
    }

    @Override
    public void setOverflow(OverflowMode overflow) {
        set("overflow", overflow);
    }

    @Override
    public VisibilityMode getVisibility() {
        return parseEnum(get("visibility"), VisibilityMode.class);
    }

    @Override
    public void setVisibility(VisibilityMode visibility) {
        set("visibility", visibility);
    }

    @Override
    public ICss3Int getZIndex() {
        return parseCssInt(get("z-index"));
    }

    @Override
    public void setZIndex(ICss3Int zIndex) {
        Object property = null;
        switch (zIndex.getType()) {
        case ICss3Int.NUMBER:
            property = zIndex;
            break;
        case ICss3Int.AUTO:
        default:
            property = zIndex;
        }
        set("z-index", property);
    }

    @Override
    public String getFontFamily() {
        return null;
    }

    @Override
    public void setFontFamily(String fontFamily) {
    }

    @Override
    public ICss3Length getFontSize() {
        return null;
    }

    @Override
    public void setFontSize(ICss3Length fontSize) {
    }

    @Override
    public FontStyleMode getFontStyle() {
        return parseEnum(getInherited("font-style"), FontStyleMode.class);
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        set("font-style", fontStyle);
    }

    @Override
    public FontVariantMode getFontVariant() {
        return parseEnum(getInherited("font-variant"), FontVariantMode.class);
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        set("font-variant", fontVariant);
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
        return parseEnum("direction", DirectionMode.class);
    }

    @Override
    public void setDirection(DirectionMode direction) {
        set("direction", direction);
    }

    @Override
    public ICss3Length getLetterSpacing() {
        String str = get("letter-spacing");
        if (str == null || "inherit".equals(str))
            return getParent().getLetterSpacing();
        switch (str) {
        case "normal":
            return Css3Length.NaN;
        default:
            return Css3Length.parseOrNaN(str);
        }
    }

    @Override
    public void setLetterSpacing(ICss3Length letterSpacing) {
        Object property = null;
        if (letterSpacing != null)
            switch (letterSpacing.getType()) {
            case ICss3Length.LENGTH:
                property = letterSpacing;
                break;
            case ICss3Length.NORMAL:
            default:
                property = letterSpacing;
            }
        set("letter-spacing", property);
    }

    @Override
    public ICss3Length getLineHeight() {
        String str = get("line-height");
        if (str == null || "inherit".equals(str))
            return getParent().getLineHeight();

        switch (str) {
        case "normal":
            return Css3Length.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str);

        return Css3Length.parseOrNaN(str);
    }

    @Override
    public void setLineHeight(ICss3Length lineHeight) {
        Object property = null;
        if (lineHeight != null)
            switch (lineHeight.getType()) {
            case ICss3Length.NUMBER:
            case ICss3Length.LENGTH:
                property = lineHeight;
                break;
            case ICss3Length.PERCENTAGE:
                property = lineHeight + "%";
                break;
            case ICss3Length.NORMAL:
            default:
                property = lineHeight;
            }
        set("line-height", property);
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        TextDecorationFlags textDecoration = getParent().getTextDecoration();

        String property = getInherited("text-decoration");
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
        set("text-decoration", textDecoration);
    }

    @Override
    public TextTransformMode getTextTransform() {
        return parseEnum("text-transform", TextTransformMode.class);
    }

    @Override
    public void setTextTransform(TextTransformMode textTransform) {
        set("text-transform", textTransform);
    }

    @Override
    public ICss3Length getTextIndent() {
        String str = getInherited("text-indent");
        if (str == null || "inherit".equals(str))
            return getParent().getTextIndent();
        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Css3Length.parseOrNaN(str);
    }

    @Override
    public void setTextIndent(ICss3Length textIndent) {
        Object property = null;
        if (textIndent != null)
            switch (textIndent.getType()) {
            case ICss3Length.PERCENTAGE:
                property = textIndent + "%";
                break;
            case ICss3Length.LENGTH:
                property = textIndent;
                break;
            default:
                property = textIndent;
            }
        set("text-indent", property);
    }

    @Override
    public TextAlignMode getTextAlign() {
        return parseEnum("text-align", TextAlignMode.class);
    }

    @Override
    public void setTextAlign(TextAlignMode textAlign) {
        set("text-align", textAlign);
    }

    @Override
    public ICss3Length getVerticalAlign() {
        String str = getInherited("vertical-align");
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
            return Css3Length.NaN;
        }
        if (str.endsWith("%"))
            str = StringPart.chop(str);
        return Css3Length.parseOrNaN(str);
    }

    @Override
    public void setVerticalAlign(ICss3Length verticalAlign) {
        Object property = null;
        if (verticalAlign != null)
            switch (verticalAlign.getType()) {
            case ICss3Length.PERCENTAGE:
                property = verticalAlign + "%";
                break;
            case ICss3Length.LENGTH:
                property = verticalAlign;
                break;
            default:
                property = verticalAlign;
            }
        set("vertical-align", property);
    }

    @Override
    public QuotesType getQuotesType() {
        String str = getInherited("quotes");
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
        String str = getInherited("quotes");
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
        set("quotes", property);
    }

    @Override
    public UnicodeBidiMode getUnicodeBidi() {
        return parseEnum("unicode-bidi", UnicodeBidiMode.class);
    }

    @Override
    public void setUnicodeBidi(UnicodeBidiMode unicodeBidi) {
        set("unicode-bidi", unicodeBidi);
    }

    @Override
    public WhiteSpaceMode getWhiteSpace() {
        return parseEnum("white-space", WhiteSpaceMode.class);
    }

    @Override
    public void setWhiteSpace(WhiteSpaceMode whiteSpace) {
        set("white-space", whiteSpace);
    }

    @Override
    public ICss3Length getWordSpacing() {
        String str = getInherited("word-spacing");
        if (str == null)
            return null;
        switch (str) {
        case "inherit":
            return getParent().getWordSpacing();
        case "normal":
            return Css3Length.NaN;
        default:
            return Css3Length.parseOrNaN(str);
        }
    }

    @Override
    public void setWordSpacing(ICss3Length wordSpacing) {
        Object property = null;
        switch (wordSpacing.getType()) {
        case ICss3Length.LENGTH:
            property = wordSpacing;
            break;
        case ICss3Length.NORMAL:
        default:
            property = wordSpacing;
        }
        set("word-spacing", property);
    }

    @Override
    public ClipType getClipType() {
        String str = getInherited("clip");
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
        String str = getInherited("clip");
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
        set("clip", property);
    }

    @Override
    public CursorType getCursorType() {
        String str = getInherited("cursor");
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
        String str = getInherited("cursor");
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
        set("cursor", property);
    }

    @Override
    public ListStyleTypeMode getListStyleType() {
        return parseEnum("list-style-type", ListStyleTypeMode.class);
    }

    @Override
    public void setListStyleType(ListStyleTypeMode listStyleType) {
        set("list-style-type", listStyleType);
    }

    @Override
    public ImageType getListStyleImageType() {
        String str = getInherited("list-style-image");
        if (str == null)
            return getParent().getListStyleImageType();
        switch (str) {
        case "inherit":
            return getParent().getListStyleImageType();
        case "none":
            return ImageType.none;
        }

        if (str.startsWith("uri(\"") && str.endsWith("\")"))
            return ImageType.uri;

        throw new IllegalUsageException("Illegal list-style-image: " + str);
    }

    @Override
    public String getListStyleImage() {
        String str = getInherited("list-style-image");
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
    public void setListStyleImage(ImageType listStyleImageType, String listStyleImage) {
        Object property = null;
        switch (listStyleImageType) {
        case uri:
            property = "uri(\"" + listStyleImage + "\")";
            break;
        case none:
        default:
            property = listStyleImageType;
        }
        set("list-style-image", property);
    }

    @Override
    public ListStylePositionMode getListStylePosition() {
        return parseEnum("list-style-position", ListStylePositionMode.class);
    }

    @Override
    public void setListStylePosition(ListStylePositionMode listStylePosition) {
        set("list-style-position", listStylePosition);
    }

    @Override
    public TableLayoutMode getTableLayout() {
        return parseEnum("table-layout", TableLayoutMode.class);
    }

    @Override
    public void setTableLayout(TableLayoutMode tableLayout) {
        set("table-layout", tableLayout);
    }

    @Override
    public CaptionSideMode getCaptionSide() {
        return parseEnum("caption-side", CaptionSideMode.class);
    }

    @Override
    public void setCaptionSide(CaptionSideMode captionSide) {
        set("caption-side", captionSide);
    }

    @Override
    public EmptyCellsMode getEmptyCells() {
        return parseEnum("empty-cells", EmptyCellsMode.class);
    }

    @Override
    public void setEmptyCells(EmptyCellsMode emptyCells) {
        set("empty-cells", emptyCells);
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
        return getInherited("counter-increment");
    }

    @Override
    public void setCounterIncrement(String counterIncrement) {
        set("counter-increment", counterIncrement);
    }

    @Override
    public String getCounterReset() {
        return getInherited("counter-reset");
    }

    @Override
    public void setCounterReset(String counterReset) {
        set("counter-reset", counterReset);
    }

    @Override
    public PageBreakMode getPageBreakAfter() {
        return parseEnum("page-break-after", PageBreakMode.class);
    }

    @Override
    public void setPageBreakAfter(PageBreakMode pageBreakAfter) {
        set("page-break-after", pageBreakAfter);
    }

    @Override
    public PageBreakMode getPageBreakBefore() {
        return parseEnum("page-break-before", PageBreakMode.class);
    }

    @Override
    public void setPageBreakBefore(PageBreakMode pageBreakBefore) {
        set("page-break-before", pageBreakBefore);
    }

    @Override
    public PageBreakMode getPageBreakInside() {
        return parseEnum("page-break-inside", PageBreakMode.class);
    }

    @Override
    public void setPageBreakInside(PageBreakMode pageBreakInside) {
        set("page-break-inside", pageBreakInside);
    }

    @Override
    public Integer getOrphans() {
        return parseInteger(get("orphans"));
    }

    @Override
    public void setOrphans(Integer orphans) {
        set("orphans", orphans);
    }

    @Override
    public Integer getWidows() {
        return parseInteger(get("widows"));
    }

    @Override
    public void setWidows(Integer widows) {
        set("widows", widows);
    }

    @Override
    public void accept(ICss3Visitor visitor) {
        MutableCss3StyleDeclaration mutable = new MutableCss3StyleDeclaration(this);
        mutable.accept(visitor);
    }

}
