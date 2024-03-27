import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import UserRunTypeInfo from "./UserRunTypeInfo";
import _UserRun_stuff from "./_UserRun_stuff";

export class UserRun extends _UserRun_stuff {

    static _typeInfo: UserRunTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserRunTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    activeTime?: OffsetDateTime
    stateText?: string

    constructor(o?: any) {
        super(o);
    }
}

export default UserRun;
