import _UserType_stuff from "./_UserType_stuff";
import { _UserType_stuffTypeInfo } from "./_UserType_stuffTypeInfo";

export class UserType extends _UserType_stuff {
    static TYPE = new _UserType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserType;
