package net.bodz.mis.util.codebar;

import java.util.BitSet;
import java.util.IllegalFormatException;

public interface Codebar1D {

    BitSet getBits(String code) throws IllegalFormatException; 
    
}
