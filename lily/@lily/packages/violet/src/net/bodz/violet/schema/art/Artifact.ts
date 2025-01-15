import type { double } from "skel01-core/src/lang/basetype";
import Uom from "lily-basic/src/net/bodz/lily/schema/util/Uom";

import ArtifactTypeInfo from "./ArtifactTypeInfo";
import IArtifactExtras from "./IArtifactExtras";
import _Artifact_stuff from "./_Artifact_stuff";

export class Artifact extends _Artifact_stuff {

    static _typeInfo: ArtifactTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    convMap?: Map<Uom, double>
    extras?: IArtifactExtras

    constructor(o?: any) {
        super(o);
    }
}

export default Artifact;
