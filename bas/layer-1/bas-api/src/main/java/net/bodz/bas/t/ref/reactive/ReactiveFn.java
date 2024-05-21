package net.bodz.bas.t.ref.reactive;

public class ReactiveFn {

    public static <T> Ref<T> ref() {
        return new Ref<T>(null);
    }

    public static <T> Ref<T> ref(T value) {
        return new Ref<T>(value);
    }

}
