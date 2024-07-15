import ArtifactVoteTypeInfo from "./ArtifactVoteTypeInfo";
import _ArtifactVote_stuff from "./_ArtifactVote_stuff";

export class ArtifactVote extends _ArtifactVote_stuff<ArtifactVote> {

    static _typeInfo: ArtifactVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactVote;
