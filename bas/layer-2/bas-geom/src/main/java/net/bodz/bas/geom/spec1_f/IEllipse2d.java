package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.IShapeAmount2d;

/**
 * An ellipse (from Greek ἔλλειψις elleipsis, a "falling short") is a plane curve that results from
 * the intersection of a cone by a plane in a way that produces a closed curve. Circles are special
 * cases of ellipses, obtained when the cutting plane is orthogonal to the cone's axis. An ellipse
 * is also the locus of all points of the plane whose distances to two fixed points add to the same
 * constant.
 */
public interface IEllipse2d
        extends IPrimitive2d, IShapeAmount2d {

    // -o IShape2d

    @Override
    IEllipse2d shot();

    @Override
    Ellipse2d snapshot();

    @Override
    Ellipse2d snap();

    // -o IShapeAmount2d

    //

    Point2d getCenter1();

    void setCenter1(Point2d center1);

    Point2d getCenter2();

    void setCenter2(Point2d center2);

    /**
     * An ellipse is a smooth closed curve which is symmetric about its horizontal and vertical
     * axes. The distance between antipodal points on the ellipse, or pairs of points whose midpoint
     * is at the center of the ellipse, is maximum along the major axis or transverse diameter, and
     * a minimum along the perpendicular minor axis or conjugate diameter.[1]
     * 
     * aka. a, also pf1 + pf2 = 2a.
     */
    float getTransverseDiameter();

    /**
     * An ellipse is a smooth closed curve which is symmetric about its horizontal and vertical
     * axes. The distance between antipodal points on the ellipse, or pairs of points whose midpoint
     * is at the center of the ellipse, is maximum along the major axis or transverse diameter, and
     * a minimum along the perpendicular minor axis or conjugate diameter.[1]
     * 
     * aka. b.
     */
    float getConjugateDiameter();

    /**
     * Focus The distance from the center C to either focus is f = ae, which can be expressed in
     * terms of the major and minor radii:
     * 
     * <code>
     * <i>f</i> = <i>sqrt(a<sup>2</sup> - b<sup>2</sup>)</i>
     * </code>
     */
    float getFocus();

    /**
     * Eccentricity The eccentricity of the ellipse (commonly denoted as either e or ) is
     * 
     * <code><i>
     * e = sqrt( (a<sup>2</sup> - b<sup>2</sup>) / a<sup>2</sup> ) 
     *    = sqrt(1 - (b/a)<sup>2</sup> )
     *    = f/a
     * </i></code>
     */
    float getEccentricity();

}
