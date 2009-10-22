package net.bodz.mda.tmpl.xtx;

import net.bodz.bas.io.CharOut;

/**
 * XTX Templating
 * 
 * Example for perl-xtx:
 * 
 * <pre>
 * &lt;h1>The example of using \¬, \{, \} escapes. &lt;/h1>
 * &lt;table>
 *     &lt;tr>
 *         &lt;th>Name&lt;/th>
 *         &lt;th>E-mail&lt;/th>
 *     &lt;/tr>
 * ¬ for my $row (@rows) {
 *     &lt;tr>
 *         &lt;td>{ $row.name }&lt;/td>
 *         &lt;td>{ $row.email }&lt;/td>
 *     &lt;/tr>
 * ¬ }
 * &lt;/table>
 * </pre>
 * 
 * will generate the output as:
 * 
 * <pre>
 * &lt;h1>The example of using ¬, {, } escapes. &lt;/h1>
 * &lt;table>
 *     &lt;tr>
 *         &lt;th>Name&lt;/th>
 *         &lt;th>E-mail&lt;/th>
 *     &lt;/tr>
 *     &lt;tr>
 *         &lt;td>Tom&lt;/td>
 *         &lt;td>tom@cat.com&lt;/td>
 *     &lt;/tr>
 * &lt;/table>
 * </pre>
 * 
 * Future extension:
 * 
 * <code>»C¬</code>, where <code>C</code> is name of receive channel.
 */
public interface XtxLang {

    String getName();

    boolean matches(String fileName);

    CodeEmitter newEmitter(CharOut out, String... options);

    /**
     * @return <code>null</code> if result script isn't runnable.
     */
    TemplateScript compile(String path);

}
