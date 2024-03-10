import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import type Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import AbstractAssetValidators from "./AbstractAssetValidators";
import type _Asset_stuff_TypeInfo from "./_Asset_stuff_TypeInfo";

export class _Asset_stuff_Validators extends AbstractAssetValidators {

    constructor(type: _Asset_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Asset_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validatePerson(val: Person) {
    }

    validateGroup(val: Group) {
    }

    validateOrg(val: Organization) {
    }

    validateOrgUnit(val: OrgUnit) {
    }

    validateUser(val: User) {
    }

}

export default _Asset_stuff_Validators;
