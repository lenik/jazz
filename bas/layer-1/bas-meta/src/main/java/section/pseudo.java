package section;

import java.lang.annotation.Annotation;

public @interface pseudo {

    Dummy _void_ = new Dummy();

}

@SuppressWarnings("all")
class Dummy
        implements algorithm, friend, generated, iface, impl, internal, main, obj, part {

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

}
