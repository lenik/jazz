import type { long } from "skel01-core/src/lang/basetype";
import FavRecord from "@lily/basic/src/net/bodz/lily/concrete/FavRecord";

import type Issue from "./Issue";
import _IssueFav_stuff_TypeInfo from "./_IssueFav_stuff_TypeInfo";

export class _IssueFav_stuff extends FavRecord {

    static _typeInfo: _IssueFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _IssueFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    issue: Issue;
    issueId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _IssueFav_stuff;
