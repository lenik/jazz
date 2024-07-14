import type { BigDecimal, int, short } from "@skeljs/core/src/lang/basetype";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";
import type UomRow from "@lily/basic/src/net/bodz/lily/schema/util/UomRow";

import type Artifact from "./Artifact";
import type ArtifactCategory from "./ArtifactCategory";
import type ArtifactPhase from "./ArtifactPhase";
import type ArtifactType from "./ArtifactType";
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

    type?: ArtifactType;
    typeId?: int;

    proto?: Artifact;
    protoId?: int;

    phase?: ArtifactPhase;
    phaseId?: int;

    uom?: UomRow;
    uomId?: int;

    category?: ArtifactCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Artifact_stuff;
