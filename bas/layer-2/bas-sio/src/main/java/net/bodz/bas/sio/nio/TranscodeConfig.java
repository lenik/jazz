package net.bodz.bas.sio.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

class TranscodeConfig {

    static int chunkSize = 1000;

    static CharBuffer allocateEncodeInputBuffer() {
        return CharBuffer.allocate(chunkSize);
    }

    static ByteBuffer allocateDecodeInputBuffer() {
        return ByteBuffer.allocate(chunkSize);
    }

    static ByteBuffer allocateOutputBuffer(CharsetEncoder encoder) {
        float averageBytesPerChar = encoder.averageBytesPerChar();
        int byteCount = (int) (chunkSize * averageBytesPerChar);
        byteCount += 3;
        return ByteBuffer.allocate(byteCount);
    }

    static CharBuffer allocateOutputBuffer(CharsetDecoder decoder) {
        float averageCharsPerByte = decoder.averageCharsPerByte();
        int charCount = (int) (chunkSize * averageCharsPerByte);
        return CharBuffer.allocate(charCount);
    }

}
