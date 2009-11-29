package net.bodz.bas.semantictype.common;

import java.util.Arrays;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.aspect.typeinfo.SemanticTypeParameter;
import net.bodz.bas.aspect.typeinfo.ValidateException;
import net.bodz.bas.semantictype.plain.PlainInteger;

public class AlphebetStringType
        extends SemanticType<String> {

    private final char[] alphabet;
    private final int maxLength;

    public AlphebetStringType() {
        this(null, -1);
    }

    /**
     * @param maxLength
     *            -1 if not limited.
     */
    public AlphebetStringType(String alphabet, int maxLength) {
        super(String.class, "Character sequence", "STRING", null, //
                new SemanticTypeParameter<Integer>("max-length", PlainInteger.getInstance(), maxLength));

        // if (maxLength < 0)
        // throw new IllegalArgumentException("maxLength must be positive: " + maxLength);
        this.maxLength = maxLength;

        this.alphabet = alphabet.toCharArray();
        Arrays.sort(this.alphabet);
    }

    @Override
    public String parse(String text, Object parseContext)
            throws ParseException {
        return text;
    }

    @Override
    public void validate(Object object, Object validateContext)
            throws ValidateException {
        String str = (String) object;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int ord = Arrays.binarySearch(alphabet, c);
            if (ord < 0)
                throw new ValidateException("Illegal character in string: char code=" + (int) c);
        }
    }

}
