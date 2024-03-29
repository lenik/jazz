import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoPrincipalTypeInfo from "../../concrete/CoPrincipalTypeInfo";
import GroupTypeInfo from "./GroupTypeInfo";
import GroupTypeTypeInfo from "./GroupTypeTypeInfo";
import _Group_stuff_Validators from "./_Group_stuff_Validators";

export class _Group_stuff_TypeInfo extends CoPrincipalTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "group";

    get name() { return "net.bodz.lily.schema.account.Group"; }
    get icon() { return "fa-tag"; }
    get label() { return "Group (Role)"; }
    get description() { return "User Group"; }

    static FIELD_TYPE_ID = "type";
    static FIELD_PARENT_ID = "parent";

    static N_TYPE_ID = 10;
    static N_PARENT_ID = 10;

    validators = new _Group_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {

        parent: property({ type: GroupTypeInfo, 
            description: "The parent group. must be acyclic", 
            validator: this.validators.validateParent }),
        parentId: property({ type: "int", precision: 10, 
            description: "The parent group. must be acyclic" }),

        type: property({ type: GroupTypeTypeInfo, nullable: false, 
            description: "Group type like normal-group, role-group, etc.", 
            validator: this.validators.validateType }),
        typeId: property({ type: "int", nullable: false, precision: 10, 
            description: "Group type like normal-group, role-group, etc." }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Group_stuff_TypeInfo;
