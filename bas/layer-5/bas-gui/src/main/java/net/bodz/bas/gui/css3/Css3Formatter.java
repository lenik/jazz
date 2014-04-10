package net.bodz.bas.gui.css3;

import java.util.List;

import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.pojo.Pair;

public class Css3Formatter
        implements ICss3Visitor {

    IPrintOut out;

    void write(String name, Object value) {

    }

    @Override
    public void width(ICss3Length width) {
        write("width", width);
    }

    @Override
    public void height(ICss3Length height) {
        write("height", height);
    }

    @Override
    public void top(ICss3Length top) {
        write("top", top);
    }

    @Override
    public void right(ICss3Length right) {
        write("right", right);
    }

    @Override
    public void bottom(ICss3Length bottom) {
        write("bottom", bottom);
    }

    @Override
    public void left(ICss3Length left) {
        write("left", left);
    }

    @Override
    public void minWidth(ICss3Length minWidth) {
        write("min-width", minWidth);
    }

    @Override
    public void maxWidth(ICss3Length maxWidth) {
        write("max-width", maxWidth);
    }

    @Override
    public void minHeight(ICss3Length minHeight) {
        write("min-height", minHeight);
    }

    @Override
    public void maxHeight(ICss3Length maxHeight) {
        write("max-height", maxHeight);
    }

    @Override
    public void color(IColor color) {
        write("color", color);
    }

    @Override
    public void backgroundColor(IColor backgroundColor) {
        write("background-color", backgroundColor);
    }

    @Override
    public void backgroundImage(ImageType backgroundImageType, String backgroundImage) {
        write("background-image", backgroundImageType.format(backgroundImage));
    }

    @Override
    public void backgroundRepeat(BackgroundRepeatMode backgroundRepeat) {
        write("background-repeat", backgroundRepeat);
    }

    @Override
    public void backgroundAttachment(BackgroundAttachmentMode backgroundAttachment) {
        write("background-attachment", backgroundAttachment);
    }

    @Override
    public void backgroundPosition(ICss3Length backgroundPositionX, ICss3Length backgroundPositionY) {
        String str = null;
        if (backgroundPositionX != null || backgroundPositionY != null) {
            str = "";
            if (backgroundPositionX != null)
                str = backgroundPositionX.toString();
            str += ";";
            if (backgroundPositionY != null)
                str += backgroundPositionY.toString();
        }
        write("background-position", str);
    }

    @Override
    public void margin(ICss3Length margin) {
        write("margin", margin);
    }

    @Override
    public void marginTop(ICss3Length marginTop) {
        write("margin-top", marginTop);
    }

    @Override
    public void marginRight(ICss3Length marginRight) {
        write("margin-right", marginRight);
    }

    @Override
    public void marginBottom(ICss3Length marginBottom) {
        write("margin-bottom", marginBottom);
    }

    @Override
    public void marginLeft(ICss3Length marginLeft) {
        write("margin-left", marginLeft);
    }

    @Override
    public void padding(ICss3Length padding) {
        write("padding", padding);
    }

    @Override
    public void paddingTop(ICss3Length paddingTop) {
        write("padding-top", paddingTop);
    }

    @Override
    public void paddingRight(ICss3Length paddingRight) {
        write("padding-right", paddingRight);
    }

    @Override
    public void paddingBottom(ICss3Length paddingBottom) {
        write("padding-bottom", paddingBottom);
    }

    @Override
    public void paddingLeft(ICss3Length paddingLeft) {
        write("padding-left", paddingLeft);
    }

    @Override
    public void border(Border border) {
        write("border", border);
    }

    @Override
    public void borderTop(Border borderTop) {
        write("border-top", borderTop);
    }

    @Override
    public void borderRight(Border borderRight) {
        write("border-right", borderRight);
    }

    @Override
    public void borderBottom(Border borderBottom) {
        write("border-bottom", borderBottom);
    }

    @Override
    public void borderLeft(Border borderLeft) {
        write("border-left", borderLeft);
    }

    @Override
    public void borderRadius(ICss3Length borderRadius) {
        write("border-radius", borderRadius);
    }

    @Override
    public void borderTopLeftRadius(ICss3Length borderTopLeftRadius) {
        write("border-top-left-radius", borderTopLeftRadius);
    }

    @Override
    public void borderTopRightRadius(ICss3Length borderTopRightRadius) {
        write("border-top-right-radius", borderTopRightRadius);
    }

    @Override
    public void borderBottomRightRadius(ICss3Length borderBottomRightRadius) {
        write("border-bottom-right-radius", borderBottomRightRadius);
    }

    @Override
    public void borderBottomLeftRadius(ICss3Length borderBottomLeftRadius) {
        write("border-bottom-left-radius", borderBottomLeftRadius);
    }

    @Override
    public void borderCollapse(BorderCollapseMode borderCollapse) {
        write("border-collapse", borderCollapse);
    }

    @Override
    public void borderSpacing(ICss3Length borderHorizontalSpacing, ICss3Length borderVerticalSpacing) {
        // write("-webkit-border-horizontal-spacing", borderHorizontalSpacing);
        // write("-webkit-border-vertical-spacing", borderVerticalSpacing);
        write("border-spacing", borderHorizontalSpacing + " " + borderVerticalSpacing);
    }

    @Override
    public void outline(Border outline, boolean invert) {
    }

    @Override
    public void outlineOffset(ICss3Length outlineOffset) {
        write("outline-offset", outlineOffset);
    }

    @Override
    public void opacity(Float opacity) {
        // write("opacity", opacity + "%");
        write("opacity", opacity + "%");
    }

    @Override
    public void clear(ClearMode clear) {
        write("clear", clear);
    }

    @Override
    public void display(DisplayMode display) {
        write("display", display);
    }

    @Override
    public void position(PositionMode position) {
        write("position", position);
    }

    @Override
    public void float_(FloatMode float_) {
        write("float", float_);
    }

    @Override
    public void overflow(OverflowMode overflow) {
        write("overflow", overflow);
    }

    @Override
    public void visibility(VisibilityMode visibility) {
        write("visibility", visibility);
    }

    @Override
    public void zIndex(ICss3Int zIndex) {
        write("z-index", zIndex);
    }

    @Override
    public void fontFamily(String fontFamily) {
        write("font-family", fontFamily);
    }

    @Override
    public void fontSize(ICss3Length fontSize) {
        write("font-size", fontSize);
    }

    @Override
    public void fontStyle(FontStyleMode fontStyle) {
        write("font-style", fontStyle);
    }

    @Override
    public void fontVariant(FontVariantMode fontVariant) {
        write("font-variant", fontVariant);
    }

    @Override
    public void fontWeight(FontWeightMode fontWeight) {
        write("font-weight", fontWeight);
    }

    @Override
    public void direction(DirectionMode direction) {
        write("direction", direction);
    }

    @Override
    public void letterSpacing(ICss3Length letterSpacing) {
        write("letter-spacing", letterSpacing);
    }

    @Override
    public void lineHeight(ICss3Length lineHeight) {
        write("line-height", lineHeight);
    }

    @Override
    public void textDecoration(TextDecorationFlags textDecoration) {
        write("text-decoration", textDecoration);
    }

    @Override
    public void textTransform(TextTransformMode textTransform) {
        write("text-transform", textTransform);
    }

    @Override
    public void textIndent(ICss3Length textIndent) {
        write("text-indent", textIndent);
    }

    @Override
    public void textAlign(TextAlignMode textAlign) {
        write("text-align", textAlign);
    }

    @Override
    public void verticalAlign(ICss3Length verticalAlign) {
        write("vertical-align", verticalAlign);
    }

    @Override
    public void quotes(QuotesType quotesType, List<Pair<String, String>> quotes) {
    }

    @Override
    public void unicodeBidi(UnicodeBidiMode unicodeBidi) {
        write("unicode-bidi", unicodeBidi);
    }

    @Override
    public void whiteSpace(WhiteSpaceMode whiteSpace) {
        write("white-space", whiteSpace);
    }

    @Override
    public void wordSpacing(ICss3Length wordSpacing) {
        write("word-spacing", wordSpacing);
    }

    @Override
    public void clip(ClipType clipType, String clip) {
    }

    @Override
    public void cursor(CursorType cursorType, String cursor) {
    }

    @Override
    public void listStyleType(ListStyleTypeMode listStyleType) {
        write("list-style-type", listStyleType);
    }

    @Override
    public void listStyleImage(String listStyleImage) {
        // write("list-style")
    }

    @Override
    public void listStylePosition(ListStylePositionMode listStylePosition) {
        write("list-style-position", listStylePosition);
    }

    @Override
    public void tableLayout(TableLayoutMode tableLayout) {
        write("table-layout", tableLayout);
    }

    @Override
    public void captionSide(CaptionSideMode captionSide) {
        write("caption-side", captionSide);
    }

    @Override
    public void emptyCells(EmptyCellsMode emptyCells) {
        write("empty-cells", emptyCells);
    }

    @Override
    public void content(ContentType contentType, List<ContentItem> contentItems) {
        // TODO conent: ...?
        write("content", contentType);
    }

    @Override
    public void counterIncrement(String counterIncrement) {
        write("counter-increment", counterIncrement);
    }

    @Override
    public void counterReset(String counterReset) {
        write("counter-reset", counterReset);
    }

    @Override
    public void pageBreakAfter(PageBreakMode pageBreakAfter) {
        write("page-break-after", pageBreakAfter);
    }

    @Override
    public void pageBreakBefore(PageBreakMode pageBreakBefore) {
        write("page-break-before", pageBreakBefore);
    }

    @Override
    public void pageBreakInside(PageBreakMode pageBreakInside) {
        write("page-break-inside", pageBreakInside);
    }

    @Override
    public void orphans(Integer orphans) {
        write("orphans", orphans);
    }

    @Override
    public void widows(Integer widows) {
        write("widows", widows);
    }

}
