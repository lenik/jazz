package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedTokenQueue
        extends AbstractDecorator<ITokenQueue>
        implements
            ITokenQueue {

    private static final long serialVersionUID = 1L;

    public DecoratedTokenQueue(ITokenQueue _orig) {
        super(_orig);
    }

    @Override
    public ITokenQueue clone() {
        return getWrapped().clone();
    }

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
    public int getInt(int index)
            throws ParseException {
        return getWrapped().getInt(index);
    }

    @Override
    public int getInt(int index, int fallback) {
        return getWrapped().getInt(index, fallback);
    }

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
    public <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException {
        return getWrapped().get(enumType, index);
    }

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback) {
        return getWrapped().get(enumType, index, fallback);
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
    public Integer shiftInt()
            throws ParseException {
        return getWrapped().shiftInt();
    }

    @Override
    public int shiftInt(int fallback) {
        return getWrapped().shiftInt(fallback);
    }

    @Override
    public Long shiftLong()
            throws ParseException {
        return getWrapped().shiftLong();
    }

    @Override
    public long shiftLong(int fallback) {
        return getWrapped().shiftLong(fallback);
    }

    @Override
    public String peek() {
        return getWrapped().peek();
    }

    @Override
    public String peekAhead(int offset) {
        return getWrapped().peekAhead(offset);
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
    public Integer peekIntAhead(int offset)
            throws ParseException {
        return getWrapped().peekIntAhead(offset);
    }

    @Override
    public int peekIntAhead(int offset, int fallback) {
        return getWrapped().peekIntAhead(offset, fallback);
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
    public Long peekLongAhead(int offset)
            throws ParseException {
        return getWrapped().peekLongAhead(offset);
    }

    @Override
    public long peekLongAhead(int offset, long fallback) {
        return getWrapped().peekLongAhead(offset, fallback);
    }

    @Override
    public int[] peekInts(int n)
            throws ParseException {
        return getWrapped().peekInts(n);
    }

    @Override
    public int[] peekInts(int n, int fallback) {
        return getWrapped().peekInts(n, fallback);
    }

    @Override
    public long[] peekLongs(int n)
            throws ParseException {
        return getWrapped().peekLongs(n);
    }

    @Override
    public long[] peekLongs(int n, long fallback)
            throws ParseException {
        return getWrapped().peekLongs(n, fallback);
    }

    @Override
    public <E extends Enum<E>> E peek(Class<E> enumType)
            throws ParseException {
        return getWrapped().peek(enumType);
    }

    @Override
    public <E extends Enum<E>> E peekAhead(Class<E> enumType, int offset)
            throws ParseException {
        return getWrapped().peekAhead(enumType, offset);
    }

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
    public <E extends Enum<E>> E peekAhead(Class<E> enumType, int offset, E fallback) {
        return getWrapped().peekAhead(enumType, offset, fallback);
    }

    @Override
    public <E extends Enum<E>> E[] peek(Class<E> enumType, int n, E fallback) {
        return getWrapped().peek(enumType, n, fallback);
    }

    @Override
    public boolean isStopped() {
        return getWrapped().isStopped();
    }

    @Override
    public void stop() {
        getWrapped().stop();
    }

}
