import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type Artifact from "../art/Artifact";
import type Region from "./Region";
import _StoreItem_stuff_TypeInfo from "./_StoreItem_stuff_TypeInfo";

export class _StoreItem_stuff extends CoEntity<long> {

    static _typeInfo: _StoreItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _StoreItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    batch?: JsonVariant;
    quantity: BigDecimal;

    region: Region;
    regionId: int;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _StoreItem_stuff;
