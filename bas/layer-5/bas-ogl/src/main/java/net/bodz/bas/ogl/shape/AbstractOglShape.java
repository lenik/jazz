package net.bodz.bas.ogl.shape;

import net.bodz.bas.err.LinkException;
import net.bodz.bas.ogl.shader.IShaderProgram;

import com.jogamp.opengl.GL2;

public abstract class AbstractOglShape
        implements IOglShape {

    private IShaderProgram shader;

    @Override
    public void setup(GL2 gl)
            throws LinkException {
    }

    @Override
    public IShaderProgram getShader() {
        return shader;
    }

    @Override
    public void setShader(IShaderProgram shader) {
        this.shader = shader;
    }

}
