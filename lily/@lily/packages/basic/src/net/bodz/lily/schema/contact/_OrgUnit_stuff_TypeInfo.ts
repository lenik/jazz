import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import OrgUnit from "./OrgUnit";
import Organization from "./Organization";
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

    override preamble() {
        super.preamble();
        this.declare({
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),

            org: property({ type: Organization.TYPE, nullable: false, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, nullable: false, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _OrgUnit_stuff_TypeInfo;
