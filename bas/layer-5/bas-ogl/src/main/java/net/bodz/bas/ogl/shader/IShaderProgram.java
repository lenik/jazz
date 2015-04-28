package net.bodz.bas.ogl.shader;

import java.io.PrintStream;

import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GLAutoDrawable;

public interface IShaderProgram {

    int getProgram();

    // void add(ShaderCode shaderCode) throws GLException;
    // boolean add(GL2ES2 gl, ShaderCode shaderCode, PrintStream verboseOut);

    void source(Class<?> context, String... filenames);

    void reload(GL2ES2 gl);

    void link(GL2ES2 gl);

    boolean isLinked();

    boolean validate(GL2ES2 gl, PrintStream verboseOut);

    void begin(GL2ES2 gl);

    void end(GL2ES2 gl);

    boolean isActive();

    void release(GL2ES2 gl);

    void release(GL2ES2 gl, boolean destroyShaderCode);

    void update(GLAutoDrawable drawable);

}
