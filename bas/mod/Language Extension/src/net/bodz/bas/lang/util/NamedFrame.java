package net.bodz.bas.lang.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.loader.CustomClassLoader;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @test {@link NamedFrameTest}
 */
public class NamedFrame {

    static CustomClassLoader frameClassLoader;
    static {
        frameClassLoader = new CustomClassLoader();
    }

    /**
     * @see _FrameTemplate
     */
    public static Runnable wrap(Runnable runnable, String className, String methodName,
            String description) {
        className = toSafeName(className);
        methodName = toSafeName(methodName);
        byte[] bytes = Dump.dump(className, methodName, description);

        Class<?> craftClass = frameClassLoader._defineClass(className, bytes, 0, bytes.length);

        try {
            Constructor<?> craftCtor = craftClass.getConstructor(String.class, Runnable.class);
            final Object craft = craftCtor.newInstance(description, runnable);
            Runnable craftWrapper;
            if (craft instanceof Runnable)
                craftWrapper = (Runnable) craft;
            else {
                final Method craftMethod = craftClass.getMethod(methodName);
                craftWrapper = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            craftMethod.invoke(craft);
                        } catch (IllegalArgumentException e) {
                            throw new FrameConstructException(e);
                        } catch (IllegalAccessException e) {
                            throw new FrameConstructException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
            }
            return craftWrapper;
        } catch (Exception e) {
            throw new FrameConstructException(e);
        }
    }

    static String toSafeName(String s) {
        s = s.replaceAll("\\s", ""); //$NON-NLS-1$ //$NON-NLS-2$
        return s;
    }

    static class Dump implements Opcodes {

        public static byte[] dump(String className, String methodName, String description) {
            boolean isRunnable = "run".equals(methodName); //$NON-NLS-1$
            String internalClassName = className.replace('.', '/');

            ClassWriter cw = new ClassWriter(0);
            FieldVisitor fv;
            MethodVisitor mv;

            cw.visit(V1_3, ACC_PUBLIC + ACC_SUPER, internalClassName, null, "java/lang/Object", //$NON-NLS-1$
                    isRunnable ? new String[] { "java/lang/Runnable" } : null); //$NON-NLS-1$

            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "description", "Ljava/lang/String;", null, //$NON-NLS-1$ //$NON-NLS-2$
                    null);
            fv.visitEnd();

            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "target", "Ljava/lang/Runnable;", null, //$NON-NLS-1$ //$NON-NLS-2$
                    null);
            fv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/String;Ljava/lang/Runnable;)V", //$NON-NLS-1$ //$NON-NLS-2$
                    null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, internalClassName, "description", "Ljava/lang/String;"); //$NON-NLS-1$ //$NON-NLS-2$
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(PUTFIELD, internalClassName, "target", "Ljava/lang/Runnable;"); //$NON-NLS-1$ //$NON-NLS-2$
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 3);
            mv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, methodName, "()V", null, null); //$NON-NLS-1$
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, internalClassName, "target", "Ljava/lang/Runnable;"); //$NON-NLS-1$ //$NON-NLS-2$
            mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Runnable", "run", "()V"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null); //$NON-NLS-1$ //$NON-NLS-2$
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, internalClassName, "description", "Ljava/lang/String;"); //$NON-NLS-1$ //$NON-NLS-2$
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            cw.visitEnd();
            return cw.toByteArray();
        }

    }

}
