package net.bodz.mda.xjdoc.user;

import java.io.IOException;

/**
 * General animal type.
 * 
 * <p lang="zh-cn">
 * 一般动物类型。
 * 
 * <p lang="et">
 * Ĝenerala besto tipo.
 */
public class Animal {

    /**
     * Get the general color of the \ skin.
     * <p lang="zh-cn">
     * 获取动物皮肤的一般颜色。
     * <p lang="ja">
     * 膚の一般的な色を取得します。
     * 
     * @return Descriptive color name.
     *         <p lang="zh-cn">
     *         描述性的颜色名称。
     *         <p lang="ja">
     *         わかりやすい色名。
     * @throws UnsupportedOperationException
     *             If the color is unknown.
     *             <p lang="zh-cn">
     *             当颜色不可用时。
     *             <p lang="ja">
     *             色は不明である場合。
     *             <p lang="et">
     *             Se la koloro estas nekonata.
     */
    public String getColor() {
        return null;
    }

    /**
     * Send greeting messages and bye.
     * 
     * This method don't return anything.
     * <p>
     * For example, hi baby, good bye!
     * 
     * <p lang="zh-cn">
     * 打声招呼就走。
     * 
     * 本方法不返回任何值。
     * <p>
     * 比如说，嘿宝贝儿，拜拜了！
     * 
     * <p lang="ja">
     * グリチングて、バイバイを言います。
     * 
     * このメソッドは何も返しません。
     * <p>
     * たとえば、赤ちゃんこんにちは、さようなら！
     * 
     * @param message
     *            The greeting message to send. Should not be <code>null</code>.
     *            <p lang="zh-cn">
     *            寒暄语。
     *            <p lang="ja">
     *            グリチング言葉。
     * @param bye
     *            The leaving message to send.
     *            <p lang="zh-cn">
     *            离别语。
     *            <p lang="ja">
     *            バイバイ言葉。
     * @throws ReflectiveOperationException
     * @exception IOException1
     *                If any I/O exception occurred.
     *                <p lang="zh-cn">
     *                当发生输入输出异常时。
     *                <p lang="ja">
     *                ハードウェアエラー。
     */
    public void greet(String message, String bye)
            throws IOException {
        System.out.println("Hello, " + message);
        System.out.println("Bye bye " + bye);
    }

}
