import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import type UserOtherIdType from "./UserOtherIdType";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class _UserOtherId_stuff extends CoEntity<int> {
    static _typeInfo: _UserOtherId_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _UserOtherId_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: int;
    otherId: string;
    auth?: JsonVariant;

    type: UserOtherIdType;
    typeId: int;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserOtherId_stuff;
