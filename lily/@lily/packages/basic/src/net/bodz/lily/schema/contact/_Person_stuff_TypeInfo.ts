import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BOOLEAN, CHAR, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import PartyTypeInfo from "./PartyTypeInfo";
import Person from "./Person";
import _Person_stuff_Validators from "./_Person_stuff_Validators";

export class _Person_stuff_TypeInfo extends PartyTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "person";

    get name() { return "net.bodz.lily.schema.contact.Person"; }
    get icon() { return "fa-tag"; }

    static FIELD_PROPERTIES = "props";
    static FIELD_FATHER_ID = "father";
    static FIELD_MOTHER_ID = "mother";
    static FIELD_ROLE_COUNT = "nrole";
    static FIELD_EMPLOYEE = "employee";
    static FIELD_BANK_COUNT = "nbank";
    static FIELD_GENDER = "gender";
    static FIELD_PRONOUN = "pronoun";
    static FIELD_SEXUAL_ORIENTATION = "sexual_orient";
    static FIELD_HOMELAND = "homeland";
    static FIELD_PASSPORT = "passport";
    static FIELD_SSN = "ssn";
    static FIELD_DLN = "dln";

    static N_PROPERTIES = 2147483647;
    static N_FATHER_ID = 10;
    static N_MOTHER_ID = 10;
    static N_ROLE_COUNT = 10;
    static N_EMPLOYEE = 1;
    static N_BANK_COUNT = 10;
    static N_GENDER = 1;
    static N_PRONOUN = 30;
    static N_SEXUAL_ORIENTATION = 30;
    static N_HOMELAND = 10;
    static N_PASSPORT = 20;
    static N_SSN = 20;
    static N_DLN = 20;

    validators = new _Person_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
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
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Person_stuff_TypeInfo;
