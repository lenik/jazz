import ArtifactPhaseTypeInfo from "./ArtifactPhaseTypeInfo";
import _ArtifactPhase_stuff from "./_ArtifactPhase_stuff";

export class ArtifactPhase extends _ArtifactPhase_stuff<ArtifactPhase> {

    static _typeInfo: ArtifactPhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactPhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactPhase;
