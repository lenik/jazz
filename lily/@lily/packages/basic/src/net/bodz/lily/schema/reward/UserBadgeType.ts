import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserBadgeValidators from "./UserBadgeValidators";
import _UserBadge_stuff_Type from "./_UserBadge_stuff_Type";

// Type Info

export class UserBadgeType extends _UserBadge_stuff_Type {

    name = "net.bodz.lily.schema.reward.UserBadge"
    icon = "fa-tag"

    static validators = new UserBadgeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserBadgeType.declaredProperty);
    }

}

export default UserBadge;
