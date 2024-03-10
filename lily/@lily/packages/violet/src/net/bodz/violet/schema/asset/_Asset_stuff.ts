import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import type Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import AbstractAsset from "./AbstractAsset";
import _Asset_stuff_TypeInfo from "./_Asset_stuff_TypeInfo";

export class _Asset_stuff extends AbstractAsset {

    static _typeInfo: _Asset_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Asset_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;

    person?: Person;
    personId?: int;

    group?: Group;
    groupId?: int;

    org?: Organization;
    orgId?: int;

    orgUnit?: OrgUnit;
    orgUnitId?: int;

    user?: User;
    userId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Asset_stuff;
