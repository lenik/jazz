import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import PartyTypeInfo from "./PartyTypeInfo";
import _Organization_stuff_Validators from "./_Organization_stuff_Validators";

export class _Organization_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "org";

    static readonly FIELD_ROLE_COUNT = "nrole";
    static readonly FIELD_BANK_COUNT = "nbank";
    static readonly FIELD_SIZE = "size";
    static readonly FIELD_TAX_ID = "taxid";

    static readonly N_ROLE_COUNT = 10;
    static readonly N_BANK_COUNT = 10;
    static readonly N_SIZE = 10;
    static readonly N_TAX_ID = 20;

    readonly validators = new _Organization_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.Organization"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            roleCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRoleCount }),
            bankCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateBankCount }),
            size: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateSize }),
            taxId: property({ type: STRING, precision: 20, validator: this.validators.validateTaxId }),
        });
    }

    static readonly INSTANCE = new _Organization_stuff_TypeInfo();

}

export default _Organization_stuff_TypeInfo;

export const _Organization_stuff_TYPE = _Organization_stuff_TypeInfo.INSTANCE;
