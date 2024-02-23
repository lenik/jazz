import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import PartyTypeInfo from "./PartyTypeInfo";
import _OrgUnit_stuff_Validators from "./_OrgUnit_stuff_Validators";

export class _OrgUnit_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "orgunit";

    name = "net.bodz.lily.schema.contact.OrgUnit"
    icon = "fa-tag"

    static FIELD_PROPERTIES = "props";
    static FIELD_ORG_ID = "org";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";

    static N_PROPERTIES = 2147483647;
    static N_ORG_ID = 10;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;

    static validators = new _OrgUnit_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        properties: property({ type: "any", validator: this.validators.validateProperties }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),

        org: property({ type: net.bodz.lily.schema.contact.OrganizationTypeInfo, nullable: false, validator: this.validators.validateOrg }),
        orgId: property({ type: "integer", nullable: false, precision: 10 }),

        parent: property({ type: net.bodz.lily.schema.contact.OrgUnitTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_OrgUnit_stuff_TypeInfo.declaredProperty);
    }

}

export default _OrgUnit_stuff_TypeInfo;
