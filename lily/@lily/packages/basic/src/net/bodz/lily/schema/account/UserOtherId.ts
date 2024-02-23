import _UserOtherId_stuff from "./_UserOtherId_stuff";
import { _UserOtherId_stuffTypeInfo } from "./_UserOtherId_stuffTypeInfo";

export class UserOtherId extends _UserOtherId_stuff {
    static TYPE = new _UserOtherId_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherId;
