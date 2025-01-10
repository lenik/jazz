import type { BigDecimal, int } from "skel01-core/src/lang/basetype";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import type Artifact from "../art/Artifact";
import _SellPrice_stuff_TypeInfo from "./_SellPrice_stuff_TypeInfo";

export class _SellPrice_stuff extends IdEntity<int> {

    static _typeInfo: _SellPrice_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _SellPrice_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    price: BigDecimal;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SellPrice_stuff;
