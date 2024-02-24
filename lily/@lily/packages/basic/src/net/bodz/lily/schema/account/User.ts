import type { integer } from "@skeljs/core/src/lang/type";

import Group from "./Group";
import UserOtherId from "./UserOtherId";
import UserRun from "./UserRun";
import UserSecret from "./UserSecret";
import UserTypeInfo from "./UserTypeInfo";
import _User_stuff from "./_User_stuff";

export class User extends _User_stuff {
    static TYPE = new UserTypeInfo();

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
