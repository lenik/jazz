import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import type Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import type ArtifactModel from "../art/ArtifactModel";
import type FabStdProcess from "./FabStdProcess";
import _FabStdProcessInput_stuff_TypeInfo from "./_FabStdProcessInput_stuff_TypeInfo";

export class _FabStdProcessInput_stuff extends IdEntity<int> {

    static _typeInfo: _FabStdProcessInput_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdProcessInput_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    quantity: BigDecimal;

    process: FabStdProcess;
    processId: int;

    model?: ArtifactModel;
    modelId?: int;

    artifact?: Artifact;
    artifactId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdProcessInput_stuff;
