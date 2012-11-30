package net.bodz.bas.gui.css3;

import java.io.Serializable;
import java.util.List;

import net.bodz.bas.gui.css3.property.*;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.Measure;

/**
 * @see <a href="http://www.w3.org/TR/CSS/#properties">CSS3 Properties</a>
 */
public class Css3StyleAttributes
        implements Serializable {

    private static final long serialVersionUID = 1L;

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

    MinMaxSizeType minWidthType;
    MinMaxSizeType maxWidthType;
    MinMaxSizeType minHeightType;
    MinMaxSizeType maxHeightType;
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

    BorderBox border;
    Float borderSpacingHorizontal;
    Float borderSpacingVertical;
    MeasureBox padding;
    MeasureBox margin;

    Float opacity;
    ClearMode clear;
    DisplayMode display;
    FloatMode float_;
    OverflowMode overflow;
    PositionMode position;
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
    LetterSpacingMode letterSpacing;
    LineHeightType lineHeightType;
    Measure lineHeight;
    TextDecorationMode textDecoration;
    TextTransformMode textTransform;
    TextAlignMode textAlign;
    VerticalAlignType verticalAlignType;
    Measure verticalAlign;
    QuotesMode quotes;
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
    ListStyleMode listStyle;
    TableLayoutMode tableLayout;
    CaptionSideMode captionSide;
    EmptyCellsMode emptyCells;

    ContentType contentType;
    List<ContentItem> contentItems;
    String counterIncrement;
    String counterReset;
    Integer orphans;
    Border outline;
    OutlineColorMode outlineColorType;
    PageBreakMode pageBreakAfter;
    PageBreakMode pageBreakBefore;
    PageBreakMode pageBreakInside;
    Integer widows;

}
