import type { integer } from "@skeljs/core/src/lang/type";
import CoPrincipalType from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Group } from "./Group";
import { GroupType } from "./GroupType";
import GroupValidators from "./GroupValidators";

export class _Group_stuff_Type extends CoPrincipalType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "group";

    name = "net.bodz.lily.schema.account.Group"
    icon = "fa-tag"
    label = "Group (Role)"
    description = "User Group"

    static FIELD_TYPE_ID = "type";
    static FIELD_PARENT_ID = "parent";

    static N_TYPE_ID = 10;
    static N_PARENT_ID = 10;

    static validators = new GroupValidators();

    static declaredProperty: EntityPropertyMap = {

        parent: property({ type: Group.TYPE, 
            description: "The parent group. must be acyclic", 
            validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10, 
            description: "The parent group. must be acyclic" }),

        type: property({ type: GroupType.TYPE, nullable: false, 
            description: "Group type like normal-group, role-group, etc.", 
            validator: this.validators.validateType }),
        typeId: property({ type: "integer", nullable: false, precision: 10, 
            description: "Group type like normal-group, role-group, etc." }),
    }

    constructor() {
        super();
        this.declare(_Group_stuff_Type.declaredProperty);
    }

}

export default _Group_stuff_Type;
