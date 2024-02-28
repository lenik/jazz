import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import OrgUnitTypeInfo from "./OrgUnitTypeInfo";
import OrganizationTypeInfo from "./OrganizationTypeInfo";
import PartyTypeInfo from "./PartyTypeInfo";
import _OrgUnit_stuff_Validators from "./_OrgUnit_stuff_Validators";

export class _OrgUnit_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "orgunit";

    get name() { return "net.bodz.lily.schema.contact.OrgUnit"; }
    get icon() { return "fa-tag"; }

    static FIELD_PROPERTIES = "props";
    static FIELD_ORG_ID = "org";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";

    static N_PROPERTIES = 2147483647;
    static N_ORG_ID = 10;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;

    validators = new _OrgUnit_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        properties: property({ type: "JsonVariant", validator: this.validators.validateProperties }),
        depth: property({ type: "int", nullable: false, precision: 10, validator: this.validators.validateDepth }),

        org: property({ type: OrganizationTypeInfo, nullable: false, validator: this.validators.validateOrg }),
        orgId: property({ type: "int", nullable: false, precision: 10 }),

        parent: property({ type: OrgUnitTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "int", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _OrgUnit_stuff_TypeInfo;
