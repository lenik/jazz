import _UserOtherIdType_stuff from "./_UserOtherIdType_stuff";
import { _UserOtherIdType_stuffTypeInfo } from "./_UserOtherIdType_stuffTypeInfo";

export class UserOtherIdType extends _UserOtherIdType_stuff {
    static TYPE = new _UserOtherIdType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherIdType;
