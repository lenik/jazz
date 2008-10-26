package net.bodz.math.mat;


public class Matrixes3f {

    public static final Matrix3f IDENTITY = new Matrix3f(
            1.0f, 0.0f, 0.0f, 
            0.0f, 1.0f, 0.0f, 
            0.0f, 0.0f, 1.0f
            ); 
    
    public static final Matrix3f ZEROS = new Matrix3f(
            0.0f, 0.0f, 0.0f, 
            0.0f, 0.0f, 0.0f, 
            0.0f, 0.0f, 0.0f
            ); 
    
    public static final Matrix3f ONES = new Matrix3f(
            1.0f, 1.0f, 1.0f, 
            1.0f, 1.0f, 1.0f, 
            1.0f, 1.0f, 1.0f
            ); 
    
    /**
     * [ M ] [ Col ]'
     */
    public static class Col {

        public static final Matrix3f translate(float dx, float dy) {
            return new Matrix3f(
                    1.0f, 0.0f, dx, 
                    0.0f, 1.0f, dy, 
                    0.0f, 0.0f, 1.0f
                    ); 
        }
        
        public static final Matrix3f scale(float kx, float ky) {
            return new Matrix3f(
                    kx,   0.0f, 0.0f, 
                    0.0f, ky,   0.0f, 
                    0.0f, 0.0f, 1.0f
                    ); 
        }
        
        public static final Matrix3f skew(float kx, float ky) {
            return new Matrix3f(
                    1.0f, kx,   0.0f, 
                    ky,   1.0f, 0.0f, 
                    0.0f, 0.0f, 1.0f
                    ); 
        }
        
        public static final Matrix3f rotate(float angle) {
            float S = (float)Math.sin(angle);
            float C = (float)Math.cos(angle);
            return new Matrix3f(
                    C,    -S,   0.0f, 
                    S,    C,    0.0f, 
                    0.0f, 0.0f, 1.0f
                    ); 
        }
        
    }
    
    /**
     * [ Row ] [ M ]
     */
    public static class Row {

        public static final Matrix3f translate(float kx, float ky) {
            Matrix3f mat = Col.translate(kx, ky);
            mat.transpose();
            return mat;
        }

        public static final Matrix3f scale(float kx, float ky) {
            return Col.scale(kx, ky);
        }

        public static final Matrix3f skew(float kx, float ky) {
            Matrix3f mat = Col.skew(kx, ky);
            mat.transpose();
            return mat;
        }

        public static final Matrix3f rotate(float angle) {
            Matrix3f mat = Col.rotate(angle);
            mat.transpose();
            return mat;
        }

    }
    
}
