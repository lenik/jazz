package net.bodz.bas.l10n.zh.conv;

import java.io.IOException;

import net.bodz.bas.err.PackageError;

public class OpenCC
        implements IChineseConverter {

    private OpenCCCore stCore;
    private OpenCCCore tsCore;

    /**
     * @throws PackageError
     */
    public OpenCC() {
        try {
            stCore = new OpenCCCore("simp_to_trad_characters.ocd", "simp_to_trad_phrases.ocd");
            tsCore = new OpenCCCore("trad_to_simp_characters.ocd", "trad_to_simp_phrases.ocd");
        } catch (IOException e) {
            throw new PackageError(e.getMessage(), e);
        }
    }

    @Override
    public String toSimplified(String str) {
        return tsCore.convert(str);
    }

    @Override
    public String toTraditional(String str) {
        return stCore.convert(str);
    }

}
