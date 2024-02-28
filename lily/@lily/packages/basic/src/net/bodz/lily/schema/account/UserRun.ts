import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import UserRunTypeInfo from "./UserRunTypeInfo";
import _UserRun_stuff from "./_UserRun_stuff";

export class UserRun extends _UserRun_stuff {
    static _typeInfo: UserRunTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new UserRunTypeInfo();
        return this._typeInfo;
    }


    activeTime?: ZonedDateTime
    stateText?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserRun;
