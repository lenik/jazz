package net.bodz.mda.xjdoc.user;

import java.io.IOException;

/**
 * Dog.
 * 
 * <p lang="zh-cn">
 * 狗。
 * 
 * <p lang="ja">
 * 犬。
 * 
 * <p lang="et">
 * Hundo.
 */
public class Dog
        extends Animal {

    /**
     * Dog is always greeting. Dog will never say good bye to you.
     * 
     * <p lang="zh">
     * 狗總是問候。狗不會對你說再見。
     * 
     * <p lang="ko">
     * 개 항상 인사입니다. 개 당신에게 좋은 인사라고하지 않습니다.
     * 
     * <p lang="ar">
     * الكلب هو دائما تحية. والكلب لا تقول وداعا لك.
     */
    @Override
    public void greet(String message, String bye)
            throws IOException {
        System.out.println("Wow! " + message + "!");
    }

}
