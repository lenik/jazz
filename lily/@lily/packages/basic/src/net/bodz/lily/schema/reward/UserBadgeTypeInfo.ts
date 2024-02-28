import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import UserBadgeValidators from "./UserBadgeValidators";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class UserBadgeTypeInfo extends _UserBadge_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.reward.UserBadge"; }
    get icon() { return "fa-tag"; }

    validators = new UserBadgeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserBadgeTypeInfo;
