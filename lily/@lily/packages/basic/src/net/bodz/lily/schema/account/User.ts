import type { integer } from "@skeljs/core/src/lang/type";

import { Group } from "./Group";
import { UserOtherId } from "./UserOtherId";
import { UserRun } from "./UserRun";
import { UserSecret } from "./UserSecret";
import _User_stuff from "./_User_stuff";
import { _User_stuff_Type } from "./_User_stuff_Type";

export class User extends _User_stuff {
    static TYPE = new _User_stuff_Type();

    groupIds?: integer[]
    groups?: Group[]
    ids?: UserOtherId[]
    otherIds?: UserOtherId[]
    registerIP?: string
    runningState?: UserRun
    secret?: UserSecret
    secrets?: UserSecret[]
    superUser: boolean

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default User;
