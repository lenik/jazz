
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { PartyType } from "./PartyType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _OrgUnit_stuff_Type extends PartyType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "orgunit";

    name = "net.bodz.lily.schema.contact.OrgUnit"
    icon = "fa-tag"

    static const FIELD_ORG_ID = "org";
    static const FIELD_PARENT_ID = "parent";
    static const FIELD_DEPTH = "depth";

    static const N_ORG_ID = 10;
    static const N_PARENT_ID = 10;
    static const N_DEPTH = 10;

    static declaredProperty: EntityPropertyMap = {
        depth: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_depth }),

        org: property({ type: "net.bodz.lily.schema.contact.Organization", nullable: false, validator: validators.validate_org }),
        orgId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_orgId }),

        parent: property({ type: "net.bodz.lily.schema.contact.OrgUnit", validator: validators.validate_parent }),
        parentId: property({ type: "integer", precision: 10, validator: validators.validate_parentId }),
    }

    constructor() {
        super();
        this.declare(_OrgUnit_stuff_Type.declaredProperty);
    }

}
