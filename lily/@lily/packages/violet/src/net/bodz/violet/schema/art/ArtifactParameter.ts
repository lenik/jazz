import ArtifactParameterTypeInfo from "./ArtifactParameterTypeInfo";
import _ArtifactParameter_stuff from "./_ArtifactParameter_stuff";

export class ArtifactParameter extends _ArtifactParameter_stuff<ArtifactParameter> {

    static _typeInfo: ArtifactParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArtifactParameter;
