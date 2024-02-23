import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import BadgeValidators from "./BadgeValidators";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

// Type Info

export class BadgeTypeInfo extends _Badge_stuff_TypeInfo {

    name = "net.bodz.lily.schema.reward.Badge"
    icon = "fa-tag"

    static validators = new BadgeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(BadgeTypeInfo.declaredProperty);
    }

}

export default Badge;
