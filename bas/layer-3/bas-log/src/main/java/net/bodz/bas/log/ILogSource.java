package net.bodz.bas.log;

public interface ILogSource {

    void addLogger(ILogger logger);

    void removeLogger(ILogger logger);

}
