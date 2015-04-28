package net.bodz.bas.ogl.shader;

import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.util.glsl.ShaderCode;

public class ShaderCodeFactory {

    String srcDir = "shader";
    String binDir = "shader/bin";

    public ShaderCode createVertexShader(GL2ES2 gl, Class<?> context, String name) {
        return ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, context, //
                srcDir, binDir, name, false);
    }

    public ShaderCode createFragmentShader(GL2ES2 gl, Class<?> context, String name) {
        return ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, context, //
                srcDir, binDir, name, false);
    }

    public ShaderCode createMutableVertexShader(GL2ES2 gl, Class<?> context, String name) {
        return ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, context, //
                srcDir, binDir, name, true);
    }

    public ShaderCode createMutableFragmentShader(GL2ES2 gl, Class<?> context, String name) {
        return ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, context, //
                srcDir, binDir, name, true);
    }

    private static ShaderCodeFactory instance = new ShaderCodeFactory();

    public static ShaderCodeFactory getInstance() {
        return instance;
    }

}
