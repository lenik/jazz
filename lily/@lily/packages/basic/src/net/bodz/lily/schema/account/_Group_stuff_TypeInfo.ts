import type { integer } from "@skeljs/core/src/lang/type";
import CoPrincipalTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _Group_stuff_Validators from "./_Group_stuff_Validators";

export class _Group_stuff_TypeInfo extends CoPrincipalTypeInfo {

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

    static validators = new _Group_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {

        parent: property({ type: net.bodz.lily.schema.account.GroupTypeInfo, 
            description: "The parent group. must be acyclic", 
            validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10, 
            description: "The parent group. must be acyclic" }),

        type: property({ type: net.bodz.lily.schema.account.GroupTypeTypeInfo, nullable: false, 
            description: "Group type like normal-group, role-group, etc.", 
            validator: this.validators.validateType }),
        typeId: property({ type: "integer", nullable: false, precision: 10, 
            description: "Group type like normal-group, role-group, etc." }),
    }

    constructor() {
        super();
        this.declare(_Group_stuff_TypeInfo.declaredProperty);
    }

}

export default _Group_stuff_TypeInfo;
