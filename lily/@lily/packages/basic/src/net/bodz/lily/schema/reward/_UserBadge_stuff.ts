import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type User from "../account/User";
import type Badge from "./Badge";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class _UserBadge_stuff extends CoEntity<int> {
    static _typeInfo: _UserBadge_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _UserBadge_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;

    badge?: Badge;
    badgeId?: int;

    user?: User;
    userId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserBadge_stuff;
