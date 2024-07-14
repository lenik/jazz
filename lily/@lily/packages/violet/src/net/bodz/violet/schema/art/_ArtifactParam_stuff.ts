import type { double, int } from "@skeljs/core/src/lang/basetype";
import CoRelation from "@lily/basic/src/net/bodz/lily/concrete/CoRelation";

import type Artifact from "./Artifact";
import type StdParameter from "./StdParameter";
import _ArtifactParam_stuff_TypeInfo from "./_ArtifactParam_stuff_TypeInfo";

export class _ArtifactParam_stuff extends CoRelation<int> {

    static _typeInfo: _ArtifactParam_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactParam_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    ival?: int;
    fval?: double;
    sval?: string;

    artifact: Artifact;
    artifactId: int;

    parameter: StdParameter;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactParam_stuff;
