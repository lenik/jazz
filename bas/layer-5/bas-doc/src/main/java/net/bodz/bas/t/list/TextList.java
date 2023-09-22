package net.bodz.bas.t.list;

import java.util.List;

public class TextList
        extends AutoList<String> {

    private static final long serialVersionUID = 1L;

    public TextList() {
        super(() -> "");
    }

    public synchronized int getTextLength() {
        int n = 0;
        for (String s : this)
            n += s.length();
        return n;
    }

    class BlockOffset {
        public int blockIndex;
        public int blockOffset;

        public BlockOffset(int blockIndex, int blockOffset) {
            this.blockIndex = blockIndex;
            this.blockOffset = blockOffset;
        }
    }

    public BlockOffset findTextBlock(int offset) {
        if (offset < 0)
            return null;
        List<String> textList = this;
        synchronized (textList) {
            int index = 0;
            int currentOffset = 0;
            for (String block : textList) {
                int blockLen = block.length();
                int blockOffset = offset - currentOffset;
                if (blockOffset <= blockLen)
                    return new BlockOffset(index, blockOffset);
                index++;
                currentOffset += blockLen;
            }
        }
        return null;
    }

    public void insertText(int offset, String s) {
        List<String> textList = this;
        synchronized (textList) {
            BlockOffset bo = findTextBlock(offset);
            if (bo == null) {
                String message = String.format("offset %d, limit %d", offset, getTextLength());
                throw new IndexOutOfBoundsException(message);
            }
            String block = textList.get(bo.blockIndex);
            String l = block.substring(0, bo.blockOffset);
            String r = block.substring(bo.blockOffset);
            String newBlock = l + s + r;
            textList.set(bo.blockIndex, newBlock);
        }
    }

    public void removeText(int start, int end) {
        if (start == end)
            return;
        if (start > end)
            throw new IllegalArgumentException("start > end");

        List<String> textList = this;
        synchronized (textList) {
            BlockOffset b = findTextBlock(start);
            BlockOffset c = findTextBlock(start);
            String bHead = textList.get(b.blockIndex).substring(0, b.blockOffset);
            String cFoot = textList.get(c.blockIndex).substring(c.blockOffset);
            if (b.blockIndex == c.blockIndex) {
                String bCut = bHead + cFoot;
                textList.set(b.blockIndex, bCut);
            } else {
                textList.set(b.blockIndex, bHead);
                textList.set(c.blockIndex, cFoot);
                for (int i = c.blockIndex - 1; i >= c.blockIndex + 1; i--) {
                    textList.remove(i);
                }
            }
        }
    }

    public String getText() {
        List<String> textList = this;
        StringBuilder sb;
        synchronized (textList) {
            switch (textList.size()) {
            case 0:
                return "";
            case 1:
                return textList.get(0);
            default:
                sb = new StringBuilder(getTextLength());
                for (String block : textList)
                    sb.append(block);
            }
        }
        return sb.toString();
    }

    public String compactText() {
        List<String> textList = this;
        synchronized (textList) {
            switch (textList.size()) {
            case 0:
                return "";
            case 1:
                return textList.get(0);
            default:
                String fullText = getText();
                textList.clear();
                textList.add(fullText);
                return fullText;
            }
        }
    }

    public void setText(String text) {
        List<String> textList = this;
        synchronized (textList) {
            textList.clear();
            textList.add(text);
        }
    }

}
