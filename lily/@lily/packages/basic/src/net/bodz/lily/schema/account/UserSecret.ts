import _UserSecret_stuff from "./_UserSecret_stuff";
import { _UserSecret_stuffTypeInfo } from "./_UserSecret_stuffTypeInfo";

export class UserSecret extends _UserSecret_stuff {
    static TYPE = new _UserSecret_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserSecret;
