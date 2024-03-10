import type { BigDecimal, int, short } from "@skeljs/core/src/lang/basetype";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";
import type Uom from "@lily/basic/src/net/bodz/lily/schema/util/Uom";

import type Artifact from "./Artifact";
import type ArtifactCategory from "./ArtifactCategory";
import type ArtifactPhase from "./ArtifactPhase";
import _Artifact_stuff_TypeInfo from "./_Artifact_stuff_TypeInfo";

export class _Artifact_stuff extends CoImaged<int> {

    static _typeInfo: _Artifact_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Artifact_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    skuCode?: string;
    barCode?: string;
    rfidCode?: string;
    modelName?: string;
    finish: short;
    price?: BigDecimal;

    proto?: Artifact;
    protoId?: int;

    phase?: ArtifactPhase;
    phaseId?: int;

    uom?: Uom;
    uomId?: int;

    category?: ArtifactCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Artifact_stuff;
