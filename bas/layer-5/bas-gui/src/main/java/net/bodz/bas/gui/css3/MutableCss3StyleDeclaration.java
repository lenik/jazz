package net.bodz.bas.gui.css3;

import java.util.Collections;
import java.util.List;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.t.pojo.Pair;

public class MutableCss3StyleDeclaration
        extends AbstractCss3Properties
        implements ICss3StyleDeclaration {

    private static final long serialVersionUID = 1L;

    ICss3StyleDeclaration parent;

    ICss3Length width;
    ICss3Length height;

    ICss3Length top;
    ICss3Length right;
    ICss3Length bottom;
    ICss3Length left;

    ICss3Length minWidth;
    ICss3Length maxWidth;
    ICss3Length minHeight;
    ICss3Length maxHeight;

    IColor color;
    // background: color || image || repeat || attachment || position.
    IColor backgroundColor;
    ImageType backgroundImageType;
    String backgroundImage;
    BackgroundRepeatMode backgroundRepeat;
    BackgroundAttachmentMode backgroundAttachment;
    ICss3Length backgroundPositionX;
    ICss3Length backgroundPositionY;

    ICss3Length margin;
    ICss3Length marginTop;
    ICss3Length marginRight;
    ICss3Length marginBottom;
    ICss3Length marginLeft;
    ICss3Length padding;
    ICss3Length paddingTop;
    ICss3Length paddingRight;
    ICss3Length paddingBottom;
    ICss3Length paddingLeft;

    Border border;
    Border borderTop;
    Border borderRight;
    Border borderBottom;
    Border borderLeft;
    ICss3Length borderRadius;
    ICss3Length borderTopLeftRadius;
    ICss3Length borderTopRightRadius;
    ICss3Length borderBottomRightRadius;
    ICss3Length borderBottomLeftRadius;
    BorderCollapseMode borderCollapse;
    ICss3Length borderHorizontalSpacing;
    ICss3Length borderVerticalSpacing;

    Border outline;
    boolean outlineInvert;
    ICss3Length outlineOffset;

    Float opacity;
    ClearMode clear;
    DisplayMode display;
    PositionMode position;
    FloatMode float_;
    OverflowMode overflow;
    VisibilityMode visibility;
    ICss3Int zIndex;

    // font: (style || variant || weight) size [/lineheight family]
    String fontFamily;
    ICss3Length fontSize;
    FontStyleMode fontStyle;
    FontVariantMode fontVariant;
    FontWeightMode fontWeight;
    DirectionMode direction;
    ICss3Length letterSpacing;
    ICss3Length lineHeight;
    TextDecorationFlags textDecoration;
    TextTransformMode textTransform;
    ICss3Length textIndent;
    TextAlignMode textAlign;
    ICss3Length verticalAlign;
    UnicodeBidiMode unicodeBidi;
    WhiteSpaceMode whiteSpace;
    ICss3Length wordSpacing;

    ClipType clipType;
    String clip;
    CursorType cursorType;
    String cursor;

    ImageType listStyleImageType;
    String listStyleImage;
    ListStylePositionMode listStylePosition;
    ListStyleTypeMode listStyleType;
    TableLayoutMode tableLayout;
    CaptionSideMode captionSide;
    EmptyCellsMode emptyCells;

    ContentType contentType;
    List<ContentItem> contentItems;
    String counterIncrement;
    String counterReset;
    QuotesType quotesType;
    List<Pair<String, String>> quotes;

    PageBreakMode pageBreakAfter;
    PageBreakMode pageBreakBefore;
    PageBreakMode pageBreakInside;
    Integer orphans;
    Integer widows;

    public MutableCss3StyleDeclaration() {
        super(Collections.<String, String> emptyMap());
    }

    public MutableCss3StyleDeclaration(ICss3Properties properties) {
        this();
        this.width = properties.getWidth();
        this.height = properties.getHeight();
        this.top = properties.getTop();
        this.right = properties.getRight();
        this.bottom = properties.getBottom();
        this.left = properties.getLeft();
        this.minWidth = properties.getMinWidth();
        this.maxWidth = properties.getMaxWidth();
        this.minHeight = properties.getMinHeight();
        this.maxHeight = properties.getMaxHeight();
        this.color = properties.getColor();
        this.backgroundColor = properties.getBackgroundColor();
        this.backgroundImageType = properties.getBackgroundImageType();
        this.backgroundImage = properties.getBackgroundImage();
        this.backgroundRepeat = properties.getBackgroundRepeat();
        this.backgroundAttachment = properties.getBackgroundAttachment();
        this.backgroundPositionX = properties.getBackgroundPositionX();
        this.backgroundPositionY = properties.getBackgroundPositionY();
        this.margin = properties.getMargin();
        this.marginTop = properties.getMarginTop();
        this.marginRight = properties.getMarginRight();
        this.marginBottom = properties.getMarginBottom();
        this.marginLeft = properties.getMarginLeft();
        this.padding = properties.getPadding();
        this.paddingTop = properties.getPaddingTop();
        this.paddingRight = properties.getPaddingRight();
        this.paddingBottom = properties.getPaddingBottom();
        this.paddingLeft = properties.getPaddingLeft();
        this.border = properties.getBorder();
        this.borderTop = properties.getBorderTop();
        this.borderRight = properties.getBorderRight();
        this.borderBottom = properties.getBorderBottom();
        this.borderLeft = properties.getBorderLeft();
        this.borderRadius = properties.getBorderRadius();
        this.borderTopLeftRadius = properties.getBorderTopLeftRadius();
        this.borderTopRightRadius = properties.getBorderTopRightRadius();
        this.borderBottomRightRadius = properties.getBorderBottomRightRadius();
        this.borderBottomLeftRadius = properties.getBorderBottomLeftRadius();
        this.borderCollapse = properties.getBorderCollapse();
        this.borderHorizontalSpacing = properties.getBorderHorizontalSpacing();
        this.borderVerticalSpacing = properties.getBorderVerticalSpacing();
        this.outline = properties.getOutline();
        this.outlineOffset = properties.getOutlineOffset();
        this.opacity = properties.getOpacity();
        this.clear = properties.getClear();
        this.display = properties.getDisplay();
        this.position = properties.getPosition();
        this.float_ = properties.getFloat();
        this.overflow = properties.getOverflow();
        this.visibility = properties.getVisibility();
        this.zIndex = properties.getZIndex();
        this.fontFamily = properties.getFontFamily();
        this.fontSize = properties.getFontSize();
        this.fontStyle = properties.getFontStyle();
        this.fontVariant = properties.getFontVariant();
        this.fontWeight = properties.getFontWeight();
        this.direction = properties.getDirection();
        this.letterSpacing = properties.getLetterSpacing();
        this.lineHeight = properties.getLineHeight();
        this.textDecoration = properties.getTextDecoration();
        this.textTransform = properties.getTextTransform();
        this.textIndent = properties.getTextIndent();
        this.textAlign = properties.getTextAlign();
        this.verticalAlign = properties.getVerticalAlign();
        this.quotesType = properties.getQuotesType();
        this.quotes = properties.getQuotes();
        this.unicodeBidi = properties.getUnicodeBidi();
        this.whiteSpace = properties.getWhiteSpace();
        this.wordSpacing = properties.getWordSpacing();
        this.clipType = properties.getClipType();
        this.clip = properties.getClip();
        this.cursorType = properties.getCursorType();
        this.cursor = properties.getCursor();
        this.listStyleType = properties.getListStyleType();
        this.listStyleImage = properties.getListStyleImage();
        this.listStylePosition = properties.getListStylePosition();
        this.tableLayout = properties.getTableLayout();
        this.captionSide = properties.getCaptionSide();
        this.emptyCells = properties.getEmptyCells();
        this.contentType = properties.getContentType();
        this.contentItems = properties.getContentItems();
        this.counterIncrement = properties.getCounterIncrement();
        this.counterReset = properties.getCounterReset();
        this.pageBreakAfter = properties.getPageBreakAfter();
        this.pageBreakBefore = properties.getPageBreakBefore();
        this.pageBreakInside = properties.getPageBreakInside();
        this.orphans = properties.getOrphans();
        this.widows = properties.getWidows();
    }

    @Override
    public ICss3StyleDeclaration getParent() {
        return parent;
    }

    public void setParent(ICss3StyleDeclaration parent) {
        this.parent = parent;
    }

    @Override
    public String get(String key, int maxInherits) {
        throw new NotImplementedException();
    }

    @Override
    public ICss3Length getWidth() {
        return width;
    }

    @Override
    public ICss3Length getHeight() {
        return height;
    }

    @Override
    public void setWidth(ICss3Length width) {
        this.width = width;
    }

    @Override
    public void setHeight(ICss3Length height) {
        this.height = height;
    }

    @Override
    public ICss3Length getTop() {
        return top;
    }

    @Override
    public ICss3Length getRight() {
        return right;
    }

    @Override
    public ICss3Length getBottom() {
        return bottom;
    }

    @Override
    public ICss3Length getLeft() {
        return left;
    }

    @Override
    public void setTop(ICss3Length top) {
        this.top = top;
    }

    @Override
    public void setRight(ICss3Length right) {
        this.right = right;
    }

    @Override
    public void setBottom(ICss3Length bottom) {
        this.bottom = bottom;
    }

    @Override
    public void setLeft(ICss3Length left) {
        this.left = left;
    }

    @Override
    public ICss3Length getMinWidth() {
        return minWidth;
    }

    @Override
    public ICss3Length getMaxWidth() {
        return maxWidth;
    }

    @Override
    public ICss3Length getMinHeight() {
        return minHeight;
    }

    @Override
    public ICss3Length getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void setMinWidth(ICss3Length minWidth) {
        this.minWidth = minWidth;
    }

    @Override
    public void setMaxWidth(ICss3Length maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    public void setMinHeight(ICss3Length minHeight) {
        this.minHeight = minHeight;
    }

    @Override
    public void setMaxHeight(ICss3Length maxHeight) {
        this.maxHeight = maxHeight;
    }

    @Override
    public IColor getColor() {
        return color;
    }

    @Override
    public void setColor(IColor color) {
        this.color = color;
    }

    @Override
    public IColor getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(IColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public ImageType getBackgroundImageType() {
        return backgroundImageType;
    }

    @Override
    public String getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    public void setBackgroundImage(ImageType backgroundImageType, String backgroundImage) {
        this.backgroundImageType = backgroundImageType;
        this.backgroundImage = backgroundImage;
    }

    @Override
    public BackgroundRepeatMode getBackgroundRepeat() {
        return backgroundRepeat;
    }

    @Override
    public void setBackgroundRepeat(BackgroundRepeatMode backgroundRepeat) {
        this.backgroundRepeat = backgroundRepeat;
    }

    @Override
    public BackgroundAttachmentMode getBackgroundAttachment() {
        return backgroundAttachment;
    }

    @Override
    public void setBackgroundAttachment(BackgroundAttachmentMode backgroundAttachment) {
        this.backgroundAttachment = backgroundAttachment;
    }

    @Override
    public ICss3Length getBackgroundPositionX() {
        return backgroundPositionX;
    }

    @Override
    public ICss3Length getBackgroundPositionY() {
        return backgroundPositionY;
    }

    @Override
    public void setBackgroundPositionX(ICss3Length backgroundPositionX) {
        this.backgroundPositionX = backgroundPositionX;
    }

    @Override
    public void setBackgroundPositionY(ICss3Length backgroundPositionY) {
        this.backgroundPositionY = backgroundPositionY;
    }

    @Override
    public void setBackgroundPosition(ICss3Length backgroundPositionX, ICss3Length backgroundPositionY) {
        this.backgroundPositionX = backgroundPositionX;
        this.backgroundPositionY = backgroundPositionY;
    }

    @Override
    public ICss3Length getMargin() {
        return margin;
    }

    @Override
    public void setMargin(ICss3Length margin) {
        this.margin = margin;
    }

    @Override
    public ICss3Length getMarginTop() {
        return marginTop;
    }

    @Override
    public void setMarginTop(ICss3Length marginTop) {
        this.marginTop = marginTop;
    }

    @Override
    public ICss3Length getMarginRight() {
        return marginRight;
    }

    @Override
    public void setMarginRight(ICss3Length marginRight) {
        this.marginRight = marginRight;
    }

    @Override
    public ICss3Length getMarginBottom() {
        return marginBottom;
    }

    @Override
    public void setMarginBottom(ICss3Length marginBottom) {
        this.marginBottom = marginBottom;
    }

    @Override
    public ICss3Length getMarginLeft() {
        return marginLeft;
    }

    @Override
    public void setMarginLeft(ICss3Length marginLeft) {
        this.marginLeft = marginLeft;
    }

    @Override
    public ICss3Length getPadding() {
        return padding;
    }

    @Override
    public void setPadding(ICss3Length padding) {
        this.padding = padding;
    }

    @Override
    public ICss3Length getPaddingTop() {
        return paddingTop;
    }

    @Override
    public void setPaddingTop(ICss3Length paddingTop) {
        this.paddingTop = paddingTop;
    }

    @Override
    public ICss3Length getPaddingRight() {
        return paddingRight;
    }

    @Override
    public void setPaddingRight(ICss3Length paddingRight) {
        this.paddingRight = paddingRight;
    }

    @Override
    public ICss3Length getPaddingBottom() {
        return paddingBottom;
    }

    @Override
    public void setPaddingBottom(ICss3Length paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    @Override
    public ICss3Length getPaddingLeft() {
        return paddingLeft;
    }

    @Override
    public void setPaddingLeft(ICss3Length paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    @Override
    public Border getBorder() {
        return border;
    }

    @Override
    public void setBorder(Border border) {
        this.border = border;
    }

    @Override
    public Border getBorderTop() {
        return borderTop;
    }

    @Override
    public void setBorderTop(Border borderTop) {
        this.borderTop = borderTop;
    }

    @Override
    public Border getBorderRight() {
        return borderRight;
    }

    @Override
    public void setBorderRight(Border borderRight) {
        this.borderRight = borderRight;
    }

    @Override
    public Border getBorderBottom() {
        return borderBottom;
    }

    @Override
    public void setBorderBottom(Border borderBottom) {
        this.borderBottom = borderBottom;
    }

    @Override
    public Border getBorderLeft() {
        return borderLeft;
    }

    @Override
    public void setBorderLeft(Border borderLeft) {
        this.borderLeft = borderLeft;
    }

    @Override
    public ICss3Length getBorderRadius() {
        return borderRadius;
    }

    @Override
    public void setBorderRadius(ICss3Length borderRadius) {
        this.borderRadius = borderRadius;
    }

    @Override
    public ICss3Length getBorderTopLeftRadius() {
        return borderTopLeftRadius;
    }

    @Override
    public void setBorderTopLeftRadius(ICss3Length borderTopLeftRadius) {
        this.borderTopLeftRadius = borderTopLeftRadius;
    }

    @Override
    public ICss3Length getBorderTopRightRadius() {
        return borderTopRightRadius;
    }

    @Override
    public void setBorderTopRightRadius(ICss3Length borderTopRightRadius) {
        this.borderTopRightRadius = borderTopRightRadius;
    }

    @Override
    public ICss3Length getBorderBottomRightRadius() {
        return borderBottomRightRadius;
    }

    @Override
    public void setBorderBottomRightRadius(ICss3Length borderBottomRightRadius) {
        this.borderBottomRightRadius = borderBottomRightRadius;
    }

    @Override
    public ICss3Length getBorderBottomLeftRadius() {
        return borderBottomLeftRadius;
    }

    @Override
    public void setBorderBottomLeftRadius(ICss3Length borderBottomLeftRadius) {
        this.borderBottomLeftRadius = borderBottomLeftRadius;
    }

    @Override
    public BorderCollapseMode getBorderCollapse() {
        return borderCollapse;
    }

    @Override
    public void setBorderCollapse(BorderCollapseMode borderCollapse) {
        this.borderCollapse = borderCollapse;
    }

    @Override
    public ICss3Length getBorderHorizontalSpacing() {
        return borderHorizontalSpacing;
    }

    @Override
    public ICss3Length getBorderVerticalSpacing() {
        return borderVerticalSpacing;
    }

    @Override
    public void setBorderSpacing(ICss3Length borderSpacingHorizontal, ICss3Length borderSpacingVertical) {
        this.borderHorizontalSpacing = borderSpacingHorizontal;
        this.borderVerticalSpacing = borderSpacingVertical;
    }

    @Override
    public Border getOutline() {
        return outline;
    }

    @Override
    public boolean isOutlineInvert() {
        return outlineInvert;
    }

    @Override
    public void setOutline(Border outline, boolean invert) {
        this.outline = outline;
        this.outlineInvert = invert;
    }

    @Override
    public ICss3Length getOutlineOffset() {
        return outlineOffset;
    }

    @Override
    public void setOutlineOffset(ICss3Length outlineOffset) {
        this.outlineOffset = outlineOffset;
    }

    @Override
    public Float getOpacity() {
        return opacity;
    }

    @Override
    public void setOpacity(Float opacity) {
        this.opacity = opacity;
    }

    @Override
    public ClearMode getClear() {
        return clear;
    }

    @Override
    public void setClear(ClearMode clear) {
        this.clear = clear;
    }

    @Override
    public DisplayMode getDisplay() {
        return display;
    }

    @Override
    public void setDisplay(DisplayMode display) {
        this.display = display;
    }

    @Override
    public PositionMode getPosition() {
        return position;
    }

    @Override
    public void setPosition(PositionMode position) {
        this.position = position;
    }

    @Override
    public FloatMode getFloat() {
        return float_;
    }

    @Override
    public void setFloat(FloatMode float_) {
        this.float_ = float_;
    }

    @Override
    public OverflowMode getOverflow() {
        return overflow;
    }

    @Override
    public void setOverflow(OverflowMode overflow) {
        this.overflow = overflow;
    }

    @Override
    public VisibilityMode getVisibility() {
        return visibility;
    }

    @Override
    public void setVisibility(VisibilityMode visibility) {
        this.visibility = visibility;
    }

    @Override
    public ICss3Int getZIndex() {
        return zIndex;
    }

    @Override
    public void setZIndex(ICss3Int zIndex) {
        this.zIndex = zIndex;
    }

    @Override
    public String getFontFamily() {
        return fontFamily;
    }

    @Override
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    @Override
    public ICss3Length getFontSize() {
        return fontSize;
    }

    @Override
    public void setFontSize(ICss3Length fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public FontStyleMode getFontStyle() {
        return fontStyle;
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
        this.fontStyle = fontStyle;
    }

    @Override
    public FontVariantMode getFontVariant() {
        return fontVariant;
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
        this.fontVariant = fontVariant;
    }

    @Override
    public FontWeightMode getFontWeight() {
        return fontWeight;
    }

    @Override
    public void setFontWeight(FontWeightMode fontWeight) {
        this.fontWeight = fontWeight;
    }

    @Override
    public DirectionMode getDirection() {
        return direction;
    }

    @Override
    public void setDirection(DirectionMode direction) {
        this.direction = direction;
    }

    @Override
    public ICss3Length getLetterSpacing() {
        return letterSpacing;
    }

    @Override
    public void setLetterSpacing(ICss3Length letterSpacing) {
        this.letterSpacing = letterSpacing;
    }

    @Override
    public ICss3Length getLineHeight() {
        return lineHeight;
    }

    @Override
    public void setLineHeight(ICss3Length lineHeight) {
        this.lineHeight = lineHeight;
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        return textDecoration;
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
        this.textDecoration = textDecoration;
    }

    @Override
    public TextTransformMode getTextTransform() {
        return textTransform;
    }

    @Override
    public void setTextTransform(TextTransformMode textTransform) {
        this.textTransform = textTransform;
    }

    @Override
    public ICss3Length getTextIndent() {
        return textIndent;
    }

    @Override
    public void setTextIndent(ICss3Length textIndent) {
        this.textIndent = textIndent;
    }

    @Override
    public TextAlignMode getTextAlign() {
        return textAlign;
    }

    @Override
    public void setTextAlign(TextAlignMode textAlign) {
        this.textAlign = textAlign;
    }

    @Override
    public ICss3Length getVerticalAlign() {
        return verticalAlign;
    }

    @Override
    public void setVerticalAlign(ICss3Length verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    @Override
    public UnicodeBidiMode getUnicodeBidi() {
        return unicodeBidi;
    }

    @Override
    public void setUnicodeBidi(UnicodeBidiMode unicodeBidi) {
        this.unicodeBidi = unicodeBidi;
    }

    @Override
    public WhiteSpaceMode getWhiteSpace() {
        return whiteSpace;
    }

    @Override
    public void setWhiteSpace(WhiteSpaceMode whiteSpace) {
        this.whiteSpace = whiteSpace;
    }

    @Override
    public ICss3Length getWordSpacing() {
        return wordSpacing;
    }

    @Override
    public void setWordSpacing(ICss3Length wordSpacing) {
        this.wordSpacing = wordSpacing;
    }

    @Override
    public ClipType getClipType() {
        return clipType;
    }

    @Override
    public String getClip() {
        return clip;
    }

    @Override
    public void setClip(ClipType clipType, String clip) {
        this.clipType = clipType;
        this.clip = clip;
    }

    @Override
    public CursorType getCursorType() {
        return cursorType;
    }

    @Override
    public String getCursor() {
        return cursor;
    }

    @Override
    public void setCursor(CursorType cursorType, String cursor) {
        this.cursorType = cursorType;
        this.cursor = cursor;
    }

    @Override
    public ImageType getListStyleImageType() {
        return listStyleImageType;
    }

    @Override
    public String getListStyleImage() {
        return listStyleImage;
    }

    @Override
    public void setListStyleImage(ImageType listStyleImageType, String listStyleImage) {
        this.listStyleImageType = listStyleImageType;
        this.listStyleImage = listStyleImage;
    }

    @Override
    public ListStylePositionMode getListStylePosition() {
        return listStylePosition;
    }

    @Override
    public void setListStylePosition(ListStylePositionMode listStylePosition) {
        this.listStylePosition = listStylePosition;
    }

    @Override
    public ListStyleTypeMode getListStyleType() {
        return listStyleType;
    }

    @Override
    public void setListStyleType(ListStyleTypeMode listStyleType) {
        this.listStyleType = listStyleType;
    }

    @Override
    public TableLayoutMode getTableLayout() {
        return tableLayout;
    }

    @Override
    public void setTableLayout(TableLayoutMode tableLayout) {
        this.tableLayout = tableLayout;
    }

    @Override
    public CaptionSideMode getCaptionSide() {
        return captionSide;
    }

    @Override
    public void setCaptionSide(CaptionSideMode captionSide) {
        this.captionSide = captionSide;
    }

    @Override
    public EmptyCellsMode getEmptyCells() {
        return emptyCells;
    }

    @Override
    public void setEmptyCells(EmptyCellsMode emptyCells) {
        this.emptyCells = emptyCells;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public List<ContentItem> getContentItems() {
        return contentItems;
    }

    @Override
    public void setContent(ContentType contentType, List<ContentItem> contentItems) {
        this.contentType = contentType;
        this.contentItems = contentItems;
    }

    @Override
    public String getCounterIncrement() {
        return counterIncrement;
    }

    @Override
    public void setCounterIncrement(String counterIncrement) {
        this.counterIncrement = counterIncrement;
    }

    @Override
    public String getCounterReset() {
        return counterReset;
    }

    @Override
    public void setCounterReset(String counterReset) {
        this.counterReset = counterReset;
    }

    @Override
    public QuotesType getQuotesType() {
        return quotesType;
    }

    @Override
    public List<Pair<String, String>> getQuotes() {
        return quotes;
    }

    @Override
    public void setQuotes(QuotesType quotesType, List<Pair<String, String>> quotes) {
        this.quotesType = quotesType;
        this.quotes = quotes;
    }

    @Override
    public PageBreakMode getPageBreakAfter() {
        return pageBreakAfter;
    }

    @Override
    public void setPageBreakAfter(PageBreakMode pageBreakAfter) {
        this.pageBreakAfter = pageBreakAfter;
    }

    @Override
    public PageBreakMode getPageBreakBefore() {
        return pageBreakBefore;
    }

    @Override
    public void setPageBreakBefore(PageBreakMode pageBreakBefore) {
        this.pageBreakBefore = pageBreakBefore;
    }

    @Override
    public PageBreakMode getPageBreakInside() {
        return pageBreakInside;
    }

    @Override
    public void setPageBreakInside(PageBreakMode pageBreakInside) {
        this.pageBreakInside = pageBreakInside;
    }

    @Override
    public Integer getOrphans() {
        return orphans;
    }

    @Override
    public void setOrphans(Integer orphans) {
        this.orphans = orphans;
    }

    @Override
    public Integer getWidows() {
        return widows;
    }

    @Override
    public void setWidows(Integer widows) {
        this.widows = widows;
    }

    @Override
    public void accept(ICss3Visitor visitor) {
        if (width != null)
            visitor.width(width);
        if (height != null)
            visitor.height(height);
        if (top != null)
            visitor.top(top);
        if (right != null)
            visitor.right(right);
        if (bottom != null)
            visitor.bottom(bottom);
        if (left != null)
            visitor.left(left);
        if (minWidth != null)
            visitor.minWidth(minWidth);
        if (maxWidth != null)
            visitor.maxWidth(maxWidth);
        if (minHeight != null)
            visitor.minHeight(minHeight);
        if (maxHeight != null)
            visitor.maxHeight(maxHeight);
        if (color != null)
            visitor.color(color);
        if (backgroundColor != null)
            visitor.backgroundColor(backgroundColor);
        if (backgroundImageType != null)
            visitor.backgroundImage(backgroundImageType, backgroundImage);
        if (backgroundRepeat != null)
            visitor.backgroundRepeat(backgroundRepeat);
        if (backgroundAttachment != null)
            visitor.backgroundAttachment(backgroundAttachment);
        if (backgroundPositionX != null && backgroundPositionY != null)
            visitor.backgroundPosition(backgroundPositionX, backgroundPositionY);
        if (margin != null)
            visitor.margin(margin);
        if (marginTop != null)
            visitor.marginTop(marginTop);
        if (marginRight != null)
            visitor.marginRight(marginRight);
        if (marginBottom != null)
            visitor.marginBottom(marginBottom);
        if (marginLeft != null)
            visitor.marginLeft(marginLeft);
        if (padding != null)
            visitor.padding(padding);
        if (paddingTop != null)
            visitor.paddingTop(paddingTop);
        if (paddingRight != null)
            visitor.paddingRight(paddingRight);
        if (paddingBottom != null)
            visitor.paddingBottom(paddingBottom);
        if (paddingLeft != null)
            visitor.paddingLeft(paddingLeft);
        if (border != null)
            visitor.border(border);
        if (borderTop != null)
            visitor.borderTop(borderTop);
        if (borderRight != null)
            visitor.borderRight(borderRight);
        if (borderBottom != null)
            visitor.borderBottom(borderBottom);
        if (borderLeft != null)
            visitor.borderLeft(borderLeft);
        if (borderRadius != null)
            visitor.borderRadius(borderRadius);
        if (borderTopLeftRadius != null)
            visitor.borderTopLeftRadius(borderTopLeftRadius);
        if (borderTopRightRadius != null)
            visitor.borderTopRightRadius(borderTopRightRadius);
        if (borderBottomRightRadius != null)
            visitor.borderBottomRightRadius(borderBottomRightRadius);
        if (borderBottomLeftRadius != null)
            visitor.borderBottomLeftRadius(borderBottomLeftRadius);
        if (borderCollapse != null)
            visitor.borderCollapse(borderCollapse);
        if (borderHorizontalSpacing != null && borderVerticalSpacing != null)
            visitor.borderSpacing(borderHorizontalSpacing, borderVerticalSpacing);
        if (outline != null)
            visitor.outline(outline, outlineInvert);
        if (outlineOffset != null)
            visitor.outlineOffset(outlineOffset);
        if (opacity != null)
            visitor.opacity(opacity);
        if (clear != null)
            visitor.clear(clear);
        if (display != null)
            visitor.display(display);
        if (position != null)
            visitor.position(position);
        if (float_ != null)
            visitor.float_(float_);
        if (overflow != null)
            visitor.overflow(overflow);
        if (visibility != null)
            visitor.visibility(visibility);
        if (zIndex != null)
            visitor.zIndex(zIndex);
        if (fontFamily != null)
            visitor.fontFamily(fontFamily);
        if (fontSize != null)
            visitor.fontSize(fontSize);
        if (fontStyle != null)
            visitor.fontStyle(fontStyle);
        if (fontVariant != null)
            visitor.fontVariant(fontVariant);
        if (fontWeight != null)
            visitor.fontWeight(fontWeight);
        if (direction != null)
            visitor.direction(direction);
        if (letterSpacing != null)
            visitor.letterSpacing(letterSpacing);
        if (lineHeight != null)
            visitor.lineHeight(lineHeight);
        if (textDecoration != null)
            visitor.textDecoration(textDecoration);
        if (textTransform != null)
            visitor.textTransform(textTransform);
        if (textIndent != null)
            visitor.textIndent(textIndent);
        if (textAlign != null)
            visitor.textAlign(textAlign);
        if (verticalAlign != null)
            visitor.verticalAlign(verticalAlign);
        if (quotesType != null)
            visitor.quotes(quotesType, quotes);
        if (unicodeBidi != null)
            visitor.unicodeBidi(unicodeBidi);
        if (whiteSpace != null)
            visitor.whiteSpace(whiteSpace);
        if (wordSpacing != null)
            visitor.wordSpacing(wordSpacing);
        if (clipType != null)
            visitor.clip(clipType, clip);
        if (cursorType != null)
            visitor.cursor(cursorType, cursor);
        if (listStyleType != null)
            visitor.listStyleType(listStyleType);
        if (listStyleImage != null)
            visitor.listStyleImage(listStyleImage);
        if (listStylePosition != null)
            visitor.listStylePosition(listStylePosition);
        if (tableLayout != null)
            visitor.tableLayout(tableLayout);
        if (captionSide != null)
            visitor.captionSide(captionSide);
        if (emptyCells != null)
            visitor.emptyCells(emptyCells);
        if (contentType != null)
            visitor.content(contentType, contentItems);
        if (counterIncrement != null)
            visitor.counterIncrement(counterIncrement);
        if (counterReset != null)
            visitor.counterReset(counterReset);
        if (pageBreakAfter != null)
            visitor.pageBreakAfter(pageBreakAfter);
        if (pageBreakBefore != null)
            visitor.pageBreakBefore(pageBreakBefore);
        if (pageBreakInside != null)
            visitor.pageBreakInside(pageBreakInside);
        if (orphans != null)
            visitor.orphans(orphans);
        if (widows != null)
            visitor.widows(widows);
    }
}
