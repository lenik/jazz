import type { double, int } from "skel01-core/src/lang/basetype";
import CoRelation from "@lily/basic/src/net/bodz/lily/concrete/CoRelation";

import type ArtifactType from "./ArtifactType";
import type StdParameter from "./StdParameter";
import _ArtifactTypeParam_stuff_TypeInfo from "./_ArtifactTypeParam_stuff_TypeInfo";

export class _ArtifactTypeParam_stuff extends CoRelation<int> {

    static _typeInfo: _ArtifactTypeParam_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactTypeParam_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    ival?: int;
    fval?: double;
    sval?: string;

    arttype: ArtifactType;
    arttypeId: int;

    parameter: StdParameter;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactTypeParam_stuff;
