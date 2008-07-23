package net.bodz.bas.text.encodings;

class Lookups {
	
	public static final byte[] c2n = new byte[] 
		{//	 0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,33,-1,-1,-1,-1,-1,-1,-1,-1,-1,45,-1,43,-1,92,		// +-, /->\, !->!
			0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1,-1,62,-1,60,63,		// <>, ?->?
			-1,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,		// A-O
			25,26,27,28,29,30,31,32,33,34,35,93,47,91,-1,-1, 		// P-Z, [], \->/
			-1,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,		// a-o
			25,26,27,28,29,30,31,32,33,34,35,-1,-1,-1,-1,-1, 		// p-z
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
		}; 

	private static final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final char[]  n2cu     = alphabet.toCharArray();
    public static final char[]  n2cl     = alphabet.toLowerCase().toCharArray();
	
}
