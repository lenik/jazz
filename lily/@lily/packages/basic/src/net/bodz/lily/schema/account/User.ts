import type { InetAddress, List, int } from "skel01-core/src/lang/basetype";

import Group from "./Group";
import UserOtherId from "./UserOtherId";
import UserRun from "./UserRun";
import UserSecret from "./UserSecret";
import UserTypeInfo from "./UserTypeInfo";
import _User_stuff from "./_User_stuff";

export class User extends _User_stuff {

    static _typeInfo: UserTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    groupIds?: Set<int>
    groups?: List<Group>
    ids?: List<UserOtherId>
    otherIds?: List<UserOtherId>
    registerIP?: InetAddress
    runningState?: UserRun
    secret?: UserSecret
    secrets?: List<UserSecret>
    superUser: boolean

    constructor(o?: any) {
        super(o);
    }
}

export default User;
