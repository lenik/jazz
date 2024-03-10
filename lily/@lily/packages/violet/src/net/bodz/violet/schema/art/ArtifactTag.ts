import ArtifactTagTypeInfo from "./ArtifactTagTypeInfo";
import _ArtifactTag_stuff from "./_ArtifactTag_stuff";

export class ArtifactTag extends _ArtifactTag_stuff<ArtifactTag> {

    static _typeInfo: ArtifactTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArtifactTag;
