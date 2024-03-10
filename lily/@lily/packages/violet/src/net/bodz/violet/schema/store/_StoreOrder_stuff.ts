import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import type FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import type Plan from "../plan/Plan";
import type StoreCategory from "./StoreCategory";
import type StoreOrder from "./StoreOrder";
import type StorePhase from "./StorePhase";
import _StoreOrder_stuff_TypeInfo from "./_StoreOrder_stuff_TypeInfo";

export class _StoreOrder_stuff extends CoEntity<long> {

    static _typeInfo: _StoreOrder_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _StoreOrder_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: int;
    subject: string;
    rawText?: string;
    formArguments?: string;
    length: int;
    totalQuantity: BigDecimal;
    totalAmount: BigDecimal;

    person?: Person;
    personId?: int;

    plan?: Plan;
    planId?: long;

    phase: StorePhase;
    phaseId: int;

    category: StoreCategory;
    categoryId: int;

    prev?: StoreOrder;
    prevId?: long;

    op?: User;
    opId?: int;

    form?: FormDef;
    formId?: int;

    org?: Organization;
    orgId?: int;

    orgUnit?: OrgUnit;
    orgUnitId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _StoreOrder_stuff;
