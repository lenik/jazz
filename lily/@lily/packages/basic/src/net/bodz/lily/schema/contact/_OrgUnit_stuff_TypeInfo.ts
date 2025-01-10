import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { OrgUnit_TYPE } from "./OrgUnitTypeInfo";
import { Organization_TYPE } from "./OrganizationTypeInfo";
import PartyTypeInfo from "./PartyTypeInfo";
import _OrgUnit_stuff_Validators from "./_OrgUnit_stuff_Validators";

export class _OrgUnit_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "orgunit";

    static readonly FIELD_ORG_ID = "org";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_DEPTH = "depth";

    static readonly N_ORG_ID = 10;
    static readonly N_PARENT_ID = 10;
    static readonly N_DEPTH = 10;

    readonly validators = new _OrgUnit_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.OrgUnit"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),

            org: property({ type: Organization_TYPE, nullable: false, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, nullable: false, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _OrgUnit_stuff_TypeInfo();

}

export default _OrgUnit_stuff_TypeInfo;

export const _OrgUnit_stuff_TYPE = _OrgUnit_stuff_TypeInfo.INSTANCE;
