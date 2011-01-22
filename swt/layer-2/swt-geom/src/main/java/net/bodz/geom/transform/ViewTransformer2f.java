package net.bodz.geom.transform;

import javax.vecmath.Vector2f;


public interface ViewTransformer2f extends Transformer2f {

    void translate(Vector2f dv);

    void translate(float x, float y);

    void translateX(float x);

    void translateY(float y);

    void scale(Vector2f kv);

    void scale(float kx, float ky);

    void scaleX(float k);

    void scaleY(float k);

    void rotate(float angle);

}
