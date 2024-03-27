import ArtifactDocTypeInfo from "./ArtifactDocTypeInfo";
import _ArtifactDoc_stuff from "./_ArtifactDoc_stuff";

export class ArtifactDoc extends _ArtifactDoc_stuff {

    static _typeInfo: ArtifactDocTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactDocTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactDoc;
