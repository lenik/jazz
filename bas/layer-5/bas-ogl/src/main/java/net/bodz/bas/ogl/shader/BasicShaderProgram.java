package net.bodz.bas.ogl.shader;

import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GLAutoDrawable;

public class BasicShaderProgram
        extends AbstractShaderProgram {

    int timeLoc;
    int mouseLoc;
    int resolutionLoc;

    @Override
    protected void relocate(GL2ES2 gl) {
        timeLoc = locate(gl, "time");
        mouseLoc = locate(gl, "mouse");
        resolutionLoc = locate(gl, "resolution");
    }

    @Override
    public void update(GLAutoDrawable drawable) {
        GL2ES2 gl = drawable.getGL().getGL2ES2();

        long time = System.currentTimeMillis();
        setTime(gl, time);

        int width = drawable.getSurfaceWidth();
        int height = drawable.getSurfaceHeight();
        setResolution(gl, width, height);
    }

    public void setTime(GL2ES2 gl, long timel) {
        int time = (int) (timel % 60_000);
        float timef = (time / 1000.0f * 3.0f);
        setTime(gl, timef);
    }

    public void setTime(GL2ES2 gl, float time) {
        assureLocationsValid();
        gl.glUniform1f(timeLoc, time);
    }

    public void setResolution(GL2ES2 gl, float width, float height) {
        assureLocationsValid();
        gl.glUniform2f(resolutionLoc, width, height);
    }

}
