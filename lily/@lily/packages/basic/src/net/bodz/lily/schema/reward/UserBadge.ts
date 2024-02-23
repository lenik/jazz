import _UserBadge_stuff from "./_UserBadge_stuff";
import { _UserBadge_stuff_Type } from "./_UserBadge_stuff_Type";

export class UserBadge extends _UserBadge_stuff {
    static TYPE = new _UserBadge_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserBadge;
