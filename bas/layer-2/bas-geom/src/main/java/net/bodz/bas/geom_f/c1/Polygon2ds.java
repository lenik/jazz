package net.bodz.bas.geom_f.c1;

import net.bodz.bas.geom_f.base.Polygon2d;

public class Polygon2ds {

    static final int DEFAULT_WAVE_SEGMENTS = 100;

    public Polygon2d createWave(float x0, float y0, float x1, float y1) {
        return createWave(x0, y0, x1, y1, DEFAULT_WAVE_SEGMENTS, 0.0f);
    }

    public Polygon2d createWave(float x0, float y0, float x1, float y1, int segments) {
        return createWave(x0, y0, x1, y1, segments, 0.0f);
    }

    static Polygon2d createWave(float x0, float y0, float x1, float y1, int segments, float angleOffset) {
        float cx = (x0 + x1) / 2;
        float cy = (y0 + y1) / 2;
        float rx = Math.abs((x1 - x0) / 2);
        float ry = Math.abs((y1 - y0) / 2);
        float[] xset = new float[segments];
        float[] yset = new float[segments];
        float a = angleOffset;
        float delta = (float) Math.PI * 2 / segments;
        for (int i = 0; i < segments; i++) {
            xset[i] = cx + rx * (float) Math.cos(a);
            yset[i] = cy + ry * (float) Math.sin(a);
            a = a + delta;
        }
        return new Polygon2d(xset, yset);
    }

}
