import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import UserRunTypeInfo from "./UserRunTypeInfo";
import _UserRun_stuff from "./_UserRun_stuff";

export class UserRun extends _UserRun_stuff {
    static TYPE = new UserRunTypeInfo();

    activeTime?: ZonedDateTime
    stateText?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserRun;
