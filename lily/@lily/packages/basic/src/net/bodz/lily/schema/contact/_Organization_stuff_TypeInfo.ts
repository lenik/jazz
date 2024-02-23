import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import PartyTypeInfo from "./PartyTypeInfo";
import _Organization_stuff_Validators from "./_Organization_stuff_Validators";

export class _Organization_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "org";

    name = "net.bodz.lily.schema.contact.Organization"
    icon = "fa-tag"

    static FIELD_PROPERTIES = "props";
    static FIELD_ROLE_COUNT = "nrole";
    static FIELD_BANK_COUNT = "nbank";
    static FIELD_SIZE = "size";
    static FIELD_TAX_ID = "taxid";

    static N_PROPERTIES = 2147483647;
    static N_ROLE_COUNT = 10;
    static N_BANK_COUNT = 10;
    static N_SIZE = 10;
    static N_TAX_ID = 20;

    static validators = new _Organization_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        properties: property({ type: "any", validator: this.validators.validateProperties }),
        roleCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateRoleCount }),
        bankCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateBankCount }),
        size: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateSize }),
        taxId: property({ type: "string", precision: 20, validator: this.validators.validateTaxId }),
    }

    constructor() {
        super();
        this.declare(_Organization_stuff_TypeInfo.declaredProperty);
    }

}

export default _Organization_stuff_TypeInfo;
