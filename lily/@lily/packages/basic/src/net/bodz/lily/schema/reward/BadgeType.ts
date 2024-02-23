import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import BadgeValidators from "./BadgeValidators";
import _Badge_stuff_Type from "./_Badge_stuff_Type";

// Type Info

export class BadgeType extends _Badge_stuff_Type {

    name = "net.bodz.lily.schema.reward.Badge"
    icon = "fa-tag"

    static validators = new BadgeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(BadgeType.declaredProperty);
    }

}

export default Badge;
