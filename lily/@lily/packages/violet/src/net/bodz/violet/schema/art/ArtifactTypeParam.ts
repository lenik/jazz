import ArtifactTypeParamTypeInfo from "./ArtifactTypeParamTypeInfo";
import _ArtifactTypeParam_stuff from "./_ArtifactTypeParam_stuff";

export class ArtifactTypeParam extends _ArtifactTypeParam_stuff {

    static _typeInfo: ArtifactTypeParamTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactTypeParamTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactTypeParam;
