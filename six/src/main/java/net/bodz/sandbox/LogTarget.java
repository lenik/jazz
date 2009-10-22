package net.bodz.sandbox;

public interface LogTarget {

    void log(String message);

    LogTarget NULL = new LogTarget() {
                       @Override
                       public void log(String message) {
                       }
                   };

}
