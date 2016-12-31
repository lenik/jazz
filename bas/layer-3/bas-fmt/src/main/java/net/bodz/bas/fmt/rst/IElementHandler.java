package net.bodz.bas.fmt.rst;

import java.lang.reflect.Array;
import java.util.Arrays;

import net.bodz.bas.c.enm.Enums;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

/**
 * @see RstElement
 */
public interface IElementHandler {

    IDataCodec codec = new DataCodec();

    /**
     * @return <code>true</code> if this attribute is handled, and should be dropped away.
     *         <code>false</code> if this attribute should be included in the enclosing element to
     *         be processed in the future.
     */
    boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException;

    /**
     * Called when the child element begins.
     */
    IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException;

    /**
     * Post-process child element.
     * 
     * @param element
     *            The child element, contains unprocessed attributes.
     * @return <code>true</code> if this (child-) element is handled, and should be dropped away.
     *         <code>false</code> if this (child-) element should be included in the enclosing
     *         element to be processed in the future.
     */
    boolean endChild(IRstElement element)
            throws ElementHandlerException;

    void complete(IRstElement element)
            throws ElementHandlerException;

    class Parser {

        Class<?> valueType;
        Object prevalue;

        TypeEnum typeEnum;
        boolean isFinal;
        int arrayLen;

        public Parser(Class<?> valueType, Object prevalue)
                throws ElementHandlerException {
            this.valueType = valueType;

            this.typeEnum = TypeEnum.forClass(valueType);
            if (typeEnum == null)
                throw new ElementHandlerException("Type isn't supported: " + valueType);

            this.prevalue = prevalue;
            this.isFinal = prevalue != null;

            arrayLen = 0;
            if (valueType.isArray())
                arrayLen = Array.getLength(prevalue);
        }

        public Object parse(String name, String data)
                throws ParseException, ElementHandlerException {
            Object value = prevalue;

            switch (typeEnum) {
            case BYTE:
                value = codec.parseByte(name, data);
                break;

            case SHORT:
                value = codec.parseShort(name, data);
                break;

            case INT:
                value = codec.parseInt(name, data);
                break;

            case LONG:
                value = codec.parseLong(name, data);
                break;

            case FLOAT:
                value = codec.parseFloat(name, data);
                break;

            case DOUBLE:
                value = codec.parseDouble(name, data);
                break;

            case BOOL:
                value = codec.parseBool(name, data);
                break;

            case CHAR:
                value = codec.parseChar(name, data);
                break;

            case STRING:
                value = codec.parseString(name, data);
                break;

            case CLASS:
                String typeName = codec.parseString(name, data);
                try {
                    value = Class.forName(typeName);
                } catch (ClassNotFoundException e) {
                    throw new ParseException("undefined class: " + typeName, e);
                }
                break;

            case ENUM:
                value = Enums.getEnum(valueType, data);
                break;

            case BYTE_ARRAY:
                if (isFinal) {
                    byte[] v = (byte[]) value;
                    int n = codec.parseBytes(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, (byte) 0);
                } else {
                    value = codec.parseBytes(name, data);
                }
                break;

            case SHORT_ARRAY:
                if (isFinal) {
                    short[] v = (short[]) value;
                    int n = codec.parseShorts(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, (short) 0);
                } else {
                    value = codec.parseShorts(name, data);
                }
                break;

            case INT_ARRAY:
                if (isFinal) {
                    int[] v = (int[]) value;
                    int n = codec.parseInts(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, (int) 0);
                } else {
                    value = codec.parseInts(name, data);
                }
                break;

            case LONG_ARRAY:
                if (isFinal) {
                    long[] v = (long[]) value;
                    int n = codec.parseLongs(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, 0L);
                } else {
                    value = codec.parseLongs(name, data);
                }
                break;

            case FLOAT_ARRAY:
                if (isFinal) {
                    float[] v = (float[]) value;
                    int n = codec.parseFloats(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, 0.0f);
                } else {
                    value = codec.parseFloats(name, data);
                }
                break;

            case DOUBLE_ARRAY:
                if (isFinal) {
                    double[] v = (double[]) value;
                    int n = codec.parseDoubles(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, Double.NaN);
                } else {
                    value = codec.parseDoubles(name, data);
                }
                break;

            case BOOL_ARRAY:
                if (isFinal) {
                    boolean[] v = (boolean[]) value;
                    int n = codec.parseBools(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, false);
                } else {
                    value = codec.parseBools(name, data);
                }
                break;

            case CHAR_ARRAY:
                if (isFinal) {
                    char[] v = (char[]) value;
                    int n = codec.parseChars(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, '\0');
                } else {
                    value = codec.parseChars(name, data);
                }
                break;

            case STRING_ARRAY:
                if (isFinal) {
                    String[] v = (String[]) value;
                    int n = codec.parseStrings(name, data, v, 0, arrayLen);
                    Arrays.fill(v, n, arrayLen, null);
                } else {
                    value = codec.parseStrings(name, data);
                }
                break;

            case CLASS_ARRAY:
                String[] typeNames = codec.parseStrings(name, data);
                if (isFinal) {
                    Class<?>[] v = (Class<?>[]) value;
                    int n = Math.min(typeNames.length, v.length);
                    for (int i = 0; i < n; i++) {
                        try {
                            v[i] = Class.forName(typeNames[i]);
                        } catch (ClassNotFoundException e) {
                            throw new ParseException("undefined class: " + typeNames[i], e);
                        }
                    }
                } else {
                    Class<?>[] types = new Class<?>[typeNames.length];
                    for (int i = 0; i < types.length; i++)
                        try {
                            types[i] = Class.forName(typeNames[i]);
                        } catch (ClassNotFoundException e) {
                            throw new ParseException("undefined class: " + typeNames[i], e);
                        }
                    value = types;
                }
                break;

            // case ENUM_ARRAY:
            // if (isFinalField) {
            // Enum<?>[] v = (Enum<?>[]) value;
            // int n = codec.parseEnums(fieldType, attributeName, attributeData);
            // Arrays.fill(v, n, arrayLen, '\0');
            // } else {
            // value = codec.parseEnums(attributeName, attributeData);
            // }
            // break;

            case OBJECT:
                IParser<Object> parser = Typers.getTyper(valueType, IParser.class);
                if (parser == null)
                    throw new ElementHandlerException("Don't know how to parse: " + valueType);
                value = parser.parse(data);
                break;

            default:
                throw new ElementHandlerException("field type isn't supported: " + valueType);
            }
            return value;
        }

    }

}
