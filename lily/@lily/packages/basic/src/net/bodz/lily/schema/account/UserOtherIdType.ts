import _UserOtherIdType_stuff from "./_UserOtherIdType_stuff";
import { _UserOtherIdType_stuff_Type } from "./_UserOtherIdType_stuff_Type";

export class UserOtherIdType extends _UserOtherIdType_stuff {
    static TYPE = new _UserOtherIdType_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherIdType;
