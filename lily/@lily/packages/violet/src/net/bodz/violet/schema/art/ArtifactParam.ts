import ArtifactParamTypeInfo from "./ArtifactParamTypeInfo";
import _ArtifactParam_stuff from "./_ArtifactParam_stuff";

export class ArtifactParam extends _ArtifactParam_stuff {

    static _typeInfo: ArtifactParamTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactParamTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactParam;
