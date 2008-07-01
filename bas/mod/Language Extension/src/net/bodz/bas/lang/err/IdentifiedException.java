package net.bodz.bas.lang.err;

/**
 * 本异常用于分离已识别的异常，并绕过编译器的异常检查。
 * 
 * <p>
 * 通常异常有如下几种处理方法：
 * <ol>
 * <li>通过 throws 语句声明向外传递异常
 * <li>解决异常，根据是否发生异常执行不同的动作。比如，当某个文件不能访问时，尝试访问其它文件。解决后的异常不再向外抛出。
 * <li>转换异常，通常是将 Exception 的各个派生异常转换至一个统一异常再向外抛出。
 * </ol>
 * 已识别的异常是指在不转换异常的前提下，将异常标记为已识别。已识别的异常通常不再被捕获，而直接使程序终止。例如：
 * 
 * <pre>
 *    int divide(String a, String b) {
 *        assert a != null &amp;&amp; b != null; 
 *        try {
 *            int i = Integer.parseInt(a); 
 *            int j = Integer.parseInt(b); 
 *            return i / j; 
 *        } catch (ParseException e) {
 *            throw new IdentifiedException(e.getMessage(), e); 
 *        }
 *    }
 * </pre>
 * 
 * 通过标注 ParseException 为已识别异常，编译器将不再要求 divide 抛出 ParseException。而 divide
 * 调用者也无法捕捉到该异常，但 ArithmeticException (除数为0) 并未被识别，因此 divide 调用者可以捕获该异常。
 * 
 * <hr>
 * This exception is used to separate identified exceptions from others, and
 * bypass the exception checking of compiling.
 * <p>
 * Generally, there are several ways to process exceptions:
 * <ol>
 * <li>By add throws statements, the exceptions are passed out to caller.
 * <li>Handle the exceptions, e.g. try to access a different file when a file
 * couldn't be accessed.
 * <li>Convert the exceptions to a unified exception.
 * </ol>
 * IdentifiedException is to mark exceptions as been identified, so they won't
 * be catched any more, they would cancel the execution of program, and the
 * compiler also not check throws statements against them.
 */
public class IdentifiedException extends RuntimeException {

    private static final long serialVersionUID = 3749253268735929927L;

    public IdentifiedException() {
        super();
    }

    public IdentifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentifiedException(String message) {
        super(message);
    }

    public IdentifiedException(Throwable cause) {
        super(cause);
    }

}
