package net.bodz.bas.ui.css3;

import java.util.List;

import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.ui.css3.property.*;
import net.bodz.bas.ui.style.IColor;

public interface ICss3Visitor {

    void width(ICss3Length width);

    void height(ICss3Length height);

    void top(ICss3Length top);

    void right(ICss3Length right);

    void bottom(ICss3Length bottom);

    void left(ICss3Length left);

    void minWidth(ICss3Length minWidth);

    void maxWidth(ICss3Length maxWidth);

    void minHeight(ICss3Length minHeight);

    void maxHeight(ICss3Length maxHeight);

    void color(IColor color);

    void backgroundColor(IColor backgroundColor);

    void backgroundImage(ImageType backgroundImageType, String backgroundImage);

    void backgroundRepeat(BackgroundRepeatMode backgroundRepeat);

    void backgroundAttachment(BackgroundAttachmentMode backgroundAttachment);

    void backgroundPosition(ICss3Length backgroundPositionX, ICss3Length backgroundPositionY);

    void margin(ICss3Length margin);

    void marginTop(ICss3Length marginTop);

    void marginRight(ICss3Length marginRight);

    void marginBottom(ICss3Length marginBottom);

    void marginLeft(ICss3Length marginLeft);

    void padding(ICss3Length padding);

    void paddingTop(ICss3Length paddingTop);

    void paddingRight(ICss3Length paddingRight);

    void paddingBottom(ICss3Length paddingBottom);

    void paddingLeft(ICss3Length paddingLeft);

    void border(Border border);

    void borderTop(Border borderTop);

    void borderRight(Border borderRight);

    void borderBottom(Border borderBottom);

    void borderLeft(Border borderLeft);

    void borderRadius(ICss3Length borderRadius);

    void borderTopLeftRadius(ICss3Length borderTopLeftRadius);

    void borderTopRightRadius(ICss3Length borderTopRightRadius);

    void borderBottomRightRadius(ICss3Length borderBottomRightRadius);

    void borderBottomLeftRadius(ICss3Length borderBottomLeftRadius);

    void borderCollapse(BorderCollapseMode borderCollapse);

    void borderSpacing(ICss3Length borderHorizontalSpacing, ICss3Length borderVerticalSpacing);

    void outline(Border outline, boolean invert);

    void outlineOffset(ICss3Length outlineOffset);

    void opacity(Float opacity);

    void clear(ClearMode clear);

    void display(DisplayMode display);

    void position(PositionMode position);

    void float_(FloatMode float_);

    void overflow(OverflowMode overflow);

    void visibility(VisibilityMode visibility);

    void zIndex(ICss3Int zIndex);

    void fontFamily(String fontFamily);

    void fontSize(ICss3Length fontSize);

    void fontStyle(FontStyleMode fontStyle);

    void fontVariant(FontVariantMode fontVariant);

    void fontWeight(FontWeightMode fontWeight);

    void direction(DirectionMode direction);

    void letterSpacing(ICss3Length letterSpacing);

    void lineHeight(ICss3Length lineHeight);

    void textDecoration(TextDecorationFlags textDecoration);

    void textTransform(TextTransformMode textTransform);

    void textIndent(ICss3Length textIndent);

    void textAlign(TextAlignMode textAlign);

    void verticalAlign(ICss3Length verticalAlign);

    void quotes(QuotesType quotesType, List<Pair<String, String>> quotes);

    void unicodeBidi(UnicodeBidiMode unicodeBidi);

    void whiteSpace(WhiteSpaceMode whiteSpace);

    void wordSpacing(ICss3Length wordSpacing);

    void clip(ClipType clipType, String clip);

    void cursor(CursorType cursorType, String cursor);

    void listStyleType(ListStyleTypeMode listStyleType);

    void listStyleImage(String listStyleImage);

    void listStylePosition(ListStylePositionMode listStylePosition);

    void tableLayout(TableLayoutMode tableLayout);

    void captionSide(CaptionSideMode captionSide);

    void emptyCells(EmptyCellsMode emptyCells);

    void content(ContentType contentType, List<ContentItem> contentItems);

    void counterIncrement(String counterIncrement);

    void counterReset(String counterReset);

    void pageBreakAfter(PageBreakMode pageBreakAfter);

    void pageBreakBefore(PageBreakMode pageBreakBefore);

    void pageBreakInside(PageBreakMode pageBreakInside);

    void orphans(Integer orphans);

    void widows(Integer widows);

}
