import { Moment } from "moment";

import _UserRun_stuff from "./_UserRun_stuff";
import { _UserRun_stuffTypeInfo } from "./_UserRun_stuffTypeInfo";

export class UserRun extends _UserRun_stuff {
    static TYPE = new _UserRun_stuffTypeInfo();

    activeTime?: Moment
    stateText?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserRun;
