package net.bodz.bas.potato.ref;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.t.ref.Ref;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.bas.typer.std.ValidationException;

public interface IRefEntry<T>
        extends Ref<T>, IElement, IQueryable {

    class fn {

        public static <T> void validateAndSet(IRefEntry<T> entry, T value)
                throws ValidationException {

            IValidator<T> validator = entry.query(IValidator.class);
            validator.validate(value);

            entry.set(value);
        }

        public static <T> void parseAndValidateAndSet(IRefEntry<T> entry, String text)
                throws ParseException, ValidationException {

            IParser<T> parser = entry.query(IParser.class);
            T value = parser.parse(text);

            validateAndSet(entry, value);
        }

    }

}
