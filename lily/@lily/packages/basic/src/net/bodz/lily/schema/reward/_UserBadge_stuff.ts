import type { int } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type User from "../account/User";
import type Badge from "./Badge";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class _UserBadge_stuff extends IdEntity<int> {

    static _typeInfo: _UserBadge_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserBadge_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    badge?: Badge;
    badgeId?: int;

    user?: User;
    userId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserBadge_stuff;
