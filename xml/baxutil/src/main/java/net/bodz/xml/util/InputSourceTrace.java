package net.bodz.xml.util;

public interface InputSourceTrace {

    /**
     * @param inputSource
     *            maybe class:
     *            <ul>
     *            <li>Reader
     *            <li>InputStream
     *            <li>URL
     *            <li>File
     *            <li>String
     *            </ul>
     */
    void setInputSource(Object inputSource);

}
