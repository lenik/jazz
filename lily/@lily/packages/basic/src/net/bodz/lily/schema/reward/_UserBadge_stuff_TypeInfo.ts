import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import User from "../account/User";
import _UserBadge_stuff_Validators from "./_UserBadge_stuff_Validators";

export class _UserBadge_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user_badge";

    name = "net.bodz.lily.schema.reward.UserBadge"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_USER_ID = "user";
    static FIELD_BADGE_ID = "badge";

    static N_ID = 10;
    static N_USER_ID = 10;
    static N_BADGE_ID = 10;

    static validators = new _UserBadge_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),

        badge: property({ type: net.bodz.lily.schema.reward.BadgeTypeInfo, validator: this.validators.validateBadge }),
        badgeId: property({ type: "integer", precision: 10 }),

        user: property({ type: net.bodz.lily.schema.account.UserTypeInfo, inheritsDoc: true, 
            description: "User Account", 
            validator: this.validators.validateUser }),
        userId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_UserBadge_stuff_TypeInfo.declaredProperty);
    }

}

export default _UserBadge_stuff_TypeInfo;
