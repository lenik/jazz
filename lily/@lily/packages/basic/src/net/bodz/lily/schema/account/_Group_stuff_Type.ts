
import type { CoPrincipalType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _Group_stuff_Type extends CoPrincipalType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "group";

    name = "net.bodz.lily.schema.account.Group"
    icon = "fa-tag"
    label = "Group (Role)"
    description = "User Group"

    static const FIELD_TYPE_ID = "type";
    static const FIELD_PARENT_ID = "parent";

    static const N_TYPE_ID = 10;
    static const N_PARENT_ID = 10;

    static declaredProperty: EntityPropertyMap = {

        parent: property({ type: "net.bodz.lily.schema.account.Group", 
            description: "The parent group. must be acyclic", 
            validator: validators.validate_parent }),
        parentId: property({ type: "integer", precision: 10, 
            description: "The parent group. must be acyclic", 
            validator: validators.validate_parentId }),

        type: property({ type: "net.bodz.lily.schema.account.GroupType", nullable: false, 
            description: "Group type like normal-group, role-group, etc.", 
            validator: validators.validate_type }),
        typeId: property({ type: "int", nullable: false, precision: 10, 
            description: "Group type like normal-group, role-group, etc.", 
            validator: validators.validate_typeId }),
    }

    constructor() {
        super();
        this.declare(_Group_stuff_Type.declaredProperty);
    }

}
