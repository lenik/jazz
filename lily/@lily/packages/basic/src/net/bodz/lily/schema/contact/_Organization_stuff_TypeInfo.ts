import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import PartyTypeInfo from "./PartyTypeInfo";
import _Organization_stuff_Validators from "./_Organization_stuff_Validators";

export class _Organization_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "org";

    get name() { return "net.bodz.lily.schema.contact.Organization"; }
    get icon() { return "fa-tag"; }

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

    validators = new _Organization_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
        roleCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRoleCount }),
        bankCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateBankCount }),
        size: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateSize }),
        taxId: property({ type: STRING, precision: 20, validator: this.validators.validateTaxId }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Organization_stuff_TypeInfo;
