import { ValidateResult } from "skel01-core/src/ui/types"
import { Moment } from "moment-timezone";
import IdEntityValidators from "../../src/net/bodz/lily/concrete/IdEntityValidators";

function lengthGE3(val: string) {
    if (val.length < 3)
        throw "length is too short, at least 3 chars."
}

class PartyValidators extends IdEntityValidators {

    validateBirthday(val: Moment) {
    }

}

export default PartyValidators;
