
import type { CoPrincipalType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _User_stuff_Type extends CoPrincipalType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "user";

    name = "net.bodz.lily.schema.account.User"
    icon = "fa-tag"
    label = "User (Account)"
    description = "User Account"

    static const FIELD_TYPE_ID = "type";
    static const FIELD_PRIMARY_GROUP_ID = "gid0";
    static const FIELD_REFERER_ID = "referer";
    static const FIELD_PERSON_ID = "person";

    static const N_TYPE_ID = 10;
    static const N_PRIMARY_GROUP_ID = 10;
    static const N_REFERER_ID = 10;
    static const N_PERSON_ID = 10;

    static declaredProperty: EntityPropertyMap = {

        person: property({ type: "net.bodz.lily.schema.contact.Person", validator: validators.validate_person }),
        personId: property({ type: "integer", precision: 10, validator: validators.validate_personId }),

        primaryGroup: property({ type: "net.bodz.lily.schema.account.Group", nullable: false, 
            description: "The primary user group, the default value of ownerGroup.", 
            validator: validators.validate_primaryGroup }),
        primaryGroupId: property({ type: "int", nullable: false, precision: 10, 
            description: "The primary user group, the default value of ownerGroup.", 
            validator: validators.validate_primaryGroupId }),

        referer: property({ type: "net.bodz.lily.schema.account.User", 
            description: "The referer user (used for promotion)", 
            validator: validators.validate_referer }),
        refererId: property({ type: "integer", precision: 10, 
            description: "The referer user (used for promotion)", 
            validator: validators.validate_refererId }),

        type: property({ type: "net.bodz.lily.schema.account.UserType", nullable: false, 
            description: "User type like system-user, normal-user, etc.", 
            validator: validators.validate_type }),
        typeId: property({ type: "int", nullable: false, precision: 10, 
            description: "User type like system-user, normal-user, etc.", 
            validator: validators.validate_typeId }),
    }

    constructor() {
        super();
        this.declare(_User_stuff_Type.declaredProperty);
    }

}
