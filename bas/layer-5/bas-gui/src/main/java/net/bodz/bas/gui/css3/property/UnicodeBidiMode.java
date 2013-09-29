package net.bodz.bas.gui.css3.property;

/**
 * The final order of characters in each block container is the same as if the bidi control codes
 * had been added as described above, markup had been stripped, and the resulting character sequence
 * had been passed to an implementation of the Unicode bidirectional algorithm for plain text that
 * produced the same line-breaks as the styled text. In this process, replaced elements with
 * 'display: inline' are treated as neutral characters, unless their 'unicode-bidi' property has a
 * value other than 'normal', in which case they are treated as strong characters in the 'direction'
 * specified for the element. All other atomic inline-level boxes are treated as neutral characters
 * always.
 * 
 * Please note that in order to be able to flow inline boxes in a uniform direction (either entirely
 * left-to-right or entirely right-to-left), more inline boxes (including anonymous inline boxes)
 * may have to be created, and some inline boxes may have to be split up and reordered before
 * flowing.
 * 
 * Because the Unicode algorithm has a limit of 61 levels of embedding, care should be taken not to
 * use 'unicode-bidi' with a value other than 'normal' unless appropriate. In particular, a value of
 * 'inherit' should be used with extreme caution. However, for elements that are, in general,
 * intended to be displayed as blocks, a setting of 'unicode-bidi: embed' is preferred to keep the
 * element together in case display is changed to inline (see example below).
 * 
 * The following example shows an XML document with bidirectional text. It illustrates an important
 * design principle: DTD designers should take bidi into account both in the language proper
 * (elements and attributes) and in any accompanying style sheets. The style sheets should be
 * designed so that bidi rules are separate from other style rules. The bidi rules should not be
 * overridden by other style sheets so that the document language's or DTD's bidi behavior is
 * preserved.
 */
public enum UnicodeBidiMode {

    /**
     * The element does not open an additional level of embedding with respect to the bidirectional
     * algorithm. For inline elements, implicit reordering works across element boundaries.
     */
    normal,

    /**
     * If the element is inline, this value opens an additional level of embedding with respect to
     * the bidirectional algorithm. The direction of this embedding level is given by the
     * 'direction' property. Inside the element, reordering is done implicitly. This corresponds to
     * adding a LRE (U+202A; for 'direction: ltr') or RLE (U+202B; for 'direction: rtl') at the
     * start of the element and a PDF (U+202C) at the end of the element.
     */
    embed,

    /**
     * For inline elements this creates an override. For block container elements this creates an
     * override for inline-level descendants not within another block container element. This means
     * that inside the element, reordering is strictly in sequence according to the 'direction'
     * property; the implicit part of the bidirectional algorithm is ignored. This corresponds to
     * adding a LRO (U+202D; for 'direction: ltr') or RLO (U+202E; for 'direction: rtl') at the
     * start of the element or at the start of each anonymous child block box, if any, and a PDF
     * (U+202C) at the end of the element.
     */
    bidi_override,

}
