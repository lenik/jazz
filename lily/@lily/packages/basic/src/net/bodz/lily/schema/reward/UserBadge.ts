
import { * as validators } from "./PersonValidators";
import type { _UserBadge_stuff } from "./_UserBadge_stuff";

export class UserBadge extends _UserBadge_stuff {
    static TYPE = new UserBadgeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
