package net.bodz.bas.ui.css3;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.map.IParsedMap;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.ui.css3.property.*;
import net.bodz.bas.ui.style.IColor;

public class NullCss3StyleDeclaration
        implements ICss3StyleDeclaration {

    /** ⇱ Implementation Of {@link IParsedMap}. */
    /* _____________________________ */static section.iface __MAP__;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        return null;
    }

    @Override
    public String put(String key, String value) {
        return null;
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
    }

    @Override
    public void clear() {
    }

    @Override
    public Set<String> keySet() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> values() {
        return Collections.emptyList();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return Collections.emptySet();
    }

    /** ⇱ Implementation Of {@link ICss3StyleDeclaration}. */
    /* _____________________________ */static section.iface __CSS3_DECL__;

    @Override
    public ICss3StyleDeclaration getParent() {
        return null;
    }

    @Override
    public String get(String key, int maxInherits) {
        return null;
    }

    @Override
    public ICss3Length getWidth() {
        return null;
    }

    @Override
    public ICss3Length getHeight() {
        return null;
    }

    @Override
    public void setWidth(ICss3Length width) {
    }

    @Override
    public void setHeight(ICss3Length height) {
    }

    @Override
    public ICss3Length getTop() {
        return null;
    }

    @Override
    public ICss3Length getRight() {
        return null;
    }

    @Override
    public ICss3Length getBottom() {
        return null;
    }

    @Override
    public ICss3Length getLeft() {
        return null;
    }

    @Override
    public void setTop(ICss3Length top) {
    }

    @Override
    public void setRight(ICss3Length right) {
    }

    @Override
    public void setBottom(ICss3Length bottom) {
    }

    @Override
    public void setLeft(ICss3Length left) {
    }

    @Override
    public ICss3Length getMinWidth() {
        return null;
    }

    @Override
    public ICss3Length getMaxWidth() {
        return null;
    }

    @Override
    public ICss3Length getMinHeight() {
        return null;
    }

    @Override
    public ICss3Length getMaxHeight() {
        return null;
    }

    @Override
    public void setMinWidth(ICss3Length minWidth) {
    }

    @Override
    public void setMaxWidth(ICss3Length maxWidth) {
    }

    @Override
    public void setMinHeight(ICss3Length minHeight) {
    }

    @Override
    public void setMaxHeight(ICss3Length maxHeight) {
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
    public ImageType getBackgroundImageType() {
        return null;
    }

    @Override
    public String getBackgroundImage() {
        return null;
    }

    @Override
    public void setBackgroundImage(ImageType backgroundImageType, String backgroundImage) {
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
    public ICss3Length getBackgroundPositionX() {
        return null;
    }

    @Override
    public ICss3Length getBackgroundPositionY() {
        return null;
    }

    @Override
    public void setBackgroundPositionX(ICss3Length backgroundPositionX) {
    }

    @Override
    public void setBackgroundPositionY(ICss3Length backgroundPositionY) {
    }

    @Override
    public void setBackgroundPosition(ICss3Length backgroundPositionX, ICss3Length backgroundPositionY) {
    }

    @Override
    public ICss3Length getMargin() {
        return null;
    }

    @Override
    public void setMargin(ICss3Length margin) {
    }

    @Override
    public ICss3Length getMarginTop() {
        return null;
    }

    @Override
    public void setMarginTop(ICss3Length marginTop) {
    }

    @Override
    public ICss3Length getMarginRight() {
        return null;
    }

    @Override
    public void setMarginRight(ICss3Length marginRight) {
    }

    @Override
    public ICss3Length getMarginBottom() {
        return null;
    }

    @Override
    public void setMarginBottom(ICss3Length marginBottom) {
    }

    @Override
    public ICss3Length getMarginLeft() {
        return null;
    }

    @Override
    public void setMarginLeft(ICss3Length marginLeft) {
    }

    @Override
    public void setMargin(ICss3Length marginTop, ICss3Length marginRight, ICss3Length marginBottom,
            ICss3Length marginLeft) {
    }

    @Override
    public ICss3Length getPadding() {
        return null;
    }

    @Override
    public void setPadding(ICss3Length padding) {
    }

    @Override
    public ICss3Length getPaddingTop() {
        return null;
    }

    @Override
    public void setPaddingTop(ICss3Length paddingTop) {
    }

    @Override
    public ICss3Length getPaddingRight() {
        return null;
    }

    @Override
    public void setPaddingRight(ICss3Length paddingRight) {
    }

    @Override
    public ICss3Length getPaddingBottom() {
        return null;
    }

    @Override
    public void setPaddingBottom(ICss3Length paddingBottom) {
    }

    @Override
    public ICss3Length getPaddingLeft() {
        return null;
    }

    @Override
    public void setPaddingLeft(ICss3Length paddingLeft) {
    }

    @Override
    public void setPadding(ICss3Length paddingTop, ICss3Length paddingRight, ICss3Length paddingBottom,
            ICss3Length paddingLeft) {
    }

    @Override
    public Border getBorder() {
        return null;
    }

    @Override
    public void setBorder(Border border) {
    }

    @Override
    public Border getBorderTop() {
        return null;
    }

    @Override
    public void setBorderTop(Border borderTop) {
    }

    @Override
    public Border getBorderRight() {
        return null;
    }

    @Override
    public void setBorderRight(Border borderRight) {
    }

    @Override
    public Border getBorderBottom() {
        return null;
    }

    @Override
    public void setBorderBottom(Border borderBottom) {
    }

    @Override
    public Border getBorderLeft() {
        return null;
    }

    @Override
    public void setBorderLeft(Border borderLeft) {
    }

    @Override
    public void setBorder(Border borderTop, Border borderRight, Border borderBottom, Border borderLeft) {
    }

    @Override
    public BorderCollapseMode getBorderCollapse() {
        return null;
    }

    @Override
    public void setBorderCollapse(BorderCollapseMode borderCollapse) {
    }

    @Override
    public ICss3Length getBorderHorizontalSpacing() {
        return null;
    }

    @Override
    public ICss3Length getBorderVerticalSpacing() {
        return null;
    }

    @Override
    public void setBorderSpacing(ICss3Length borderHorizontalSpacing, ICss3Length borderVerticalSpacing) {
    }

    @Override
    public ICss3Length getBorderRadius() {
        return null;
    }

    @Override
    public void setBorderRadius(ICss3Length borderRadius) {
    }

    @Override
    public ICss3Length getBorderTopLeftRadius() {
        return null;
    }

    @Override
    public void setBorderTopLeftRadius(ICss3Length borderTopLeftRadius) {
    }

    @Override
    public ICss3Length getBorderTopRightRadius() {
        return null;
    }

    @Override
    public void setBorderTopRightRadius(ICss3Length borderTopRightRadius) {
    }

    @Override
    public ICss3Length getBorderBottomRightRadius() {
        return null;
    }

    @Override
    public void setBorderBottomRightRadius(ICss3Length borderBottomRightRadius) {
    }

    @Override
    public ICss3Length getBorderBottomLeftRadius() {
        return null;
    }

    @Override
    public void setBorderBottomLeftRadius(ICss3Length borderBottomLeftRadius) {
    }

    @Override
    public void setBorderRadius(ICss3Length borderTopLeftRadius, ICss3Length borderTopRightRadius,
            ICss3Length borderBottomRightRadius, ICss3Length borderBottomLeftRadius) {
    }

    @Override
    public Border getOutline() {
        return null;
    }

    @Override
    public boolean isOutlineInvert() {
        return false;
    }

    @Override
    public void setOutline(Border outline, boolean invert) {
    }

    @Override
    public ICss3Length getOutlineOffset() {
        return null;
    }

    @Override
    public void setOutlineOffset(ICss3Length outlineOffset) {
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
    public ICss3Int getZIndex() {
        return null;
    }

    @Override
    public void setZIndex(ICss3Int zIndex) {
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
    public ICss3Length getLetterSpacing() {
        return null;
    }

    @Override
    public void setLetterSpacing(ICss3Length letterSpacing) {
    }

    @Override
    public ICss3Length getLineHeight() {
        return null;
    }

    @Override
    public void setLineHeight(ICss3Length lineHeight) {
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
    public ICss3Length getTextIndent() {
        return null;
    }

    @Override
    public void setTextIndent(ICss3Length textIndent) {
    }

    @Override
    public TextAlignMode getTextAlign() {
        return null;
    }

    @Override
    public void setTextAlign(TextAlignMode textAlign) {
    }

    @Override
    public ICss3Length getVerticalAlign() {
        return null;
    }

    @Override
    public void setVerticalAlign(ICss3Length verticalAlign) {
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
    public ICss3Length getWordSpacing() {
        return null;
    }

    @Override
    public void setWordSpacing(ICss3Length wordSpacing) {
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
    public ImageType getListStyleImageType() {
        return null;
    }

    @Override
    public String getListStyleImage() {
        return null;
    }

    @Override
    public void setListStyleImage(ImageType listStyleImageType, String listStyleImage) {
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

    @Override
    public void accept(ICss3Visitor visitor) {
    }

}
