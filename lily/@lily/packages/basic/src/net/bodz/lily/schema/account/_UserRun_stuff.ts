import type { InetAddress, Timestamp, int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class _UserRun_stuff extends CoEntity<int> {
    static _typeInfo: _UserRun_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _UserRun_stuff_TypeInfo();
        return this._typeInfo;
    }

    score: int;
    lastLoginTime?: Timestamp;
    lastLoginIP?: InetAddress;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserRun_stuff;
