package net.bodz.bas.gui.xjdoc;

public interface IImageData {

    /**
     * Returns the alpha value at offset <code>x</code> in scanline <code>y</code> in the receiver's
     * alpha data. The alpha value is between 0 (transparent) and 255 (opaque).
     * 
     * @param x
     *            the x coordinate of the pixel to get the alpha value of
     * @param y
     *            the y coordinate of the pixel to get the alpha value of
     * @return the alpha value at the given coordinates
     */
    int getAlpha(int x, int y);

    /**
     * Returns <code>getWidth</code> alpha values starting at offset <code>x</code> in scanline
     * <code>y</code> in the receiver's alpha data starting at <code>startIndex</code>. The alpha
     * values are unsigned, between <code>(byte)0</code> (transparent) and <code>(byte)255</code>
     * (opaque).
     * 
     * @param x
     *            the x position of the pixel to begin getting alpha values
     * @param y
     *            the y position of the pixel to begin getting alpha values
     * @param getWidth
     *            the width of the data to get
     * @param alphas
     *            the buffer in which to put the alpha values
     * @param startIndex
     *            the offset into the image to begin getting alpha values
     * 
     * @exception IndexOutOfBoundsException
     *                if getWidth is too large
     */
    void getAlphas(int x, int y, int getWidth, byte[] alphas, int startIndex);

    /**
     * Returns the pixel value at offset <code>x</code> in scanline <code>y</code> in the receiver's
     * data.
     * 
     * @param x
     *            the x position of the pixel to get
     * @param y
     *            the y position of the pixel to get
     * @return the pixel at the given coordinates
     * 
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_INVALID_ARGUMENT - if either argument is out of bounds</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_UNSUPPORTED_DEPTH if the depth is not one of 1, 2, 4, 8, 16, 24 or
     *                32</li>
     *                </ul>
     */
    int getPixel(int x, int y);

    /**
     * Returns <code>getWidth</code> pixel values starting at offset <code>x</code> in scanline
     * <code>y</code> in the receiver's data starting at <code>startIndex</code>.
     * 
     * @param x
     *            the x position of the first pixel to get
     * @param y
     *            the y position of the first pixel to get
     * @param getWidth
     *            the width of the data to get
     * @param pixels
     *            the buffer in which to put the pixels
     * @param startIndex
     *            the offset into the byte array to begin storing pixels
     * 
     * @exception IndexOutOfBoundsException
     *                if getWidth is too large
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_NULL_ARGUMENT - if pixels is null</li>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                <li>ERROR_INVALID_ARGUMENT - if getWidth is negative</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_UNSUPPORTED_DEPTH - if the depth is not one of 1, 2, 4 or 8 (For
     *                higher depths, use the int[] version of this method.)</li>
     *                </ul>
     */
    void getPixels(int x, int y, int getWidth, byte[] pixels, int startIndex);

    /**
     * Returns <code>getWidth</code> pixel values starting at offset <code>x</code> in scanline
     * <code>y</code> in the receiver's data starting at <code>startIndex</code>.
     * 
     * @param x
     *            the x position of the first pixel to get
     * @param y
     *            the y position of the first pixel to get
     * @param getWidth
     *            the width of the data to get
     * @param pixels
     *            the buffer in which to put the pixels
     * @param startIndex
     *            the offset into the buffer to begin storing pixels
     * 
     * @exception IndexOutOfBoundsException
     *                if getWidth is too large
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_NULL_ARGUMENT - if pixels is null</li>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                <li>ERROR_INVALID_ARGUMENT - if getWidth is negative</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_UNSUPPORTED_DEPTH - if the depth is not one of 1, 2, 4, 8, 16, 24 or
     *                32</li>
     *                </ul>
     */
    void getPixels(int x, int y, int getWidth, int[] pixels, int startIndex);

    /**
     * Returns the image transparency type, which will be one of <code>SWT.TRANSPARENCY_NONE</code>,
     * <code>SWT.TRANSPARENCY_MASK</code>, <code>SWT.TRANSPARENCY_PIXEL</code> or
     * <code>SWT.TRANSPARENCY_ALPHA</code>.
     * 
     * @return the receiver's transparency type
     */
    TransparencyType getTransparencyType();

    /**
     * Returns an <code>ImageData</code> which specifies the transparency mask information for the
     * receiver. If the receiver has no transparency or is not an icon, returns an opaque mask.
     * 
     * @return the transparency mask
     */
    IImageData getTransparencyMask();

