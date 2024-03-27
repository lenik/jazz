import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { InetAddress, int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class _UserRun_stuff extends CoEntity<int> {

    static _typeInfo: _UserRun_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserRun_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    properties?: JsonVariant;
    score: int;
    lastLoginTime?: OffsetDateTime;
    lastLoginIP?: InetAddress;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserRun_stuff;
