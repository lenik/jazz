package net.bodz.bas.jvm.stack;

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
    public static Runnable wrap(Runnable runnable, String className, String methodName, String description) {
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
        s = s.replaceAll("\\s", "");  
        return s;
    }

    static class Dump implements Opcodes {

        public static byte[] dump(String className, String methodName, String description) {
            boolean isRunnable = "run".equals(methodName); 
            String internalClassName = className.replace('.', '/');

            ClassWriter cw = new ClassWriter(0);
            FieldVisitor fv;
            MethodVisitor mv;

            cw.visit(V1_3, ACC_PUBLIC + ACC_SUPER, internalClassName, null, "java/lang/Object", 
                    isRunnable ? new String[] { "java/lang/Runnable" } : null); 

            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "description", "Ljava/lang/String;", null,  
                    null);
            fv.visitEnd();

            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "target", "Ljava/lang/Runnable;", null,  
                    null);
            fv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/String;Ljava/lang/Runnable;)V",  
                    null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");   
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, internalClassName, "description", "Ljava/lang/String;");  
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(PUTFIELD, internalClassName, "target", "Ljava/lang/Runnable;");  
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 3);
            mv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, methodName, "()V", null, null); 
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, internalClassName, "target", "Ljava/lang/Runnable;");  
            mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Runnable", "run", "()V");   
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);  
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, internalClassName, "description", "Ljava/lang/String;");  
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            cw.visitEnd();
            return cw.toByteArray();
        }

    }

}
