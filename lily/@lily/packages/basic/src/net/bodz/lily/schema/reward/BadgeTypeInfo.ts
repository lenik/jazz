import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import BadgeValidators from "./BadgeValidators";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class BadgeTypeInfo extends _Badge_stuff_TypeInfo {

    name = "net.bodz.lily.schema.reward.Badge"
    icon = "fa-tag"

    validators = new BadgeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default BadgeTypeInfo;
