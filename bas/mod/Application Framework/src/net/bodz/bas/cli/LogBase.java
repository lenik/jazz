package net.bodz.bas.cli;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.types.TypeParsers.CharOutParser;

public class LogBase {

    @Option(hidden = true, parser = CharOutParser.class)
    protected CharOut _stdout        = CharOuts.stdout;

    @Option(hidden = true, parser = CharOutParser.class)
    protected CharOut _logout        = CharOuts.stderr;

    @Option(hidden = true)
    protected String  _logPrefix     = getClass().getSimpleName();

    @Option(hidden = true)
    protected boolean _logWithPrefix = true;

    protected String  _logDelim      = " ";

    protected int     _verbose       = 1;

    @Option(alias = "v", doc = "repeat to get more info")
    protected void _verbose() {
        _verbose++;
    }

    @Option(alias = "q", doc = "repeat to get less info")
    protected void _quiet() {
        _verbose--;
    }

    protected final void _p(Object first, Object... more) {
        _stdout.print(first);
        if (more.length > 0)
            for (Object m : more)
                _stdout.print(m);
    }

    protected final void _P(Object first, Object... more) {
        _p(first, more);
        _stdout.println();
    }

    protected final void _pf(String format, Object... args) {
        _stdout.printf(format, args);
    }

    protected final void _PF(String format, Object... args) {
        _stdout.printf(format, args);
        _stdout.println();
    }

    protected final void _log(int n, Object first, Object... more) {
        if (_verbose < n)
            return;
        if (_logWithPrefix) {
            _logout.print('[');
            _logout.print(_logPrefix);
            _logout.print(']');
            _logout.print(_logDelim);
        }
        _logout.print(first);
        for (int i = 0; i < more.length; i++) {
            _logout.print(_logDelim);
            _logout.print(more[i]);
        }
        _logout.println();
    }

    // private static int _conwidth = 70;
    private static int _prevsiglen = 0;

    protected final void _sig(int n, Object first, Object... more) {
        if (_verbose < n)
            return;
        int l = 0;
        int dl = _logDelim.length();
        String s;
        if (_logWithPrefix) {
            _logout.print('[');
            _logout.print(_logPrefix);
            _logout.print(']');
            _logout.print(_logDelim);
            l += _logPrefix.length() + dl + 2;
        }
        _logout.print(s = String.valueOf(first));
        l += s.length();
        if (more.length > 0)
            for (int i = 0; i < more.length; i++) {
                _logout.print(_logDelim);
                _logout.print(s = String.valueOf(more[i]));
                l += dl + s.length();
            }
        int len = l;
        while (l++ < _prevsiglen /* min(_prev, _conwidth) */)
            _logout.print(' ');
        _prevsiglen = len;
        _logout.print('\r');
    }

    protected final void _log1(Object first, Object... more) {
        _log(1, first, more);
    }

    protected final void _log2(Object first, Object... more) {
        _log(2, first, more);
    }

    protected final void _log3(Object first, Object... more) {
        _log(3, first, more);
    }

    protected final void _sig1(Object first, Object... more) {
        _sig(1, first, more);
    }

    protected final void _sig2(Object first, Object... more) {
        _sig(2, first, more);
    }

    protected final void _sig3(Object first, Object... more) {
        _sig(3, first, more);
    }

}
