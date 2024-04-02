import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { OrgUnit_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnitTypeInfo";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import AbstractAssetTypeInfo from "./AbstractAssetTypeInfo";
import _Asset_stuff_Validators from "./_Asset_stuff_Validators";

export class _Asset_stuff_TypeInfo extends AbstractAssetTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "asset";

    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_USER_ID = "o_user";
    static readonly FIELD_GROUP_ID = "o_group";
    static readonly FIELD_ORG_ID = "o_org";
    static readonly FIELD_ORG_UNIT_ID = "o_orgunit";
    static readonly FIELD_PERSON_ID = "o_person";

    static readonly N_BATCH = 2147483647;
    static readonly N_USER_ID = 10;
    static readonly N_GROUP_ID = 10;
    static readonly N_ORG_ID = 10;
    static readonly N_ORG_UNIT_ID = 10;
    static readonly N_PERSON_ID = 10;

    readonly validators = new _Asset_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.Asset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),

            person: property({ type: Person_TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            group: property({ type: Group_TYPE, inheritsDoc: true, 
                description: "User Group", 
                validator: this.validators.validateGroup }),
            groupId: property({ type: INT, precision: 10 }),

            org: property({ type: Organization_TYPE, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, precision: 10 }),

            orgUnit: property({ type: OrgUnit_TYPE, validator: this.validators.validateOrgUnit }),
            orgUnitId: property({ type: INT, precision: 10 }),

            user: property({ type: User_TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Asset_stuff_TypeInfo();

}

export default _Asset_stuff_TypeInfo;

export const _Asset_stuff_TYPE = _Asset_stuff_TypeInfo.INSTANCE;
