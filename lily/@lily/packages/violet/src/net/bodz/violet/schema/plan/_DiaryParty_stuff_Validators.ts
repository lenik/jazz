import type { int, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type Diary from "./Diary";
import type _DiaryParty_stuff_TypeInfo from "./_DiaryParty_stuff_TypeInfo";

export class _DiaryParty_stuff_Validators extends IdEntityValidators {

    constructor(type: _DiaryParty_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryParty_stuff_TypeInfo;
    }

    validateValue(val: int) {
    }

    validatePerson(val: Person) {
    }

    validateOrg(val: Organization) {
    }

    validateUser(val: User) {
    }

    validateDiary(val: Diary) {
    }

}

export default _DiaryParty_stuff_Validators;
