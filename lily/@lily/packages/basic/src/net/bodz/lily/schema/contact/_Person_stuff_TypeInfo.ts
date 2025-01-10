import { BOOLEAN, CHAR, INT, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import PartyTypeInfo from "./PartyTypeInfo";
import { Person_TYPE } from "./PersonTypeInfo";
import _Person_stuff_Validators from "./_Person_stuff_Validators";

export class _Person_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "person";

    static readonly FIELD_FATHER_ID = "father";
    static readonly FIELD_MOTHER_ID = "mother";
    static readonly FIELD_ROLE_COUNT = "nrole";
    static readonly FIELD_EMPLOYEE = "employee";
    static readonly FIELD_BANK_COUNT = "nbank";
    static readonly FIELD_GENDER = "gender";
    static readonly FIELD_PRONOUN = "pronoun";
    static readonly FIELD_SEXUAL_ORIENTATION = "sexual_orient";
    static readonly FIELD_HOMELAND = "homeland";
    static readonly FIELD_PASSPORT = "passport";
    static readonly FIELD_SSN = "ssn";
    static readonly FIELD_DLN = "dln";

    static readonly N_FATHER_ID = 10;
    static readonly N_MOTHER_ID = 10;
    static readonly N_ROLE_COUNT = 10;
    static readonly N_EMPLOYEE = 1;
    static readonly N_BANK_COUNT = 10;
    static readonly N_GENDER = 1;
    static readonly N_PRONOUN = 30;
    static readonly N_SEXUAL_ORIENTATION = 30;
    static readonly N_HOMELAND = 10;
    static readonly N_PASSPORT = 20;
    static readonly N_SSN = 20;
    static readonly N_DLN = 20;

    readonly validators = new _Person_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.Person"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            roleCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRoleCount }),
            employee: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateEmployee }),
            bankCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateBankCount }),
            gender: property({ type: CHAR, precision: 1, validator: this.validators.validateGender }),
            pronoun: property({ type: STRING, precision: 30, validator: this.validators.validatePronoun }),
            sexualOrientation: property({ type: STRING, precision: 30, validator: this.validators.validateSexualOrientation }),
            homeland: property({ type: STRING, precision: 10, validator: this.validators.validateHomeland }),
            passport: property({ type: STRING, precision: 20, validator: this.validators.validatePassport }),
            ssn: property({ type: STRING, precision: 20, validator: this.validators.validateSsn }),
            dln: property({ type: STRING, precision: 20, validator: this.validators.validateDln }),

            mother: property({ type: this, validator: this.validators.validateMother }),
            motherId: property({ type: INT, precision: 10 }),

            father: property({ type: this, validator: this.validators.validateFather }),
            fatherId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Person_stuff_TypeInfo();

}

export default _Person_stuff_TypeInfo;

export const _Person_stuff_TYPE = _Person_stuff_TypeInfo.INSTANCE;
