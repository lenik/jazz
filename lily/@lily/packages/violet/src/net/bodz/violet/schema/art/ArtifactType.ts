import ArtifactTypeTypeInfo from "./ArtifactTypeTypeInfo";
import _ArtifactType_stuff from "./_ArtifactType_stuff";

export class ArtifactType extends _ArtifactType_stuff {

    static _typeInfo: ArtifactTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactType;
