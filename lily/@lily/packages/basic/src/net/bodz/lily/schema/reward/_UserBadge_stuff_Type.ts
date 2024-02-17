
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { User } from "../account/User";
import { * as validators } from "./PersonValidators";

// Type Info

export class _UserBadge_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "user_badge";

    name = "net.bodz.lily.schema.reward.UserBadge"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_USER_ID = "user";
    static const FIELD_BADGE_ID = "badge";

    static const N_ID = 10;
    static const N_USER_ID = 10;
    static const N_BADGE_ID = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),

        badge: property({ type: "net.bodz.lily.schema.reward.Badge", validator: validators.validate_badge }),
        badgeId: property({ type: "integer", precision: 10, validator: validators.validate_badgeId }),

        user: property({ type: "net.bodz.lily.schema.account.User", 
            description: "User Account", 
            validator: validators.validate_user }),
        userId: property({ type: "integer", precision: 10, validator: validators.validate_userId }),
    }

    constructor() {
        super();
        this.declare(_UserBadge_stuff_Type.declaredProperty);
    }

}
