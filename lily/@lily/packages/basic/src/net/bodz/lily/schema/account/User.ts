
import type { InetAddress } from "../../../../../java/net/InetAddress";
import type { List } from "../../../../../java/util/List";
import type { Set } from "../../../../../java/util/Set";
import { * as validators } from "./PersonValidators";
import type { UserRun } from "./UserRun";
import type { UserSecret } from "./UserSecret";
import type { _User_stuff } from "./_User_stuff";

export class User extends _User_stuff {
    static TYPE = new UserType();

    groupIds?: Set
    groups?: List
    ids?: List
    otherIds?: List
    registerIP?: InetAddress
    runningState?: UserRun
    secret?: UserSecret
    secrets?: List
    superUser: boolean

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
