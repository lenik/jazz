import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserBadgeValidators from "./UserBadgeValidators";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

// Type Info

export class UserBadgeTypeInfo extends _UserBadge_stuff_TypeInfo {

    name = "net.bodz.lily.schema.reward.UserBadge"
    icon = "fa-tag"

    static validators = new UserBadgeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserBadgeTypeInfo.declaredProperty);
    }

}

export default UserBadge;
