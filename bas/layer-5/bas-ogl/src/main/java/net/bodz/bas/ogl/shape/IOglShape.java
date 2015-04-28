package net.bodz.bas.ogl.shape;

import net.bodz.bas.err.LinkException;
import net.bodz.bas.ogl.shader.IShaderProgram;

import com.jogamp.opengl.GL2;

public interface IOglShape {

    void setup(GL2 gl)
            throws LinkException;

    void render(GL2 gl);

    IShaderProgram getShader();

    void setShader(IShaderProgram shader);

}
