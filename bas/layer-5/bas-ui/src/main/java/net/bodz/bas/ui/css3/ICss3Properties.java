package net.bodz.bas.ui.css3;

import java.util.List;

import net.bodz.bas.t.map.IParsedMap;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.ui.css3.property.*;
import net.bodz.bas.ui.style.IColor;

/**
 * CSS Declaration Properties.
 * 
 * @see <a href="http://www.w3.org/TR/CSS/#properties">CSS3 Properties</a>
 */
public interface ICss3Properties
        extends IParsedMap<String, String> {

    /**
     * This property specifies the content width of boxes.
     * 
     * This property does not apply to non-replaced inline elements. The content width of a
     * non-replaced inline element's boxes is that of the rendered content within them (before any
     * relative offset of children). Recall that inline boxes flow into line boxes. The width of
     * line boxes is given by the their containing block, but may be shorted by the presence of
     * floats.
     */
    ICss3Length getWidth();

    ICss3Length getHeight();

    void setWidth(ICss3Length width);

    void setHeight(ICss3Length height);

    /**
     * This property specifies how far an absolutely positioned box's top margin edge is offset
     * below the top edge of the box's containing block. For relatively positioned boxes, the offset
     * is with respect to the top edges of the box itself (i.e., the box is given a position in the
     * normal flow, then offset from that position according to these properties).
     */
    ICss3Length getTop();

    /**
     * Like 'top', but specifies how far a box's right margin edge is offset to the left of the
     * right edge of the box's containing block. For relatively positioned boxes, the offset is with
     * respect to the right edge of the box itself.
     */
    ICss3Length getRight();

    /**
     * Like 'top', but specifies how far a box's bottom margin edge is offset above the bottom of
     * the box's containing block. For relatively positioned boxes, the offset is with respect to
     * the bottom edge of the box itself.
     */
    ICss3Length getBottom();

    /**
     * Like 'top', but specifies how far a box's left margin edge is offset to the right of the left
     * edge of the box's containing block. For relatively positioned boxes, the offset is with
     * respect to the left edge of the box itself.
     */
    ICss3Length getLeft();

    void setTop(ICss3Length top);

    void setRight(ICss3Length right);

    void setBottom(ICss3Length bottom);

    void setLeft(ICss3Length left);

    ICss3Length getMinWidth();

    ICss3Length getMaxWidth();

    ICss3Length getMinHeight();

    ICss3Length getMaxHeight();

    void setMinWidth(ICss3Length minWidth);

    void setMaxWidth(ICss3Length maxWidth);

    void setMinHeight(ICss3Length minHeight);

    void setMaxHeight(ICss3Length maxHeight);

    /**
     * This property describes the foreground color of an element's text content. In addition it is
     * used to provide a potential indirect value (currentColor) for any other properties that
     * accept color values. If the ‘currentColor’ keyword is set on the ‘color’ property itself, it
     * is treated as ‘color: inherit’.
     */
    @_InheritedIfNull
    IColor getColor();

    void setColor(IColor color);

    /*
     * The 'background' property is a shorthand property for setting the individual background
     * properties (i.e., 'background-color', 'background-image', 'background-repeat',
     * 'background-attachment' and 'background-position') at the same place in the style sheet.
     * 
     * Given a valid declaration, the 'background' property first sets all the individual background
     * properties to their initial values, then assigns explicit values given in the declaration.
     */
    /**
     * This property sets the background color of an element, either a <color> value or the keyword
     * 'transparent', to make the underlying colors shine through.
     */
    IColor getBackgroundColor();

    void setBackgroundColor(IColor backgroundColor);

    /**
     * This property sets the background image of an element. When setting a background image,
     * authors should also specify a background color that will be used when the image is
     * unavailable. When the image is available, it is rendered on top of the background color.
     * (Thus, the color is visible in the transparent parts of the image).
     */
    ImageType getBackgroundImageType();

    String getBackgroundImage();

    void setBackgroundImage(ImageType backgroundImageType, String backgroundImage);

    /**
     * If a background image is specified, this property specifies whether the image is repeated
     * (tiled), and how. All tiling covers the content, padding and border areas of a box.
     * 
     * The tiling and positioning of the background-image on inline elements is undefined in this
     * specification. A future level of CSS may define the tiling and positioning of the
     * background-image on inline elements.
     */
    BackgroundRepeatMode getBackgroundRepeat();

    void setBackgroundRepeat(BackgroundRepeatMode backgroundRepeat);

    /**
     * If a background image is specified, this property specifies whether it is fixed with regard
     * to the viewport ('fixed') or scrolls along with the containing block ('scroll').
     * 
     * Note that there is only one viewport per view. If an element has a scrolling mechanism (see
     * 'overflow'), a 'fixed' background does not move with the element, and a 'scroll' background
     * does not move with the scrolling mechanism.
     * 
     * Even if the image is fixed, it is still only visible when it is in the content, padding or
     * border area of the element. Thus, unless the image is tiled ('background-repeat: repeat'), it
     * may be invisible.
     * 
     * In paged media, where there is no viewport, a 'fixed' background is fixed with respect to the
     * page box and is therefore replicated on every page.
     */
    BackgroundAttachmentMode getBackgroundAttachment();

    void setBackgroundAttachment(BackgroundAttachmentMode backgroundAttachment);

    /**
     * If a background image has been specified, this property specifies its initial position. If
     * only one value is specified, the second value is assumed to be 'center'. If at least one
     * value is not a keyword, then the first value represents the horizontal position and the
     * second represents the vertical position. Negative <percentage> and <length> values are
     * allowed.
     * 
     * <ul>
     * <li>
     * <i>length</i>: A length L aligns the top left corner of the image a distance L to the right
     * of (for horizontal) or below (for vertical) the top left corner of the element's padding box.
     * For example, with a value pair of '2cm 1cm', the upper left corner of the image is placed 2cm
     * to the right and 1cm below the upper left corner of the padding box.
     * <li><i>percentage</i>: A percentage X aligns the point X% across (for horizontal) or down
     * (for vertical) the image with the point X% across (for horizontal) or down (for vertical) the
     * element's padding box. For example, with a value pair of '0% 0%',the upper left corner of the
     * image is aligned with the upper left corner of the padding box. A value pair of '100% 100%'
     * places the lower right corner of the image in the lower right corner of the padding box. With
     * a value pair of '14% 84%', the point 14% across and 84% down the image is to be placed at the
     * point 14% across and 84% down the padding box.
     * <li><code>top</code>: Equivalent to '0%' for the vertical position.
     * <li><code>right</code>: Equivalent to '100%' for the horizontal position.
     * <li><code>bottom</code>: Equivalent to '100%' for the vertical position.
     * <li><code>left</code>: Equivalent to '0%' for the horizontal position.
     * <li><code>center</code>: Equivalent to '50%' for the horizontal position if it is not
     * otherwise given, or '50%' for the vertical position if it is.
     * </ul>
     */
    ICss3Length getBackgroundPositionX();

    /**
     * If a background image has been specified, this property specifies its initial position. If
     * only one value is specified, the second value is assumed to be 'center'. If at least one
     * value is not a keyword, then the first value represents the horizontal position and the
     * second represents the vertical position. Negative <percentage> and <length> values are
     * allowed.
     */
    ICss3Length getBackgroundPositionY();

    void setBackgroundPositionX(ICss3Length backgroundPositionX);

    void setBackgroundPositionY(ICss3Length backgroundPositionY);

    void setBackgroundPosition(ICss3Length backgroundPositionX, ICss3Length backgroundPositionY);

    /**
     * The 'margin' property is a shorthand property for setting 'margin-top', 'margin-right',
     * 'margin-bottom', and 'margin-left' at the same place in the style sheet.
     * 
     * If there is only one component value, it applies to all sides. If there are two values, the
     * top and bottom margins are set to the first value and the right and left margins are set to
     * the second. If there are three values, the top is set to the first value, the left and right
     * are set to the second, and the bottom is set to the third. If there are four values, they
     * apply to the top, right, bottom, and left, respectively.
     */
    ICss3Length getMargin();

    void setMargin(ICss3Length margin);

    ICss3Length getMarginTop();

    void setMarginTop(ICss3Length marginTop);

    ICss3Length getMarginRight();

    void setMarginRight(ICss3Length marginRight);

    ICss3Length getMarginBottom();

    void setMarginBottom(ICss3Length marginBottom);

    ICss3Length getMarginLeft();

    void setMarginLeft(ICss3Length marginLeft);

    void setMargin(ICss3Length marginTop, ICss3Length marginRight, ICss3Length marginBottom, ICss3Length marginLeft);

    /**
     * The 'padding' property is a shorthand property for setting 'padding-top', 'padding-right',
     * 'padding-bottom', and 'padding-left' at the same place in the style sheet.
     * 
     * If there is only one component value, it applies to all sides. If there are two values, the
     * top and bottom paddings are set to the first value and the right and left paddings are set to
     * the second. If there are three values, the top is set to the first value, the left and right
     * are set to the second, and the bottom is set to the third. If there are four values, they
     * apply to the top, right, bottom, and left, respectively.
     * 
     * The surface color or image of the padding area is specified via the 'background' property:
     */
    ICss3Length getPadding();

    void setPadding(ICss3Length padding);

    ICss3Length getPaddingTop();

    void setPaddingTop(ICss3Length paddingTop);

    ICss3Length getPaddingRight();

    void setPaddingRight(ICss3Length paddingRight);

    ICss3Length getPaddingBottom();

    void setPaddingBottom(ICss3Length paddingBottom);

    ICss3Length getPaddingLeft();

    void setPaddingLeft(ICss3Length paddingLeft);

    void setPadding(ICss3Length paddingTop, ICss3Length paddingRight, ICss3Length paddingBottom, ICss3Length paddingLeft);

    Border getBorder();

    void setBorder(Border border);

    Border getBorderTop();

    void setBorderTop(Border borderTop);

    Border getBorderRight();

    void setBorderRight(Border borderRight);

    Border getBorderBottom();

    void setBorderBottom(Border borderBottom);

    Border getBorderLeft();

    void setBorderLeft(Border borderLeft);

    void setBorder(Border borderTop, Border borderRight, Border borderBottom, Border borderLeft);

    ICss3Length getBorderRadius();

    void setBorderRadius(ICss3Length borderRadius);

    ICss3Length getBorderTopLeftRadius();

    void setBorderTopLeftRadius(ICss3Length borderTopLeftRadius);

    ICss3Length getBorderTopRightRadius();

    void setBorderTopRightRadius(ICss3Length borderTopRightRadius);

    ICss3Length getBorderBottomRightRadius();

    void setBorderBottomRightRadius(ICss3Length borderBottomRightRadius);

    ICss3Length getBorderBottomLeftRadius();

    void setBorderBottomLeftRadius(ICss3Length borderBottomLeftRadius);

    /**
     * The four values for each radii are given in the order top-left, top-right, bottom-right,
     * bottom-left. If bottom-left is omitted it is the same as top-right. If bottom-right is
     * omitted it is the same as top-left. If top-right is omitted it is the same as top-left.
     */
    void setBorderRadius(ICss3Length borderTopLeftRadius, ICss3Length borderTopRightRadius,
            ICss3Length borderBottomRightRadius, ICss3Length borderBottomLeftRadius);

    /**
     * This property selects a table's border model. The value 'separate' selects the separated
     * borders border model. The value 'collapse' selects the collapsing borders model. The models
     * are described below.
     */
    @_InheritedIfNull
    BorderCollapseMode getBorderCollapse();

    void setBorderCollapse(BorderCollapseMode borderCollapse);

    /**
     * The lengths specify the distance that separates adjoining cell borders. If one length is
     * specified, it gives both the horizontal and vertical spacing. If two are specified, the first
     * gives the horizontal spacing and the second the vertical spacing. Lengths may not be
     * negative.
     * 
     * The distance between the table border and the borders of the cells on the edge of the table
     * is the table's padding for that side, plus the relevant border spacing distance. For example,
     * on the right hand side, the distance is padding-right + horizontal border-spacing.
     * 
     * The width of the table is the distance from the left inner padding edge to the right inner
     * padding edge (including the border spacing but excluding padding and border).
     */
    @_InheritedIfNull
    ICss3Length getBorderHorizontalSpacing();

    @_InheritedIfNull
    ICss3Length getBorderVerticalSpacing();

    void setBorderSpacing(ICss3Length borderHorizontalSpacing, ICss3Length borderVerticalSpacing);

    Border getOutline();

    boolean isOutlineInvert();

    void setOutline(Border outline, boolean invert);

    ICss3Length getOutlineOffset();

    void setOutlineOffset(ICss3Length outlineOffset);

    /**
     * Opacity can be thought of as a postprocessing operation. Conceptually, after the element
     * (including its descendants) is rendered into an RGBA offscreen image, the opacity setting
     * specifies how to blend the offscreen rendering into the current composite rendering. See
     * simple alpha compositing for details.
     * 
     * The opacity is a number. The uniform opacity setting to be applied across an entire object.
     * Any values outside the range 0.0 (fully transparent) to 1.0 (fully opaque) will be clamped to
     * this range. If the object is a container element, then the effect is as if the contents of
     * the container element were blended against the current background using a mask where the
     * value of each pixel of the mask is alphavalue.
     */
    Float getOpacity();

    void setOpacity(Float opacity);

    ClearMode getClear();

    void setClear(ClearMode clear);

    DisplayMode getDisplay();

    void setDisplay(DisplayMode display);

    /**
     * The 'position' and 'float' properties determine which of the CSS 2.1 positioning algorithms
     * is used to calculate the position of a box.
     */
    PositionMode getPosition();

    void setPosition(PositionMode position);

    /**
     * This property specifies whether a box should float to the left, right, or not at all. It may
     * be set for any element, but only applies to elements that generate boxes that are not
     * absolutely positioned.
     */
    FloatMode getFloat();

    void setFloat(FloatMode float_);

    /**
     * This property specifies whether content of a block container element is clipped when it
     * overflows the element's box. It affects the clipping of all of the element's content except
     * any descendant elements (and their respective content and descendants) whose containing block
     * is the viewport or an ancestor of the element.
     */
    OverflowMode getOverflow();

    void setOverflow(OverflowMode overflow);

    /**
     * The 'visibility' property specifies whether the boxes generated by an element are rendered.
     * Invisible boxes still affect layout (set the 'display' property to 'none' to suppress box
     * generation altogether).
     */
    @_InheritedIfNull
    VisibilityMode getVisibility();

    void setVisibility(VisibilityMode visibility);

    /**
     * For a positioned box, the 'z-index' property specifies:
     * <ol>
     * <li>The stack level of the box in the current stacking context.
     * <li>Whether the box establishes a stacking context.
     * </ol>
     * 
     * In CSS 2.1, each box has a position in three dimensions. In addition to their horizontal and
     * vertical positions, boxes lie along a "z-axis" and are formatted one on top of the other.
     * Z-axis positions are particularly relevant when boxes overlap visually.
     * 
     * The order in which the rendering tree is painted onto the canvas is described in terms of
     * stacking contexts. Stacking contexts can contain further stacking contexts. A stacking
     * context is atomic from the point of view of its parent stacking context; boxes in other
     * stacking contexts may not come between any of its boxes.
     * 
     * Each box belongs to one stacking context. Each positioned box in a given stacking context has
     * an integer stack level, which is its position on the z-axis relative other stack levels
     * within the same stacking context. Boxes with greater stack levels are always formatted in
     * front of boxes with lower stack levels. Boxes may have negative stack levels. Boxes with the
     * same stack level in a stacking context are stacked back-to-front according to document tree
     * order.
     * 
     * The root element forms the root stacking context. Other stacking contexts are generated by
     * any positioned element (including relatively positioned elements) having a computed value of
     * 'z-index' other than 'auto'. Stacking contexts are not necessarily related to containing
     * blocks. In future levels of CSS, other properties may introduce stacking contexts, for
     * example 'opacity' [CSS3COLOR].
     * 
     * <ul>
     * <li><i>number</i>: This integer is the stack level of the generated box in the current
     * stacking context. The box also establishes a new stacking context.
     * <li><code>auto</code>: The stack level of the generated box in the current stacking context
     * is 0. The box does not establish a new stacking context unless it is the root element.
     * </ul>
     * 
     * @return The larger integer means at the front.
     * @see ICss3Keywords#AUTO
     * @see ICss3Keywords#NUMBER
     */
    ICss3Int getZIndex();

    void setZIndex(ICss3Int zIndex);

    /**
     * The property value is a prioritized list of font family names and/or generic family names.
     * Unlike most other CSS properties, component values are separated by a comma to indicate that
     * they are alternatives:
     * 
     * body { font-family: Gill, Helvetica, sans-serif } Although many fonts provide the
     * "missing character" glyph, typically an open box, as its name implies this should not be
     * considered a match for characters that cannot be found in the font. (It should, however, be
     * considered a match for U+FFFD, the "missing character" character's code point).
     */
    @_InheritedIfNull
    String getFontFamily();

    void setFontFamily(String fontFamily);

    /**
     * @see ICss3Length#MEDIUM
     * @see ICss3Length#LARGE
     * @see ICss3Length#SMALL
     * @see ICss3Length#XX_LARGE
     * @see ICss3Length#X_LARGE
     * @see ICss3Length#LARGER
     * @see ICss3Length#SMALLER
     * @see ICss3Length#X_SMALL
     * @see ICss3Length#XX_SMALL
     */
    ICss3Length getFontSize();

    void setFontSize(ICss3Length fontSize);

    /**
     * @see FontStyleMode#normal
     * @see FontStyleMode#italic
     * @see FontStyleMode#oblique
     */
    @_InheritedIfNull
    FontStyleMode getFontStyle();

    void setFontStyle(FontStyleMode fontStyle);

    /**
     * @see FontVariantMode#normal
     * @see FontVariantMode#small_caps
     */
    @_InheritedIfNull
    FontVariantMode getFontVariant();

    void setFontVariant(FontVariantMode fontVariant);

    @_InheritedIfNull
    FontWeightMode getFontWeight();

    void setFontWeight(FontWeightMode fontWeight);

    /**
     * This property specifies the base writing direction of blocks and the direction of embeddings
     * and overrides (see 'unicode-bidi') for the Unicode bidirectional algorithm. In addition, it
     * specifies such things as the direction of table column layout, the direction of horizontal
     * overflow, the position of an incomplete last line in a block in case of 'text-align:
     * justify'.
     * 
     * @see DirectionMode#ltr
     * @see DirectionMode#rtl
     */
    @_InheritedIfNull
    DirectionMode getDirection();

    void setDirection(DirectionMode direction);

    /**
     * This property specifies spacing behavior between text characters.
     * 
     * <ul>
     * <li><code>normal</code>: The spacing is the normal spacing for the current font. This value
     * allows the user agent to alter the space between characters in order to justify text.
     * <li><i>length</i>: This value indicates inter-character space in addition to the default
     * space between characters. Values may be negative, but there may be implementation-specific
     * limits. User agents may not further increase or decrease the inter-character space in order
     * to justify text.
     * </ul>
     * 
     * @see ICss3Length#NORMAL
     * @see ICss3Length#LENGTH
     */
    @_InheritedIfNull
    ICss3Length getLetterSpacing();

    void setLetterSpacing(ICss3Length letterSpacing);

    /**
     * <ul>
     * <li><code>normal</code>: Tells user agents to set the used value to a "reasonable" value
     * based on the font of the element. The value has the same meaning as <number>. We recommend a
     * used value for 'normal' between 1.0 to 1.2. The computed value is 'normal'.
     * 
     * <li><i>length</i>: The specified length is used in the calculation of the line box height.
     * Negative values are illegal.
     * 
     * <li><i>number</i>: The used value of the property is this number multiplied by the element's
     * font size. Negative values are illegal. The computed value is the same as the specified
     * value.
     * 
     * <li><i>percentage</i>: The computed value of the property is this percentage multiplied by
     * the element's computed font size. Negative values are illegal.
     * </ul>
     * 
     * @see ICss3Length#NORMAL
     * @see ICss3Length#LENGTH
     * @see ICss3Length#NUMBER
     * @see ICss3Length#PERCENTAGE
     */
    @_InheritedIfNull
    ICss3Length getLineHeight();

    void setLineHeight(ICss3Length lineHeight);

    /**
     * This property describes decorations that are added to the text of an element using the
     * element's color. When specified on or propagated to an inline element, it affects all the
     * boxes generated by that element, and is further propagated to any in-flow block-level boxes
     * that split the inline (see section 9.2.1.1). But, in CSS 2.1, it is undefined whether the
     * decoration propagates into block-level tables. For block containers that establish an inline
     * formatting context, the decorations are propagated to an anonymous inline element that wraps
     * all the in-flow inline-level children of the block container. For all other elements it is
     * propagated to any in-flow children. Note that text decorations are not propagated to floating
     * and absolutely positioned descendants, nor to the contents of atomic inline-level descendants
     * such as inline blocks and inline tables.
     * 
     * Underlines, overlines, and line-throughs are applied only to text (including white space,
     * letter spacing, and word spacing): margins, borders, and padding are skipped. User agents
     * must not render these text decorations on content that is not text. For example, images and
     * inline blocks must not be underlined.
     */
    @_InheritedIfNull
    TextDecorationFlags getTextDecoration();

    void setTextDecoration(TextDecorationFlags textDecoration);

    /**
     * This property controls capitalization effects of an element's text.
     * 
     * Values have the following meanings:
     * 
     * @see TextTransformMode#none
     * @see TextTransformMode#uppercase
     * @see TextTransformMode#lowercase
     * @see TextTransformMode#capitalize
     */
    @_InheritedIfNull
    TextTransformMode getTextTransform();

    void setTextTransform(TextTransformMode textTransform);

    /**
     * This property specifies the indentation of the first line of text in a block container. More
     * precisely, it specifies the indentation of the first box that flows into the block's first
     * line box. The box is indented with respect to the left (or right, for right-to-left layout)
     * edge of the line box. User agents must render this indentation as blank space.
     * 
     * 'Text-indent' only affects a line if it is the first formatted line of an element. For
     * example, the first line of an anonymous block box is only affected if it is the first child
     * of its parent element.
     * 
     * The value of 'text-indent' may be negative, but there may be implementation-specific limits.
     * If the value of 'text-indent' is either negative or exceeds the width of the block, that
     * first box, described above, can overflow the block. The value of 'overflow' will affect
     * whether such text that overflows the block is visible.
     * 
     * @see ICss3Length#LENGTH
     * @see ICss3Length#PERCENTAGE
     */
    @_InheritedIfNull
    ICss3Length getTextIndent();

    void setTextIndent(ICss3Length textIndent);

    /**
     * This property describes how inline-level content of a block container is aligned.
     * 
     * Values have the following meanings:
     * 
     * @see TextAlignMode#left
     * @see TextAlignMode#right
     * @see TextAlignMode#justify
     */
    @_InheritedIfNull
    TextAlignMode getTextAlign();

    void setTextAlign(TextAlignMode textAlign);

    /**
     * This property affects the vertical positioning inside a line box of the boxes generated by an
     * inline-level element.
     * <p>
     * Note. Values of this property have different meanings in the context of tables. Please
     * consult the section on table height algorithms for details.
     * <p>
     * The following values only have meaning with respect to a parent inline element, or to the
     * strut of a parent block container element.
     * <p>
     * In the following definitions, for inline non-replaced elements, the box used for alignment is
     * the box whose height is the 'line-height' (containing the box's glyphs and the half-leading
     * on each side, see above). For all other elements, the box used for alignment is the margin
     * box.
     * <ul>
     * <li><i>length</i>: Raise (positive value) or lower (negative value) the box by this distance.
     * The value '0cm' means the same as 'baseline'.
     * <li><code>percentage</code>: Raise (positive value) or lower (negative value) the box by this
     * distance (a percentage of the 'line-height' value). The value '0%' means the same as
     * 'baseline'.
     * <li><code>baseline</code>: Align the baseline of the box with the baseline of the parent box.
     * If the box does not have a baseline, align the bottom margin edge with the parent's baseline.
     * <li><code>middle</code>: Align the vertical midpoint of the box with the baseline of the
     * parent box plus half the x-height of the parent.
     * <li><code>sub</code>: Lower the baseline of the box to the proper position for subscripts of
     * the parent's box. (This value has no effect on the font size of the element's text.)
     * <li><code>super</code>: Raise the baseline of the box to the proper position for superscripts
     * of the parent's box. (This value has no effect on the font size of the element's text.)
     * <li><code>text-top</code>: Align the top of the box with the top of the parent's content area
     * (see 10.6.1).
     * <li><code>text-bottom</code>: Align the bottom of the box with the bottom of the parent's
     * content area (see 10.6.1).
     * </ul>
     * 
     * The following values align the element relative to the line box. Since the element may have
     * children aligned relative to it (which in turn may have descendants aligned relative to
     * them), these values use the bounds of the aligned subtree. The aligned subtree of an inline
     * element contains that element and the aligned subtrees of all children inline elements whose
     * computed 'vertical-align' value is not 'top' or 'bottom'. The top of the aligned subtree is
     * the highest of the tops of the boxes in the subtree, and the bottom is analogous.
     * <ul>
     * <li><code>top</code>: Align the top of the aligned subtree with the top of the line box.
     * <li><code>bottom</code>: Align the bottom of the aligned subtree with the bottom of the line
     * box.
     * </ul>
     * 
     * @see ICss3Length#BASELINE
     * @see ICss3Length#MIDDLE
     * @see ICss3Length#SUB
     * @see ICss3Length#SUPER
     * @see ICss3Length#TEXT_TOP
     * @see ICss3Length#TEXT_BOTTOM
     * @see ICss3Length#PERCENTAGE
     * @see ICss3Length# LENGTH
     * 
     */
    ICss3Length getVerticalAlign();

    void setVerticalAlign(ICss3Length verticalAlign);

    /**
     * This property specifies quotation marks for any number of embedded quotations. Values have
     * the following meanings:
     */
    @_InheritedIfNull
    QuotesType getQuotesType();

    @_InheritedIfNull
    List<Pair<String, String>> getQuotes();

    void setQuotes(QuotesType quotesType, List<Pair<String, String>> quotes);

    /**
     * The final order of characters in each block container is the same as if the bidi control
     * codes had been added as described above, markup had been stripped, and the resulting
     * character sequence had been passed to an implementation of the Unicode bidirectional
     * algorithm for plain text that produced the same line-breaks as the styled text. In this
     * process, replaced elements with 'display: inline' are treated as neutral characters, unless
     * their 'unicode-bidi' property has a value other than 'normal', in which case they are treated
     * as strong characters in the 'direction' specified for the element. All other atomic
     * inline-level boxes are treated as neutral characters always.
     * 
     * Please note that in order to be able to flow inline boxes in a uniform direction (either
     * entirely left-to-right or entirely right-to-left), more inline boxes (including anonymous
     * inline boxes) may have to be created, and some inline boxes may have to be split up and
     * reordered before flowing.
     * 
     * Because the Unicode algorithm has a limit of 61 levels of embedding, care should be taken not
     * to use 'unicode-bidi' with a value other than 'normal' unless appropriate. In particular, a
     * value of 'inherit' should be used with extreme caution. However, for elements that are, in
     * general, intended to be displayed as blocks, a setting of 'unicode-bidi: embed' is preferred
     * to keep the element together in case display is changed to inline (see example below).
     */
    UnicodeBidiMode getUnicodeBidi();

    void setUnicodeBidi(UnicodeBidiMode unicodeBidi);

    /**
     * This property declares how white space inside the element is handled. Newlines in the source
     * can be represented by a carriage return (U+000D), a linefeed (U+000A) or both (U+000D U+000A)
     * or by some other mechanism that identifies the beginning and end of document segments, such
     * as the SGML RECORD-START and RECORD-END tokens. The CSS 'white-space' processing model
     * assumes all newlines have been normalized to line feeds. UAs that recognize other newline
     * representations must apply the white space processing rules as if this normalization has
     * taken place. If no newline rules are specified for the document language, each carriage
     * return (U+000D) and CRLF sequence (U+000D U+000A) in the document text is treated as single
     * line feed character. This default normalization rule also applies to generated content.
     */
    @_InheritedIfNull
    WhiteSpaceMode getWhiteSpace();

    void setWhiteSpace(WhiteSpaceMode whiteSpace);

    /**
     * Word spacing algorithms are user agent-dependent. Word spacing is also influenced by
     * justification (see the 'text-align' property). Word spacing affects each space (U+0020) and
     * non-breaking space (U+00A0), left in the text after the white space processing rules have
     * been applied. The effect of the property on other word-separator characters is undefined.
     * However general punctuation, characters with zero advance width (such as the zero with space
     * U+200B) and fixed-width spaces (such as U+3000 and U+2000 through U+200A) are not affected.,
     * 
     * <ul>
     * <li><code>normal</code>: The normal inter-word space, as defined by the current font and/or
     * the UA.
     * <li><i>length</i>: This value indicates inter-word space in addition to the default space
     * between words. Values may be negative, but there may be implementation-specific limits.
     * </ul>
     */
    ICss3Length getWordSpacing();

    void setWordSpacing(ICss3Length wordSpacing);

    /**
     * The 'clip' property applies only to absolutely positioned elements.
     * 
     * An element's clipping region clips out any aspect of the element (e.g., content, children,
     * background, borders, text decoration, outline and visible scrolling mechanism — if any) that
     * is outside the clipping region. Content that has been clipped does not cause overflow.
     * 
     * The element's ancestors may also clip portions of their content (e.g., via their own 'clip'
     * property and/or if their 'overflow' property is not 'visible'); what is rendered is the
     * cumulative intersection.
     * 
     * If the clipping region exceeds the bounds of the UA's document window, content may be clipped
     * to that window by the native operating environment.
     */
    ClipType getClipType();

    String getClip();

    void setClip(ClipType clipType, String clip);

    /**
     * This property specifies the type of cursor to be displayed for the pointing device.
     */
    @_InheritedIfNull
    CursorType getCursorType();

    @_InheritedIfNull
    String getCursor();

    void setCursor(CursorType cursorType, String cursor);

    /*
     * The 'list-style' property is a shorthand notation for setting the three properties
     * 'list-style-type', 'list-style-image', and 'list-style-position' at the same place in the
     * style sheet.
     */
    /**
     * This property specifies appearance of the list item marker if 'list-style-image' has the
     * value 'none' or if the image pointed to by the URI cannot be displayed. The value 'none'
     * specifies no marker, otherwise there are three types of marker: glyphs, numbering systems,
     * and alphabetic systems.
     */
    @_InheritedIfNull
    ListStyleTypeMode getListStyleType();

    void setListStyleType(ListStyleTypeMode listStyleType);

    @_InheritedIfNull
    ImageType getListStyleImageType();

    /**
     * This property sets the image that will be used as the list item marker. When the image is
     * available, it will replace the marker set with the 'list-style-type' marker.
     */
    @_InheritedIfNull
    String getListStyleImage();

    void setListStyleImage(ImageType listStyleImageType, String listStyleImage);

    /**
     * This property specifies the position of the marker box with respect to the principal block
     * box.
     */
    @_InheritedIfNull
    ListStylePositionMode getListStylePosition();

    void setListStylePosition(ListStylePositionMode listStylePosition);

    /**
     * The 'table-layout' property controls the algorithm used to lay out the table cells, rows, and
     * columns.
     */
    TableLayoutMode getTableLayout();

    void setTableLayout(TableLayoutMode tableLayout);

    /**
     * This property specifies the position of the caption box with respect to the table box.
     */
    @_InheritedIfNull
    CaptionSideMode getCaptionSide();

    void setCaptionSide(CaptionSideMode captionSide);

    /**
     * In the separated borders model, this property controls the rendering of borders and
     * backgrounds around cells that have no visible content. Empty cells and cells with the
     * 'visibility' property set to 'hidden' are considered to have no visible content. Cells are
     * empty unless they contain one or more of the following:
     * <ul>
     * <li>floating content (including empty elements),
     * <li>in-flow content (including empty elements) other than white space that has been collapsed
     * away by the 'white-space' property handling.
     * </ul>
     * When this property has the value 'show', borders and backgrounds are drawn around/behind
     * empty cells (like normal cells).
     * 
     * A value of 'hide' means that no borders or backgrounds are drawn around/behind empty cells
     * (see point 6 in 17.5.1). Furthermore, if all the cells in a row have a value of 'hide' and
     * have no visible content, then the row has zero height and there is vertical border-spacing on
     * only one side of the row.
     */
    @_InheritedIfNull
    EmptyCellsMode getEmptyCells();

    void setEmptyCells(EmptyCellsMode emptyCells);

    ContentType getContentType();

    List<ContentItem> getContentItems();

    void setContent(ContentType contentType, List<ContentItem> contentItems);

    /**
     * The 'counter-increment' property accepts one or more names of counters (identifiers), each
     * one optionally followed by an integer. The integer indicates by how much the counter is
     * incremented for every occurrence of the element. The default increment is 1. Zero and
     * negative integers are allowed.
     */
    String getCounterIncrement();

    void setCounterIncrement(String counterIncrement);

    /**
     * The 'counter-reset' property also contains a list of one or more names of counters, each one
     * optionally followed by an integer. The integer gives the value that the counter is set to on
     * each occurrence of the element. The default is 0.
     */
    String getCounterReset();

    void setCounterReset(String counterReset);

    PageBreakMode getPageBreakAfter();

    void setPageBreakAfter(PageBreakMode pageBreakAfter);

    PageBreakMode getPageBreakBefore();

    void setPageBreakBefore(PageBreakMode pageBreakBefore);

    PageBreakMode getPageBreakInside();

    void setPageBreakInside(PageBreakMode pageBreakInside);

    /**
     * The 'orphans' property specifies the minimum number of lines in a block container that must
     * be left at the bottom of a page. The 'widows' property specifies the minimum number of lines
     * in a block container that must be left at the top of a page.
     * 
     * Only positive values are allowed.
     */
    @_InheritedIfNull
    Integer getOrphans();

    void setOrphans(Integer orphans);

    /**
     * The 'orphans' property specifies the minimum number of lines in a block container that must
     * be left at the bottom of a page. The 'widows' property specifies the minimum number of lines
     * in a block container that must be left at the top of a page.
     * 
     * Only positive values are allowed.
     */
    @_InheritedIfNull
    Integer getWidows();

    void setWidows(Integer widows);

    void accept(ICss3Visitor visitor);

}