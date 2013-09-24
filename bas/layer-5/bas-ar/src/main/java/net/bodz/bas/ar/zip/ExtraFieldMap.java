package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.BByteIOS;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.data.DataInImplLE;

public class ExtraFieldMap
        extends LinkedHashMap<Short, ExtraField> {

    private static final long serialVersionUID = 1L;

    static Map<Integer, ExtraFieldMetadata> tagMetaMap;

    static {
        tagMetaMap = new HashMap<>();

        for (ExtraField inst : ServiceLoader.load(ExtraField.class)) {
            Class<? extends ExtraField> clazz = inst.getClass();
            ExtraFieldType extraFieldType = clazz.getAnnotation(ExtraFieldType.class);
            if (extraFieldType == null)
                throw new IllegalUsageException("Missing @ExtraFieldType on " + clazz);

            int id = extraFieldType.id() & 0xffff;

            ExtraFieldMetadata metadata = tagMetaMap.get(id);
            if (metadata != null)
                throw new DuplicatedKeyException("Extra field id in use: " + id);

            metadata = new ExtraFieldMetadata();
            metadata.type = clazz;
            metadata.bigEndian = extraFieldType.bigEndian();
            metadata.sizeTotal = extraFieldType.sizeTotal();

            tagMetaMap.put(id, metadata);
        }
    }

    public static ExtraFieldMap parse(byte[] extras)
            throws IOException {
        ExtraFieldMap map = new ExtraFieldMap();

        IByteIOS ios = new BByteIOS(extras);
        DataInImplLE le = DataInImplLE.from(ios);

        while (true) {
            int low = le.read();
            int high = le.read();
            if (high == -1 || low == -1)
                break;

            int tag = (high << 8) | low;
            ExtraFieldMetadata metadata = tagMetaMap.get(tag);
            if (metadata == null)
                // warning...
                continue;

            int size = le.readWord() & 0xffff;
            int remaining = metadata.sizeTotal ? size - 4 : size;
            long nextptr = ios.tell() + remaining;

            ExtraField field = metadata.newInstance();
            field.tag = (short) tag;
            field.size = (short) size;

            field._readObject(le);

            map.put((short) tag, field);

            if (ios.tell() != nextptr)
                ios.seek(nextptr);
        }

        return map;
    }
}
