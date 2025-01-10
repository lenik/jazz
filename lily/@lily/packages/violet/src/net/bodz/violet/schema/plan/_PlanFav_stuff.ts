import type { long } from "skel01-core/src/lang/basetype";
import FavRecord from "@lily/basic/src/net/bodz/lily/concrete/FavRecord";

import type Plan from "./Plan";
import _PlanFav_stuff_TypeInfo from "./_PlanFav_stuff_TypeInfo";

export class _PlanFav_stuff extends FavRecord {

    static _typeInfo: _PlanFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PlanFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    plan: Plan;
    planId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PlanFav_stuff;
