package net.bodz.bas.gui.css3;

import java.util.List;

import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.std.LengthMeasure;
import net.bodz.bas.t.pojo.Pair;

public class NullCss3StyleDeclaration
        implements ICss3StyleDeclaration {

    @Override
    public ICss3StyleDeclaration getParent() {
        return null;
    }

    @Override
    public void setParent(ICss3StyleDeclaration parent) {
    }

    @Override
    public OffsetType getWidthType() {
        return null;
    }

    @Override
    public OffsetType getHeightType() {
        return null;
    }

    @Override
    public LengthMeasure getWidth() {
        return null;
    }

    @Override
    public LengthMeasure getHeight() {
        return null;
    }

    @Override
    public void setWidth(OffsetType widthType, LengthMeasure width) {
    }

    @Override
    public void setHeight(OffsetType heightType, LengthMeasure height) {
    }

    @Override
    public OffsetType getTopType() {
        return null;
    }

    @Override
    public OffsetType getRightType() {
        return null;
    }

    @Override
    public OffsetType getBottomType() {
        return null;
    }

    @Override
    public OffsetType getLeftType() {
        return null;
    }

    @Override
    public LengthMeasure getTop() {
        return null;
    }

    @Override
    public LengthMeasure getRight() {
        return null;
    }

    @Override
    public LengthMeasure getBottom() {
        return null;
    }

    @Override
    public LengthMeasure getLeft() {
        return null;
    }

    @Override
    public void setTop(OffsetType topType, LengthMeasure top) {
    }

    @Override
    public void setRight(OffsetType rightType, LengthMeasure right) {
    }

    @Override
    public void setBottom(OffsetType bottomType, LengthMeasure bottom) {
    }

    @Override
    public void setLeft(OffsetType leftType, LengthMeasure left) {
    }

    @Override
    public OffsetType getMinWidthType() {
        return null;
    }

    @Override
    public OffsetType getMaxWidthType() {
        return null;
    }

    @Override
    public OffsetType getMinHeightType() {
        return null;
    }

    @Override
    public OffsetType getMaxHeightType() {
        return null;
    }

    @Override
    public LengthMeasure getMinWidth() {
        return null;
    }

    @Override
    public LengthMeasure getMaxWidth() {
        return null;
    }

    @Override
    public LengthMeasure getMinHeight() {
        return null;
    }

    @Override
    public LengthMeasure getMaxHeight() {
        return null;
    }

    @Override
    public void setMinWidth(OffsetType minWidthType, LengthMeasure minWidth) {
    }

    @Override
    public void setMaxWidth(OffsetType maxWidthType, LengthMeasure maxWidth) {
    }

    @Override
    public void setMinHeight(OffsetType minHeightType, LengthMeasure minHeight) {
    }

    @Override
    public void setMaxHeight(OffsetType maxHeightType, LengthMeasure maxHeight) {
    }

    @Override
    public IColor getColor() {
        return null;
    }

    @Override
    public void setColor(IColor color) {
    }

    @Override
    public IColor getBackgroundColor() {
        return null;
    }

    @Override
    public void setBackgroundColor(IColor backgroundColor) {
    }

    @Override
    public BackgroundImageType getBackgroundImageType() {
        return null;
    }

    @Override
    public String getBackgroundImage() {
        return null;
    }

    @Override
    public void setBackgroundImage(BackgroundImageType backgroundImageType, String backgroundImage) {
    }

    @Override
    public BackgroundRepeatMode getBackgroundRepeat() {
        return null;
    }

    @Override
    public void setBackgroundRepeat(BackgroundRepeatMode backgroundRepeat) {
    }

    @Override
    public BackgroundAttachmentMode getBackgroundAttachment() {
        return null;
    }

    @Override
    public void setBackgroundAttachment(BackgroundAttachmentMode backgroundAttachment) {
    }

    @Override
    public BackgroundPositionType getBackgroundPositionXType() {
        return null;
    }

    @Override
    public BackgroundPositionType getBackgroundPositionYType() {
        return null;
    }

    @Override
    public LengthMeasure getBackgroundPositionX() {
        return null;
    }

    @Override
    public LengthMeasure getBackgroundPositionY() {
        return null;
    }

    @Override
    public void setBackgroundPosition(BackgroundPositionType backgroundPositionXType,
            LengthMeasure backgroundPositionX, BackgroundPositionType backgroundPositionYType,
            LengthMeasure backgroundPositionY) {
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
        return null;
    }

    @Override
    public void setBorderCollapse(BorderCollapseMode borderCollapse) {
    }

    @Override
    public LengthMeasure getBorderSpacingHorizontal() {
        return null;
    }

    @Override
    public LengthMeasure getBorderSpacingVertical() {
        return null;
    }

    @Override
    public void setBorderSpacing(LengthMeasure borderSpacingHorizontal, LengthMeasure borderSpacingVertical) {
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
        return null;
    }

    @Override
    public void setOpacity(Float opacity) {
    }

    @Override
    public ClearMode getClear() {
        return null;
    }

    @Override
    public void setClear(ClearMode clear) {
    }

    @Override
    public DisplayMode getDisplay() {
        return null;
    }

    @Override
    public void setDisplay(DisplayMode display) {
    }

    @Override
    public PositionMode getPosition() {
        return null;
    }

    @Override
    public void setPosition(PositionMode position) {
    }

    @Override
    public FloatMode getFloat() {
        return null;
    }

    @Override
    public void setFloat(FloatMode float_) {
    }

    @Override
    public OverflowMode getOverflow() {
        return null;
    }

    @Override
    public void setOverflow(OverflowMode overflow) {
    }

    @Override
    public VisibilityMode getVisibility() {
        return null;
    }

    @Override
    public void setVisibility(VisibilityMode visibility) {
    }

    @Override
    public ZIndexType getZIndexType() {
        return null;
    }

    @Override
    public int getZIndex() {
        return 0;
    }

    @Override
    public void setZIndex(ZIndexType zIndexType, int zIndex) {
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
    public LengthMeasure getFontSize() {
        return null;
    }

    @Override
    public void setFontSize(FontSizeType fontSizeType, LengthMeasure fontSize) {
    }

    @Override
    public FontStyleMode getFontStyle() {
        return null;
    }

    @Override
    public void setFontStyle(FontStyleMode fontStyle) {
    }

    @Override
    public FontVariantMode getFontVariant() {
        return null;
    }

    @Override
    public void setFontVariant(FontVariantMode fontVariant) {
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
        return null;
    }

    @Override
    public void setDirection(DirectionMode direction) {
    }

    @Override
    public LetterSpacingType getLetterSpacingType() {
        return null;
    }

    @Override
    public LengthMeasure getLetterSpacing() {
        return null;
    }

    @Override
    public void setLetterSpacing(LetterSpacingType letterSpacingType, LengthMeasure letterSpacing) {
    }

    @Override
    public LineHeightType getLineHeightType() {
        return null;
    }

    @Override
    public LengthMeasure getLineHeight() {
        return null;
    }

    @Override
    public void setLineHeight(LineHeightType lineHeightType, LengthMeasure lineHeight) {
    }

    @Override
    public TextDecorationFlags getTextDecoration() {
        return null;
    }

    @Override
    public void setTextDecoration(TextDecorationFlags textDecoration) {
    }

    @Override
    public TextTransformMode getTextTransform() {
        return null;
    }

    @Override
    public void setTextTransform(TextTransformMode textTransform) {
    }

    @Override
    public TextIndentType getTextIndentType() {
        return null;
    }

    @Override
    public LengthMeasure getTextIndent() {
        return null;
    }

    @Override
    public void setTextIndent(TextIndentType textIndentType, LengthMeasure textIndent) {
    }

    @Override
    public TextAlignMode getTextAlign() {
        return null;
    }

    @Override
    public void setTextAlign(TextAlignMode textAlign) {
    }

    @Override
    public VerticalAlignType getVerticalAlignType() {
        return null;
    }

    @Override
    public LengthMeasure getVerticalAlign() {
        return null;
    }

    @Override
    public void setVerticalAlign(VerticalAlignType verticalAlignType, LengthMeasure verticalAlign) {
    }

    @Override
    public QuotesType getQuotesType() {
        return null;
    }

    @Override
    public List<Pair<String, String>> getQuotes() {
        return null;
    }

    @Override
    public void setQuotes(QuotesType quotesType, List<Pair<String, String>> quotes) {
    }

    @Override
    public UnicodeBidiMode getUnicodeBidi() {
        return null;
    }

    @Override
    public void setUnicodeBidi(UnicodeBidiMode unicodeBidi) {
    }

    @Override
    public WhiteSpaceMode getWhiteSpace() {
        return null;
    }

    @Override
    public void setWhiteSpace(WhiteSpaceMode whiteSpace) {
    }

    @Override
    public WordSpacingType getWordSpacingType() {
        return null;
    }

    @Override
    public LengthMeasure getWordSpacing() {
        return null;
    }

    @Override
    public void setWordSpacing(WordSpacingType wordSpacingType, LengthMeasure wordSpacing) {
    }

    @Override
    public ClipType getClipType() {
        return null;
    }

    @Override
    public String getClip() {
        return null;
    }

    @Override
    public void setClip(ClipType clipType, String clip) {
    }

    @Override
    public CursorType getCursorType() {
        return null;
    }

    @Override
    public String getCursor() {
        return null;
    }

    @Override
    public void setCursor(CursorType cursorType, String cursor) {
    }

    @Override
    public ListStyleTypeMode getListStyleType() {
        return null;
    }

    @Override
    public void setListStyleType(ListStyleTypeMode listStyleType) {
    }

    @Override
    public ListStyleImageType getListStyleImageType() {
        return null;
    }

    @Override
    public String getListStyleImage() {
        return null;
    }

    @Override
    public void setListStyleImage(ListStyleImageType listStyleImageType, String listStyleImage) {
    }

    @Override
    public ListStylePositionMode getListStylePosition() {
        return null;
    }

    @Override
    public void setListStylePosition(ListStylePositionMode listStylePosition) {
    }

    @Override
    public TableLayoutMode getTableLayout() {
        return null;
    }

    @Override
    public void setTableLayout(TableLayoutMode tableLayout) {
    }

    @Override
    public CaptionSideMode getCaptionSide() {
        return null;
    }

    @Override
    public void setCaptionSide(CaptionSideMode captionSide) {
    }

    @Override
    public EmptyCellsMode getEmptyCells() {
        return null;
    }

    @Override
    public void setEmptyCells(EmptyCellsMode emptyCells) {
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
        return null;
    }

    @Override
    public void setCounterIncrement(String counterIncrement) {
    }

    @Override
    public String getCounterReset() {
        return null;
    }

    @Override
    public void setCounterReset(String counterReset) {
    }

    @Override
    public PageBreakMode getPageBreakAfter() {
        return null;
    }

    @Override
    public void setPageBreakAfter(PageBreakMode pageBreakAfter) {
    }

    @Override
    public PageBreakMode getPageBreakBefore() {
        return null;
    }

    @Override
    public void setPageBreakBefore(PageBreakMode pageBreakBefore) {
    }

    @Override
    public PageBreakMode getPageBreakInside() {
        return null;
    }

    @Override
    public void setPageBreakInside(PageBreakMode pageBreakInside) {
    }

    @Override
    public Integer getOrphans() {
        return null;
    }

    @Override
    public void setOrphans(Integer orphans) {
    }

    @Override
    public Integer getWidows() {
        return null;
    }

    @Override
    public void setWidows(Integer widows) {
    }

}
