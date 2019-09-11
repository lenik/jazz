package net.bodz.bas.ogl.shader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.ogl.GLLinkException;

import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

public abstract class AbstractShaderProgram
        implements IShaderProgram {

    static final Logger logger = LoggerFactory.getLogger(AbstractShaderProgram.class);

    private ShaderProgram program;
    private boolean locationsValid;

    Class<?> context;
    String[] filenames;
    boolean loaded;

    public AbstractShaderProgram() {
    }

    @Override
    public int getProgram() {
        return program.program();
    }

    @Override
    public void source(Class<?> context, String... filenames) {
        this.context = context;
        this.filenames = filenames;
    }

    @Override
    public void reload(GL2ES2 gl) {
        if (program != null) {
            program.release(gl);
            program.destroy(gl);
        }
        program = new ShaderProgram();

        ShaderCodeFactory factory = ShaderCodeFactory.getInstance();
        for (String filename : filenames) {
            int lastDot = filename.lastIndexOf('.');
            if (lastDot == -1)
                throw new IllegalArgumentException("Expect extension in: " + filename);

            ShaderCode code;
            String name = filename.substring(0, lastDot);
            String extension = filename.substring(lastDot + 1);
            if ("fp".equals(extension))
                code = factory.createMutableFragmentShader(gl, context, name);
            else if ("vp".equals(extension))
                code = factory.createMutableVertexShader(gl, context, name);
            else
                throw new IllegalArgumentException("Unknown extension: " + filename);
            program.add(code);
        } // for filename
        loaded = true;
    }

    @Override
    public synchronized void link(GL2ES2 gl)
            throws GLLinkException {
        if (!loaded)
            reload(gl);

        try {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(buf, true, "utf-8");

            locationsValid = false;
            if (!program.link(gl, out)) {
                String mesg = buf.toString("utf-8");
                logger.error("glLinkProgram: " + mesg);
                throw new GLLinkException("Failed to link the program.");
            }

            if (!program.validateProgram(gl, out)) {
                String mesg = buf.toString("utf-8");
                logger.error("glValidateProgram: " + mesg);
                throw new GLLinkException("Failed to validate the program.");
            }

            relocate(gl);
            locationsValid = true;
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isLinked() {
        return program.linked();
    }

    @Override
    public boolean validate(GL2ES2 gl, PrintStream verboseOut) {
        return program.validateProgram(gl, verboseOut);
    }

    @Override
    public void begin(GL2ES2 gl) {
        if (program.linked())
            program.useProgram(gl, true);
    }

    @Override
    public void end(GL2ES2 gl) {
        if (program.linked())
            program.useProgram(gl, false);
    }

    @Override
    public boolean isActive() {
        return program.inUse();
    }

    @Override
    public void release(GL2ES2 gl) {
        program.release(gl);
    }

    @Override
    public void release(GL2ES2 gl, boolean destroyShaderCode) {
        program.release(gl, destroyShaderCode);
    }

    protected void assureLocationsValid() {
        if (!locationsValid)
            throw new IllegalStateException("Not linked/located yet.");
    }

    protected abstract void relocate(GL2ES2 gl)
            throws GLLinkException;

    protected int locate(GL2ES2 gl, String name) {
        return gl.glGetUniformLocation(program.program(), name);
    }

}