    /**
     * Returns a copy of the receiver which has been stretched or shrunk to the specified size. If
     * either the width or height is negative, the resulting image will be inverted in the
     * associated axis.
     * 
     * @param width
     *            the width of the new ImageData
     * @param height
     *            the height of the new ImageData
     * @return a scaled copy of the image
     */
    IImageData scaledTo(int width, int height);

    /**
     * Sets the alpha value at offset <code>x</code> in scanline <code>y</code> in the receiver's
     * alpha data. The alpha value must be between 0 (transparent) and 255 (opaque).
     * 
     * @param x
     *            the x coordinate of the alpha value to set
     * @param y
     *            the y coordinate of the alpha value to set
     * @param alpha
     *            the value to set the alpha to
     * 
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                </ul>
     */
    void setAlpha(int x, int y, int alpha);

    /**
     * Sets the alpha values starting at offset <code>x</code> in scanline <code>y</code> in the
     * receiver's alpha data to the values from the array <code>alphas</code> starting at
     * <code>startIndex</code>. The alpha values must be between <code>(byte)0</code> (transparent)
     * and <code>(byte)255</code> (opaque)
     * 
     * @param x
     *            the x coordinate of the pixel to being setting the alpha values
     * @param y
     *            the y coordinate of the pixel to being setting the alpha values
     * @param putWidth
     *            the width of the alpha values to set
     * @param alphas
     *            the alpha values to set
     * @param startIndex
     *            the index at which to begin setting
     * 
     * @exception IndexOutOfBoundsException
     *                if putWidth is too large
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_NULL_ARGUMENT - if pixels is null</li>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                <li>ERROR_INVALID_ARGUMENT - if putWidth is negative</li>
     *                </ul>
     */
    void setAlphas(int x, int y, int putWidth, byte[] alphas, int startIndex);

    /**
     * Sets the pixel value at offset <code>x</code> in scanline <code>y</code> in the receiver's
     * data.
     * 
     * @param x
     *            the x coordinate of the pixel to set
     * @param y
     *            the y coordinate of the pixel to set
     * @param pixelValue
     *            the value to set the pixel to
     * 
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_UNSUPPORTED_DEPTH if the depth is not one of 1, 2, 4, 8, 16, 24 or
     *                32</li>
     *                </ul>
     */
    void setPixel(int x, int y, int pixelValue);

    /**
     * Sets the pixel values starting at offset <code>x</code> in scanline <code>y</code> in the
     * receiver's data to the values from the array <code>pixels</code> starting at
     * <code>startIndex</code>.
     * 
     * @param x
     *            the x position of the pixel to set
     * @param y
     *            the y position of the pixel to set
     * @param putWidth
     *            the width of the pixels to set
     * @param pixels
     *            the pixels to set
     * @param startIndex
     *            the index at which to begin setting
     * 
     * @exception IndexOutOfBoundsException
     *                if putWidth is too large
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_NULL_ARGUMENT - if pixels is null</li>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                <li>ERROR_INVALID_ARGUMENT - if putWidth is negative</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_UNSUPPORTED_DEPTH if the depth is not one of 1, 2, 4, 8 (For higher
     *                depths, use the int[] version of this method.)</li>
     *                </ul>
     */
    void setPixels(int x, int y, int putWidth, byte[] pixels, int startIndex);

    /**
     * Sets the pixel values starting at offset <code>x</code> in scanline <code>y</code> in the
     * receiver's data to the values from the array <code>pixels</code> starting at
     * <code>startIndex</code>.
     * 
     * @param x
     *            the x position of the pixel to set
     * @param y
     *            the y position of the pixel to set
     * @param putWidth
     *            the width of the pixels to set
     * @param pixels
     *            the pixels to set
     * @param startIndex
     *            the index at which to begin setting
     * 
     * @exception IndexOutOfBoundsException
     *                if putWidth is too large
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_NULL_ARGUMENT - if pixels is null</li>
     *                <li>ERROR_INVALID_ARGUMENT - if x or y is out of bounds</li>
     *                <li>ERROR_INVALID_ARGUMENT - if putWidth is negative</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_UNSUPPORTED_DEPTH if the depth is not one of 1, 2, 4, 8, 16, 24 or
     *                32</li>
     *                </ul>
     */
    void setPixels(int x, int y, int putWidth, int[] pixels, int startIndex);

}

enum TransparencyType {
    none, mask, pixel, alpha,
}