package net.bodz.swt.image.accessor;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.err.NotImplementedException;

public abstract class SWTImageAccessor
        extends _ImageAccessor {

    protected final ImageData image;
    protected final byte[] data;
    protected final int bytesPerLine;
    protected final int depthBytes;
    protected int pointer;

    public SWTImageAccessor(ImageData image) {
        this(image, 0);
    }

    public SWTImageAccessor(ImageData image, int pointer) {
        this.image = image;
        this.data = image.data;
        this.bytesPerLine = image.bytesPerLine;
        this.depthBytes = image.depth / 8;
        setPosition(pointer);
    }

    public void checkIntegrity() {
        assert pointer >= 0 : "bad pointer";
        assert pointer < image.data.length : "bad pointer";
        int offset = pointer % bytesPerLine;
        assert offset % depthBytes == 0 : "offset alignment: " + (offset % depthBytes);
        int x = offset / depthBytes;
        assert x >= 0 && x < image.width : "x";
        int y = pointer / bytesPerLine;
        assert y >= 0 && y < image.height : "y";
    }

    @Override
    public final int getPosition() {
        return pointer;
    }

    @Override
    public final void setPosition(int position) {
        pointer = position;
        checkIntegrity();
    }

    @Override
    public final int getWidth() {
        return image.width;
    }

    @Override
    public final int getHeight() {
        return image.height;
    }

    @Override
    public final int getX() {
        int offset = pointer % bytesPerLine;
        int x = offset / depthBytes;
        return x;
    }

    @Override
    public final int getY() {
        int y = pointer / bytesPerLine;
        return y;
    }

    @Override
    public final void setX(int x) {
        int offset = pointer % bytesPerLine;
        pointer += -offset + x * depthBytes;
    }

    @Override
    public final void setY(int y) {
        int offset = pointer % bytesPerLine;
        pointer = y * bytesPerLine + offset;
    }

    @Override
    public final void getXY(int[] xy) {
        assert xy != null && xy.length >= 2 : "output xy[]";
        int y = pointer / bytesPerLine;
        int offset = pointer % bytesPerLine;
        int x = offset / depthBytes;
        xy[0] = x;
        xy[1] = y;
    }

    @Override
    public final void setXY(int x, int y) {
        pointer = y * bytesPerLine + x * depthBytes;
    }

    @Override
    public final void incX() {
        pointer += depthBytes;
    }

    @Override
    public final void incY() {
        pointer += bytesPerLine;
    }

    @Override
    public final void decX() {
        pointer -= depthBytes;
    }

    @Override
    public final void decY() {
        pointer -= bytesPerLine;
    }

    @Override
    public int getPixel() {
        int[] xy = new int[2];
        getXY(xy);
        return image.getPixel(xy[0], xy[1]);
    }

    @Override
    public void setPixel(int pixel) {
        int[] xy = new int[2];
        getXY(xy);
        image.setPixel(xy[0], xy[1], pixel);
    }

    @Override
    public RGB getRGB() {
        int pixel = getPixel();
        RGB rgb = image.palette.getRGB(pixel);
        return rgb;
    }

    @Override
    public void setRGB(RGB rgb) {
        int[] xy = new int[2];
        getXY(xy);
        int pixel = image.palette.getPixel(rgb);
        image.setPixel(xy[0], xy[1], pixel);
    }

    @Override
    public int getAlpha() {
        int[] xy = new int[2];
        getXY(xy);
        return image.getAlpha(xy[0], xy[1]);
    }

    @Override
    public void setAlpha(int alpha) {
        int[] xy = new int[2];
        getXY(xy);
        image.setAlpha(xy[0], xy[1], alpha);
    }

    /** Bytes: AA RR GG BB */
    static class ARGB32
            extends SWTImageAccessor {

        public ARGB32(ImageData image, int pointer) {
            super(image, pointer);
        }

        public ARGB32(ImageData image) {
            super(image);
        }

        @Override
        public ImageAccessor clone() {
            return new ARGB32(image, pointer);
        }

        @Override
        public RGB getRGB() {
            return copyRGB();
        }

        @Override
        public RGB copyRGB() {
            return new RGB( //
                    data[pointer + 1] & 0xff, //
                    data[pointer + 2] & 0xff, //
                    data[pointer + 3] & 0xff);
        }

        @Override
        public void setRGB(RGB rgb) {
            assert rgb.red >= 0 && rgb.red < 256 : "red";
            assert rgb.green >= 0 && rgb.green < 256 : "green";
            assert rgb.blue >= 0 && rgb.blue < 256 : "blue";
            data[pointer + 1] = (byte) rgb.red;
            data[pointer + 2] = (byte) rgb.green;
            data[pointer + 3] = (byte) rgb.blue;
        }

        @Override
        public void getRGB(int[] rgbv) {
            int p = pointer + 1;
            for (int i = 0; i < 3; i++) {
                assert p < data.length : "pointer overflow: " + p;
                rgbv[i] = data[p++] & 0xff;
            }
        }

        @Override
        public void setRGB(int[] rgbv) {
            int p = pointer + 1;
            for (int i = 0; i < 3; i++) {
                int c = rgbv[i];
                assert c >= 0 && c < 256 : "rgbv[" + i + "]=" + c;
                data[p++] = (byte) c;
            }
        }

        @Override
        public int getAlpha() {
            return data[pointer + 0] & 0xff;
        }

        @Override
        public int getRed() {
            return data[pointer + 1] & 0xff;
        }

        @Override
        public int getGreen() {
            return data[pointer + 2] & 0xff;
        }

        @Override
        public int getBlue() {
            return data[pointer + 3] & 0xff;
        }

        @Override
        public void setAlpha(int alpha) {
            assert alpha >= 0 && alpha < 256;
            data[pointer + 0] = (byte) alpha;
        }

        @Override
        public void setRed(int red) {
            assert red >= 0 && red < 256;
            data[pointer + 1] = (byte) red;
        }

        @Override
        public void setGreen(int green) {
            assert green >= 0 && green < 256;
            data[pointer + 2] = (byte) green;
        }

        @Override
        public void setBlue(int blue) {
            assert blue >= 0 && blue < 256;
            data[pointer + 3] = (byte) blue;
        }

        @Override
        public int getPixel() {
            return ((data[pointer + 0] & 0xFF) << 24) | ((data[pointer + 1] & 0xFF) << 16)
                    | ((data[pointer + 2] & 0xFF) << 8) | (data[pointer + 3] & 0xFF);
        }

        @Override
        public void setPixel(int pixel) {
            data[pointer + 0] = (byte) ((pixel >> 24) & 0xFF);
            data[pointer + 1] = (byte) ((pixel >> 16) & 0xFF);
            data[pointer + 2] = (byte) ((pixel >> 8) & 0xFF);
            data[pointer + 3] = (byte) (pixel & 0xFF);
        }

    }

    /** Bytes: BB GG RR AA */
    static class BGRA32
            extends SWTImageAccessor {

        public BGRA32(ImageData image, int pointer) {
            super(image, pointer);
        }

        public BGRA32(ImageData image) {
            super(image);
        }

        @Override
        public ImageAccessor clone() {
            return new BGRA32(image, pointer);
        }

        @Override
        public RGB getRGB() {
            return copyRGB();
        }

        @Override
        public RGB copyRGB() {
            return new RGB( //
                    data[pointer + 2] & 0xff, //
                    data[pointer + 1] & 0xff, //
                    data[pointer + 0] & 0xff);
        }

        @Override
        public void setRGB(RGB rgb) {
            assert rgb.red >= 0 && rgb.red < 256 : "red";
            assert rgb.green >= 0 && rgb.green < 256 : "green";
            assert rgb.blue >= 0 && rgb.blue < 256 : "blue";
            data[pointer + 2] = (byte) rgb.red;
            data[pointer + 1] = (byte) rgb.green;
            data[pointer + 0] = (byte) rgb.blue;
        }

        @Override
        public void getRGB(int[] rgbv) {
            int p = pointer + 3;
            for (int i = 0; i < 3; i++) {
                assert p < data.length : "pointer overflow: " + p;
                rgbv[i] = data[--p] & 0xff;
            }
        }

        @Override
        public void setRGB(int[] rgbv) {
            int p = pointer + 3;
            for (int i = 0; i < 3; i++) {
                int c = rgbv[i];
                assert c >= 0 && c < 256 : "rgbv[" + i + "]=" + c;
                data[--p] = (byte) c;
            }
        }

        @Override
        public int getAlpha() {
            return data[pointer + 3] & 0xff;
        }

        @Override
        public int getRed() {
            return data[pointer + 2] & 0xff;
        }

        @Override
        public int getGreen() {
            return data[pointer + 1] & 0xff;
        }

        @Override
        public int getBlue() {
            return data[pointer + 0] & 0xff;
        }

        @Override
        public void setAlpha(int alpha) {
            assert alpha >= 0 && alpha < 256;
            data[pointer + 3] = (byte) alpha;
        }

        @Override
        public void setRed(int red) {
            assert red >= 0 && red < 256;
            data[pointer + 2] = (byte) red;
        }

        @Override
        public void setGreen(int green) {
            assert green >= 0 && green < 256;
            data[pointer + 1] = (byte) green;
        }

        @Override
        public void setBlue(int blue) {
            assert blue >= 0 && blue < 256;
            data[pointer + 0] = (byte) blue;
        }

        @Override
        public int getPixel() {
            return ((data[pointer + 0] & 0xFF) << 24) | ((data[pointer + 1] & 0xFF) << 16)
                    | ((data[pointer + 2] & 0xFF) << 8) | (data[pointer + 3] & 0xFF);
        }

        @Override
        public void setPixel(int pixel) {
            data[pointer + 0] = (byte) ((pixel >> 24) & 0xFF);
            data[pointer + 1] = (byte) ((pixel >> 16) & 0xFF);
            data[pointer + 2] = (byte) ((pixel >> 8) & 0xFF);
            data[pointer + 3] = (byte) (pixel & 0xFF);
        }

    }

    /**
     * Bytes: RR GG BB
     */
    static class RGB24
            extends SWTImageAccessor {

        public RGB24(ImageData image, int pointer) {
            super(image, pointer);
        }

        public RGB24(ImageData image) {
            super(image);
        }

        @Override
        public ImageAccessor clone() {
            return new RGB24(image, pointer);
        }

        @Override
        public RGB getRGB() {
            return copyRGB();
        }

        @Override
        public RGB copyRGB() {
            return new RGB( //
                    data[pointer + 0] & 0xff, //
                    data[pointer + 1] & 0xff, //
                    data[pointer + 2] & 0xff);
        }

        @Override
        public void setRGB(RGB rgb) {
            assert rgb.red >= 0 && rgb.red < 256 : "red";
            assert rgb.green >= 0 && rgb.green < 256 : "green";
            assert rgb.blue >= 0 && rgb.blue < 256 : "blue";
            data[pointer + 0] = (byte) rgb.red;
            data[pointer + 1] = (byte) rgb.green;
            data[pointer + 2] = (byte) rgb.blue;
        }

        @Override
        public void getRGB(int[] rgbv) {
            int p = pointer;
            for (int i = 0; i < 3; i++) {
                assert p < data.length : "pointer overflow: " + p;
                rgbv[i] = data[p++] & 0xff;
            }
        }

        @Override
        public void setRGB(int[] rgbv) {
            int p = pointer;
            for (int i = 0; i < 3; i++) {
                int c = rgbv[i];
                assert c >= 0 && c < 256 : "rgbv[" + i + "]=" + c;
                data[p++] = (byte) c;
            }
        }

        @Override
        public int getRed() {
            return data[pointer + 0] & 0xff;
        }

        @Override
        public int getGreen() {
            return data[pointer + 1] & 0xff;
        }

        @Override
        public int getBlue() {
            return data[pointer + 2] & 0xff;
        }

        @Override
        public void setRed(int red) {
            assert red >= 0 && red < 256;
            data[pointer] = (byte) red;
        }

        @Override
        public void setGreen(int green) {
            assert green >= 0 && green < 256;
            data[pointer + 1] = (byte) green;
        }

        @Override
        public void setBlue(int blue) {
            assert blue >= 0 && blue < 256;
            data[pointer + 2] = (byte) blue;
        }

        @Override
        public int getPixel() {
            return ((data[pointer] & 0xFF) << 16) | ((data[pointer + 1] & 0xFF) << 8) | (data[pointer + 2] & 0xFF);
        }

        @Override
        public void setPixel(int pixel) {
            data[pointer] = (byte) ((pixel >> 16) & 0xFF);
            data[pointer + 1] = (byte) ((pixel >> 8) & 0xFF);
            data[pointer + 2] = (byte) (pixel & 0xFF);
        }

    }

    static abstract class _Index
            extends SWTImageAccessor {

        protected RGB[] palette;

        public _Index(ImageData image) {
            super(image);
            this.palette = image.palette.colors;
        }

        public _Index(ImageData image, int pointer) {
            super(image, pointer);
            this.palette = image.palette.colors;
        }

        @Override
        public int getRed() {
            int pixel = getPixel();
            return palette[pixel].red;
        }

        @Override
        public int getGreen() {
            int pixel = getPixel();
            return palette[pixel].green;
        }

        @Override
        public int getBlue() {
            int pixel = getPixel();
            return palette[pixel].blue;
        }

    }

    static class Index8
            extends _Index {

        public Index8(ImageData image, int pointer) {
            super(image, pointer);
        }

        public Index8(ImageData image) {
            super(image);
        }

        @Override
        public Index8 clone() {
            return new Index8(image, pointer);
        }

        @Override
        public int getPixel() {
            return data[pointer];
        }

        @Override
        public void setPixel(int pixel) {
            data[pointer] = (byte) pixel;
        }

    }

    static class Index4
            extends _Index {

        public Index4(ImageData image, int pointer) {
            super(image, pointer);
            throw new NotImplementedException();
        }

        public Index4(ImageData image) {
            super(image);
            throw new NotImplementedException();
        }

        @Override
        public ImageAccessor clone() {
            return new Index4(image, pointer);
        }

    }

    static class Index2
            extends _Index {

        public Index2(ImageData image, int pointer) {
            super(image, pointer);
            throw new NotImplementedException();
        }

        public Index2(ImageData image) {
            super(image);
            throw new NotImplementedException();
        }

        @Override
        public ImageAccessor clone() {
            return new Index2(image, pointer);
        }

    }

    static class Index1
            extends _Index {

        public Index1(ImageData image, int pointer) {
            super(image, pointer);
            throw new NotImplementedException();
        }

        public Index1(ImageData image) {
            super(image);
            throw new NotImplementedException();
        }

        @Override
        public ImageAccessor clone() {
            return new Index1(image, pointer);
        }

    }

}
