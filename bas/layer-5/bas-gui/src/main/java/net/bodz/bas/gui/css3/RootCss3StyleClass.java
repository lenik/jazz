package net.bodz.bas.gui.css3;

import java.io.Serializable;
import java.util.List;

import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.Measure;
import net.bodz.bas.util.Pair;

public class RootCss3StyleClass
        implements Serializable, ICss3StyleClass {

    private static final long serialVersionUID = 1L;

    OffsetType widthType;
    OffsetType heightType;
    Measure width;
    Measure height;

    OffsetType topType;
    OffsetType rightType;
    OffsetType bottomType;
    OffsetType leftType;
    Measure top;
    Measure right;
    Measure bottom;
    Measure left;

    OffsetType minWidthType;
    OffsetType maxWidthType;
    OffsetType minHeightType;
    OffsetType maxHeightType;
    Measure minWidth;
    Measure maxWidth;
    Measure minHeight;
    Measure maxHeight;

    IColor color;
    // background: color || image || repeat || attachment || position.
    IColor backgroundColor;
    BackgroundImageType backgroundImageType;
    String backgroundImage;
    BackgroundRepeatMode backgroundRepeat;
    BackgroundAttachmentMode backgroundAttachment;
    BackgroundPositionType backgroundPositionXType;
    BackgroundPositionType backgroundPositionYType;
    Measure backgroundPositionX;
    Measure backgroundPositionY;

    MeasureBox margin;
    MeasureBox padding;
    BorderBox border;
    BorderCollapseMode borderCollapse;
    Measure borderSpacingHorizontal;
    Measure borderSpacingVertical;
    OutlineColorMode outlineColorType;
    Border outline;

    Float opacity;
    ClearMode clear;
    DisplayMode display;
    PositionMode position;
    FloatMode float_;
    OverflowMode overflow;
    VisibilityMode visibility;
    ZIndexType zIndexType;
    int zIndex;

    // font: (style || variant || weight) size [/lineheight family]
    String fontFamily;
    FontSizeType fontSizeType;
    Measure fontSize;
    FontStyleMode fontStyle;
    FontVariantMode fontVariant;
    FontWeightMode fontWeight;
    DirectionMode direction;
    LetterSpacingType letterSpacingType;
    Measure letterSpacing;
    LineHeightType lineHeightType;
    Measure lineHeight;
    TextDecorationFlags textDecoration;
    TextTransformMode textTransform;
    TextIndentType textIndentType;
    Measure textIndent;
    TextAlignMode textAlign;
    VerticalAlignType verticalAlignType;
    Measure verticalAlign;
    UnicodeBidiMode unicodeBidi;
    WhiteSpaceMode whiteSpace;
    WordSpacingType wordSpacingType;
    Measure wordSpacing;

    ClipType clipType;
    String clip;
    CursorType cursorType;
    String cursor;

    ListStyleImageType listStyleImageType;
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

    @Override
    public ICss3StyleClass getParent() {
        return null;
    }

    @Override
    public OffsetType getWidthType() {
        return widthType;
    }

    @Override
    public OffsetType getHeightType() {
        return heightType;
    }

    @Override
    public Measure getWidth() {
        return width;
    }

    @Override
    public Measure getHeight() {
        return height;
    }

    @Override
    public void setWidth(OffsetType widthType, Measure width) {
        this.widthType = widthType;
        this.width = width;
    }

    @Override
    public void setHeight(OffsetType heightType, Measure height) {
        this.heightType = heightType;
        this.height = height;
    }

    @Override
    public OffsetType getTopType() {
        return topType;
    }

    @Override
    public OffsetType getRightType() {
        return rightType;
    }

    @Override
    public OffsetType getBottomType() {
        return bottomType;
    }

    @Override
    public OffsetType getLeftType() {
        return leftType;
    }

    @Override
    public Measure getTop() {
        return top;
    }

    @Override
    public Measure getRight() {
        return right;
    }

    @Override
    public Measure getBottom() {
        return bottom;
    }

    @Override
    public Measure getLeft() {
        return left;
    }

    @Override
    public void setTop(OffsetType topType, Measure top) {
        this.topType = topType;
        this.top = top;
    }

    @Override
    public void setRight(OffsetType rightType, Measure right) {
        this.rightType = rightType;
        this.right = right;
    }

    @Override
    public void setBottom(OffsetType bottomType, Measure bottom) {
        this.bottomType = bottomType;
        this.bottom = bottom;
    }

    @Override
    public void setLeft(OffsetType leftType, Measure left) {
        this.leftType = leftType;
        this.left = left;
    }

    @Override
    public OffsetType getMinWidthType() {
        return minWidthType;
    }

    @Override
    public OffsetType getMaxWidthType() {
        return maxWidthType;
    }

    @Override
    public OffsetType getMinHeightType() {
        return minHeightType;
    }

    @Override
    public OffsetType getMaxHeightType() {
        return maxHeightType;
    }

    @Override
    public Measure getMinWidth() {
        return minWidth;
    }

    @Override
    public Measure getMaxWidth() {
        return maxWidth;
    }

    @Override
    public Measure getMinHeight() {
        return minHeight;
    }

    @Override
    public Measure getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void setMinWidth(OffsetType minWidthType, Measure minWidth) {
        this.minWidthType = minWidthType;
        this.minWidth = minWidth;
    }

    @Override
    public void setMaxWidth(OffsetType maxWidthType, Measure maxWidth) {
        this.maxWidthType = maxWidthType;
        this.maxWidth = maxWidth;
    }

    @Override
    public void setMinHeight(OffsetType minHeightType, Measure minHeight) {
        this.minHeightType = minHeightType;
        this.minHeight = minHeight;
    }

    @Override
    public void setMaxHeight(OffsetType maxHeightType, Measure maxHeight) {
        this.maxHeightType = maxHeightType;
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
    public BackgroundImageType getBackgroundImageType() {
        return backgroundImageType;
    }

    @Override
    public String getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    public void setBackgroundImage(BackgroundImageType backgroundImageType, String backgroundImage) {
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
    public BackgroundPositionType getBackgroundPositionXType() {
        return backgroundPositionXType;
    }

    @Override
    public BackgroundPositionType getBackgroundPositionYType() {
        return backgroundPositionYType;
    }

    @Override
    public Measure getBackgroundPositionX() {
        return backgroundPositionX;
    }

    @Override
    public Measure getBackgroundPositionY() {
        return backgroundPositionY;
    }

    @Override
    public void setBackgroundPosition(BackgroundPositionType backgroundPositionXType, Measure backgroundPositionX,
            BackgroundPositionType backgroundPositionYType, Measure backgroundPositionY) {
        this.backgroundPositionXType = backgroundPositionXType;
        this.backgroundPositionX = backgroundPositionX;
        this.backgroundPositionYType = backgroundPositionYType;
        this.backgroundPositionY = backgroundPositionY;
    }

    @Override
    public MeasureBox getMargin() {
        return margin;
    }

    @Override
    public void setMargin(MeasureBox margin) {
        this.margin = margin;
    }

    @Override
    public MeasureBox getPadding() {
        return padding;
    }

    @Override
    public void setPadding(MeasureBox padding) {
        this.padding = padding;
    }

    @Override
    public BorderBox getBorder() {
        return border;
    }

    @Override
    public void setBorder(BorderBox border) {
        this.border = border;
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
    public Measure getBorderSpacingHorizontal() {
        return borderSpacingHorizontal;
    }

    @Override
    public Measure getBorderSpacingVertical() {
        return borderSpacingVertical;
    }

    @Override
    public void setBorderSpacing(Measure borderSpacingHorizontal, Measure borderSpacingVertical) {
        this.borderSpacingHorizontal = borderSpacingHorizontal;
        this.borderSpacingVertical = borderSpacingVertical;
    }

    @Override
    public OutlineColorMode getOutlineColorType() {
        return outlineColorType;
    }

    @Override
    public void setOutlineColorType(OutlineColorMode outlineColorType) {
        this.outlineColorType = outlineColorType;
    }

    @Override
    public Border getOutline() {
        return outline;
    }

    @Override
    public void setOutline(Border outline) {
        this.outline = outline;
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
    public ZIndexType getZIndexType() {
        return zIndexType;
    }

    @Override
    public int getZIndex() {
        return zIndex;
    }

    @Override
    public void setZIndex(ZIndexType zIndexType, int zIndex) {
        this.zIndexType = zIndexType;
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
    public FontSizeType getFontSizeType() {
        return fontSizeType;
    }

    @Override
    public Measure getFontSize() {
        return fontSize;
    }

    @Override
    public void setFontSize(FontSizeType fontSizeType, Measure fontSize) {
        this.fontSizeType = fontSizeType;
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
    public LetterSpacingType getLetterSpacingType() {
        return letterSpacingType;
    }

    @Override
    public Measure getLetterSpacing() {
        return letterSpacing;
    }

    @Override
    public void setLetterSpacing(LetterSpacingType letterSpacingType, Measure letterSpacing) {
        this.letterSpacingType = letterSpacingType;
        this.letterSpacing = letterSpacing;
    }

    @Override
    public LineHeightType getLineHeightType() {
        return lineHeightType;
    }

    @Override
    public Measure getLineHeight() {
        return lineHeight;
    }

    @Override
    public void setLineHeight(LineHeightType lineHeightType, Measure lineHeight) {
        this.lineHeightType = lineHeightType;
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
    public TextIndentType getTextIndentType() {
        return textIndentType;
    }

    @Override
    public Measure getTextIndent() {
        return textIndent;
    }

    @Override
    public void setTextIndent(TextIndentType textIndentType, Measure textIndent) {
        this.textIndentType = textIndentType;
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
    public VerticalAlignType getVerticalAlignType() {
        return verticalAlignType;
    }

    @Override
    public Measure getVerticalAlign() {
        return verticalAlign;
    }

    @Override
    public void setVerticalAlign(VerticalAlignType verticalAlignType, Measure verticalAlign) {
        this.verticalAlignType = verticalAlignType;
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
    public WordSpacingType getWordSpacingType() {
        return wordSpacingType;
    }

    @Override
    public Measure getWordSpacing() {
        return wordSpacing;
    }

    @Override
    public void setWordSpacing(WordSpacingType wordSpacingType, Measure wordSpacing) {
        this.wordSpacingType = wordSpacingType;
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
    public ListStyleImageType getListStyleImageType() {
        return listStyleImageType;
    }

    @Override
    public String getListStyleImage() {
        return listStyleImage;
    }

    @Override
    public void setListStyleImage(ListStyleImageType listStyleImageType, String listStyleImage) {
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

}
