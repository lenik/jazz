import { ValidateResult } from "@skeljs/core/src/ui/types"
import { Moment } from "moment-timezone";
import PartyValidators from "../../src/net/bodz/lily/schema/contact/PartyValidators";
import PersonTypeInfo from "./PersonTypeInfo";
import Person from "./Person"

function lengthGE3(val: string) {
    if (val.length < 3)
        throw "length is too short, at least 3 chars."
}

class PersonValidators extends PartyValidators {

    constructor(type: PersonTypeInfo) {
        super(type);
    }

    validateFather(val: Person) {
        if (val.gender != null)
            if (val.gender != 'male')
                throw new Error("father should be male.");
    }

    validateMother(val: Person) {
        if (val.gender != null)
            if (val.gender != 'female')
                throw new Error("mother should be female.");
    }

    validateGender(val: string) {
    }

    validateHomeland(val: string) {
        lengthGE3(val);
    }

    validatePassport(val: string) {
        lengthGE3(val);
    }

    validateSsn(val: string) {
        lengthGE3(val);
    }

    validateDln(val: string) {
        lengthGE3(val);
    }
}

export default PersonValidators;
