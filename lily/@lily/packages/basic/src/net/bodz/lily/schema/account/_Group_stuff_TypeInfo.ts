import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoPrincipalTypeInfo from "../../concrete/CoPrincipalTypeInfo";
import { Group_TYPE } from "./GroupTypeInfo";
import { GroupType_TYPE } from "./GroupTypeTypeInfo";
import _Group_stuff_Validators from "./_Group_stuff_Validators";

export class _Group_stuff_TypeInfo extends CoPrincipalTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "group";

    static readonly FIELD_TYPE_ID = "type";
    static readonly FIELD_PARENT_ID = "parent";

    static readonly N_TYPE_ID = 10;
    static readonly N_PARENT_ID = 10;

    readonly validators = new _Group_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.Group"; }
    get icon() { return "fa-tag"; }
    get label() { return "Group (Role)"; }
    get description() { return "User Group"; }

    override preamble() {
        super.preamble();
        this.declare({

            parent: property({ type: this, 
                description: "The parent group. must be acyclic", 
                validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10, 
                description: "The parent group. must be acyclic" }),

            type: property({ type: GroupType_TYPE, nullable: false, 
                description: "Group type like normal-group, role-group, etc.", 
                validator: this.validators.validateType }),
            typeId: property({ type: INT, nullable: false, precision: 10, 
                description: "Group type like normal-group, role-group, etc." }),
        });
    }

    static readonly INSTANCE = new _Group_stuff_TypeInfo();

}

export default _Group_stuff_TypeInfo;

export const _Group_stuff_TYPE = _Group_stuff_TypeInfo.INSTANCE;
