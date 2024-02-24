import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoPrincipalTypeInfo from "../../concrete/CoPrincipalTypeInfo";
import GroupTypeInfo from "./GroupTypeInfo";
import GroupTypeTypeInfo from "./GroupTypeTypeInfo";
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

    validators = new _Group_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {

        parent: property({ type: GroupTypeInfo, 
            description: "The parent group. must be acyclic", 
            validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10, 
            description: "The parent group. must be acyclic" }),

        type: property({ type: GroupTypeTypeInfo, nullable: false, 
            description: "Group type like normal-group, role-group, etc.", 
            validator: this.validators.validateType }),
        typeId: property({ type: "integer", nullable: false, precision: 10, 
            description: "Group type like normal-group, role-group, etc." }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Group_stuff_TypeInfo;
