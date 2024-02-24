import UserBadgeTypeInfo from "./UserBadgeTypeInfo";
import _UserBadge_stuff from "./_UserBadge_stuff";

export class UserBadge extends _UserBadge_stuff {
    static TYPE = new UserBadgeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserBadge;
