import ArtifactBackrefTypeInfo from "./ArtifactBackrefTypeInfo";
import _ArtifactBackref_stuff from "./_ArtifactBackref_stuff";

export class ArtifactBackref extends _ArtifactBackref_stuff {

    static _typeInfo: ArtifactBackrefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactBackrefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactBackref;
