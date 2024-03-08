import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoPrincipalTypeInfo from "../../concrete/CoPrincipalTypeInfo";
import Group from "./Group";
import GroupType from "./GroupType";
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

    override preamble() {
        super.preamble();
        this.declare({

            parent: property({ type: this, 
                description: "The parent group. must be acyclic", 
                validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10, 
                description: "The parent group. must be acyclic" }),

            type: property({ type: GroupType.TYPE, nullable: false, 
                description: "Group type like normal-group, role-group, etc.", 
                validator: this.validators.validateType }),
            typeId: property({ type: INT, nullable: false, precision: 10, 
                description: "Group type like normal-group, role-group, etc." }),
        });
    }

    constructor() {
        super();
    }

}

export default _Group_stuff_TypeInfo;
