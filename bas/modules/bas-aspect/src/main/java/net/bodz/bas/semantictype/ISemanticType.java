package net.bodz.bas.semantictype;

import net.bodz.bas.aspect.typeinfo.Formatter;
import net.bodz.bas.aspect.typeinfo.InstanceStore;
import net.bodz.bas.aspect.typeinfo.Parser;
import net.bodz.bas.aspect.typeinfo.SampleGenerator;
import net.bodz.bas.aspect.typeinfo.Validator;

public interface ISemanticType<T> {

    /**
     * Display name.
     */
    String getName();

    String getDescription();

    Class<? extends T> getInstanceClass();

    String getSyntax();

    Parser getParser();

    Formatter getFormatter();

    Validator getValidator();

    MeasureUnit getMeasureUnit();

    int getParameterCount();

    Parameter<?> getParameter(int parameterIndex);

    Parameter<?> getParameter(String parameterName);

    SampleGenerator<T> getSampleGenerator();

    InstanceStore<T> getInstanceStore();

    class Parameter<PT> {

        private final String name;
        private final ISemanticType<PT> type;
        private final PT value;

        public Parameter(String name, ISemanticType<PT> type, PT value) {
            if (name == null)
                throw new NullPointerException("name");
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public ISemanticType<PT> getType() {
            return type;
        }

        public PT getValue() {
            return value;
        }

    }

}
