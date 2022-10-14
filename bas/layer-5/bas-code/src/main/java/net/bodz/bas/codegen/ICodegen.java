package net.bodz.bas.codegen;

import net.bodz.bas.io.ITreeOut;

public interface ICodegen<model_t> {

    void generateSource(ITreeOut out, model_t model);

}
