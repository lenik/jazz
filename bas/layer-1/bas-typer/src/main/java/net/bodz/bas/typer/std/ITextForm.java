package net.bodz.bas.typer.std;

public interface ITextForm<T>
        extends IParser<T>, IFormatter<T>, IStdTyper {

    int typerIndex = 0x11c0ada2; // ITextForm

}
