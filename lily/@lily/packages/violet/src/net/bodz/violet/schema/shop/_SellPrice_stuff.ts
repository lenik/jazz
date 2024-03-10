import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type Artifact from "../art/Artifact";
import _SellPrice_stuff_TypeInfo from "./_SellPrice_stuff_TypeInfo";

export class _SellPrice_stuff extends CoEntity<int> {

    static _typeInfo: _SellPrice_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _SellPrice_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: int;
    code?: string;
    price: BigDecimal;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SellPrice_stuff;
