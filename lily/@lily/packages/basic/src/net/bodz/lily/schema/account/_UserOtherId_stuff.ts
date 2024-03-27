import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import CoImaged from "../../concrete/CoImaged";
import type User from "./User";
import type UserOtherIdType from "./UserOtherIdType";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class _UserOtherId_stuff extends CoImaged<int> {

    static _typeInfo: _UserOtherId_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserOtherId_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    beginTime?: OffsetDateTime;
    endTime?: OffsetDateTime;
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
