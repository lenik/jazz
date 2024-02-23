import _UserOtherId_stuff from "./_UserOtherId_stuff";
import { _UserOtherId_stuff_Type } from "./_UserOtherId_stuff_Type";

export class UserOtherId extends _UserOtherId_stuff {
    static TYPE = new _UserOtherId_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherId;
