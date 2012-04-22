package net.bodz.art.obfuz.math;

public interface OctsDomain {

    byte[] zero();

    byte[] zerol();

    byte[] zeror();

    byte[] ident();

    byte[] identl();

    byte[] ridentr();

    byte[] add(byte[] x, byte[] y);

    byte[] mul(byte[] x, byte[] y);

    /** additive inverse */
    byte[] neg(byte[] x);

    /** multiplicative inverse */
    byte[] inv(byte[] x);

    boolean equals(byte[] x, byte[] y);

    boolean lessThan(byte[] x, byte[] y);

}
