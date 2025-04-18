package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedTokenQueue
        extends AbstractDecorator<ITokenQueue>
        implements ITokenQueue {

    private static final long serialVersionUID = 1L;

    public DecoratedTokenQueue(ITokenQueue _orig) {
        super(_orig);
    }

    @Override
    public abstract ITokenQueue clone();

    @Override
    public String getMethod() {
        return getWrapped().getMethod();
    }

    @Override
    public String getScheme() {
        return getWrapped().getScheme();
    }

    @Override
    public String getHost() {
        return getWrapped().getHost();
    }

    @Override
    public int getPort() {
        return getWrapped().getPort();
    }

    @Override
    public String getUserInfo() {
        return getWrapped().getUserInfo();
    }

    @Override
    public String getQuery() {
        return getWrapped().getQuery();
    }

    @Override
    public String getFragment() {
        return getWrapped().getFragment();
    }

    @Override
    public int size() {
        return getWrapped().size();
    }

    @Override
    public boolean isEmpty() {
        return getWrapped().isEmpty();
    }

    @Override
    public String get(int index) {
        return getWrapped().get(index);
    }

    @Override
    public int available() {
        return getWrapped().available();
    }

    @Override
    public String getRemainingPath() {
        return getWrapped().getRemainingPath();
    }

    @Override
    public boolean isEntered() {
        return getWrapped().isEntered();
    }

    @Override
    public boolean isDone() {
        return getWrapped().isDone();
    }

    @Override
    public boolean isStopped() {
        return getWrapped().isStopped();
    }

    @Override
    public void stop() {
        getWrapped().stop();
    }

    @Override
    public void skip(int n) {
        getWrapped().skip(n);
    }

    @Override
    public String[] shift(int n) {
        return getWrapped().shift(n);
    }

    @Override
    public String[] shiftAll() {
        return getWrapped().shiftAll();
    }

    @Override
    public String shift() {
        return getWrapped().shift();
    }

    @Override
    public String peek() {
        return getWrapped().peek();
    }

    @Override
    public String peekAt(int offset) {
        return getWrapped().peekAt(offset);
    }

    // ----------------------------------------- GROUP: BYTE -----------------------------------------

    @Override
    public byte getByte(int index)
            throws ParseException {
        return getWrapped().getByte(index);
    }

    @Override
    public byte getByte(int index, byte fallback) {
        return getWrapped().getByte(index, fallback);
    }

    @Override
    public Byte shiftByte()
            throws ParseException {
        return getWrapped().shiftByte();
    }

    @Override
    public byte shiftByte(byte fallback) {
        return getWrapped().shiftByte(fallback);
    }

    @Override
    public Byte peekByte()
            throws ParseException {
        return getWrapped().peekByte();
    }

    @Override
    public byte peekByte(byte fallback) {
        return getWrapped().peekByte(fallback);
    }

    @Override
    public Byte peekByteAt(int offset)
            throws ParseException {
        return getWrapped().peekByteAt(offset);
    }

    @Override
    public byte peekByteAt(int offset, byte fallback) {
        return getWrapped().peekByteAt(offset, fallback);
    }

    @NotNull
    @Override
    public byte[] peekBytes(int n)
            throws ParseException {
        return getWrapped().peekBytes(n);
    }

    @NotNull
    @Override
    public byte[] peekBytes(int n, byte fallback)
            throws ParseException {
        return getWrapped().peekBytes(n, fallback);
    }

    // ----------------------------------------- GROUP: SHORT -----------------------------------------

    @Override
    public short getShort(int index)
            throws ParseException {
        return getWrapped().getShort(index);
    }

    @Override
    public short getShort(int index, short fallback) {
        return getWrapped().getShort(index, fallback);
    }

    @Override
    public Short shiftShort()
            throws ParseException {
        return getWrapped().shiftShort();
    }

    @Override
    public short shiftShort(short fallback) {
        return getWrapped().shiftShort(fallback);
    }

    @Override
    public Short peekShort()
            throws ParseException {
        return getWrapped().peekShort();
    }

    @Override
    public short peekShort(short fallback) {
        return getWrapped().peekShort(fallback);
    }

    @Override
    public Short peekShortAt(int offset)
            throws ParseException {
        return getWrapped().peekShortAt(offset);
    }

    @Override
    public short peekShortAt(int offset, short fallback) {
        return getWrapped().peekShortAt(offset, fallback);
    }

    @NotNull
    @Override
    public short[] peekShorts(int n)
            throws ParseException {
        return getWrapped().peekShorts(n);
    }

    @NotNull
    @Override
    public short[] peekShorts(int n, short fallback)
            throws ParseException {
        return getWrapped().peekShorts(n, fallback);
    }

    // ----------------------------------------- GROUP: INT -----------------------------------------

    @Override
    public int getInt(int index)
            throws ParseException {
        return getWrapped().getInt(index);
    }

    @Override
    public int getInt(int index, int fallback) {
        return getWrapped().getInt(index, fallback);
    }

    @Override
    public Integer shiftInt()
            throws ParseException {
        return getWrapped().shiftInt();
    }

    @Override
    public int shiftInt(int fallback) {
        return getWrapped().shiftInt(fallback);
    }

    @Override
    public Integer peekInt()
            throws ParseException {
        return getWrapped().peekInt();
    }

    @Override
    public int peekInt(int fallback) {
        return getWrapped().peekInt(fallback);
    }

    @Override
    public Integer peekIntAt(int offset)
            throws ParseException {
        return getWrapped().peekIntAt(offset);
    }

    @Override
    public int peekIntAt(int offset, int fallback) {
        return getWrapped().peekIntAt(offset, fallback);
    }

    @NotNull
    @Override
    public int[] peekInts(int n)
            throws ParseException {
        return getWrapped().peekInts(n);
    }

    @NotNull
    @Override
    public int[] peekInts(int n, int fallback) {
        return getWrapped().peekInts(n, fallback);
    }

    // ----------------------------------------- GROUP: LONG -----------------------------------------

    @Override
    public long getLong(int index)
            throws ParseException {
        return getWrapped().getLong(index);
    }

    @Override
    public long getLong(int index, long fallback) {
        return getWrapped().getLong(index, fallback);
    }

    @Override
    public Long shiftLong()
            throws ParseException {
        return getWrapped().shiftLong();
    }

    @Override
    public long shiftLong(long fallback) {
        return getWrapped().shiftLong(fallback);
    }

    @Override
    public Long peekLong()
            throws ParseException {
        return getWrapped().peekLong();
    }

    @Override
    public long peekLong(long fallback) {
        return getWrapped().peekLong(fallback);
    }

    @Override
    public Long peekLongAt(int offset)
            throws ParseException {
        return getWrapped().peekLongAt(offset);
    }

    @Override
    public long peekLongAt(int offset, long fallback) {
        return getWrapped().peekLongAt(offset, fallback);
    }

    @NotNull
    @Override
    public long[] peekLongs(int n)
            throws ParseException {
        return getWrapped().peekLongs(n);
    }

    @NotNull
    @Override
    public long[] peekLongs(int n, long fallback)
            throws ParseException {
        return getWrapped().peekLongs(n, fallback);
    }

    // ----------------------------------------- GROUP: FLOAT -----------------------------------------

    @Override
    public float getFloat(int index)
            throws ParseException {
        return getWrapped().getFloat(index);
    }

    @Override
    public float getFloat(int index, float fallback) {
        return getWrapped().getFloat(index, fallback);
    }

    @Override
    public Float shiftFloat()
            throws ParseException {
        return getWrapped().shiftFloat();
    }

    @Override
    public float shiftFloat(float fallback) {
        return getWrapped().shiftFloat(fallback);
    }

    @Override
    public Float peekFloat()
            throws ParseException {
        return getWrapped().peekFloat();
    }

    @Override
    public float peekFloat(float fallback) {
        return getWrapped().peekFloat(fallback);
    }

    @Override
    public Float peekFloatAt(int offset)
            throws ParseException {
        return getWrapped().peekFloatAt(offset);
    }

    @Override
    public float peekFloatAt(int offset, float fallback) {
        return getWrapped().peekFloatAt(offset, fallback);
    }

    @NotNull
    @Override
    public float[] peekFloats(int n)
            throws ParseException {
        return getWrapped().peekFloats(n);
    }

    @NotNull
    @Override
    public float[] peekFloats(int n, float fallback)
            throws ParseException {
        return getWrapped().peekFloats(n, fallback);
    }

    // ----------------------------------------- GROUP: DOUBLE -----------------------------------------

    @Override
    public double getDouble(int index)
            throws ParseException {
        return getWrapped().getDouble(index);
    }

    @Override
    public double getDouble(int index, double fallback) {
        return getWrapped().getDouble(index, fallback);
    }

    @Override
    public Double shiftDouble()
            throws ParseException {
        return getWrapped().shiftDouble();
    }

    @Override
    public double shiftDouble(double fallback) {
        return getWrapped().shiftDouble(fallback);
    }

    @Override
    public Double peekDouble()
            throws ParseException {
        return getWrapped().peekDouble();
    }

    @Override
    public double peekDouble(double fallback) {
        return getWrapped().peekDouble(fallback);
    }

    @Override
    public Double peekDoubleAt(int offset)
            throws ParseException {
        return getWrapped().peekDoubleAt(offset);
    }

    @Override
    public double peekDoubleAt(int offset, double fallback) {
        return getWrapped().peekDoubleAt(offset, fallback);
    }

    @NotNull
    @Override
    public double[] peekDoubles(int n)
            throws ParseException {
        return getWrapped().peekDoubles(n);
    }

    @NotNull
    @Override
    public double[] peekDoubles(int n, double fallback)
            throws ParseException {
        return getWrapped().peekDoubles(n, fallback);
    }

    // ----------------------------------------- GROUP: BOOLEAN -----------------------------------------

    @Override
    public boolean getBoolean(int index)
            throws ParseException {
        return getWrapped().getBoolean(index);
    }

    @Override
    public boolean getBoolean(int index, boolean fallback) {
        return getWrapped().getBoolean(index, fallback);
    }

    @Override
    public Boolean shiftBoolean()
            throws ParseException {
        return getWrapped().shiftBoolean();
    }

    @Override
    public boolean shiftBoolean(boolean fallback) {
        return getWrapped().shiftBoolean(fallback);
    }

    @Override
    public Boolean peekBoolean()
            throws ParseException {
        return getWrapped().peekBoolean();
    }

    @Override
    public boolean peekBoolean(boolean fallback) {
        return getWrapped().peekBoolean(fallback);
    }

    @Override
    public Boolean peekBooleanAt(int offset)
            throws ParseException {
        return getWrapped().peekBooleanAt(offset);
    }

    @Override
    public boolean peekBooleanAt(int offset, boolean fallback) {
        return getWrapped().peekBooleanAt(offset, fallback);
    }

    @NotNull
    @Override
    public boolean[] peekBooleans(int n)
            throws ParseException {
        return getWrapped().peekBooleans(n);
    }

    @NotNull
    @Override
    public boolean[] peekBooleans(int n, boolean fallback)
            throws ParseException {
        return getWrapped().peekBooleans(n, fallback);
    }

    // ----------------------------------------- GROUP: ENUM -----------------------------------------

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException {
        return getWrapped().get(enumType, index);
    }

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback) {
        return getWrapped().get(enumType, index, fallback);
    }

    @Override
    public <E extends Enum<E>> E peek(Class<E> enumType)
            throws ParseException {
        return getWrapped().peek(enumType);
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset)
            throws ParseException {
        return getWrapped().peekAt(enumType, offset);
    }

    @NotNull
    @Override
    public <E extends Enum<E>> E[] peek(Class<E> enumType, int n)
            throws ParseException {
        return getWrapped().peek(enumType, n);
    }

    @Override
    public <E extends Enum<E>> E peek(Class<E> enumType, E fallback) {
        return getWrapped().peek(enumType, fallback);
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset, E fallback) {
        return getWrapped().peekAt(enumType, offset, fallback);
    }

    @NotNull
    @Override
    public <E extends Enum<E>> E[] peek(Class<E> enumType, int n, E fallback) {
        return getWrapped().peek(enumType, n, fallback);
    }

}
