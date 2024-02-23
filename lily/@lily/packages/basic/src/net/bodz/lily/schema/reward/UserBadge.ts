import _UserBadge_stuff from "./_UserBadge_stuff";
import { _UserBadge_stuffTypeInfo } from "./_UserBadge_stuffTypeInfo";

export class UserBadge extends _UserBadge_stuff {
    static TYPE = new _UserBadge_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserBadge;
