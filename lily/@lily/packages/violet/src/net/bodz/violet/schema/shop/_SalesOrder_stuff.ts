import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import type FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import type Plan from "../plan/Plan";
import type SalesCategory from "./SalesCategory";
import type SalesOrder from "./SalesOrder";
import type SalesPhase from "./SalesPhase";
import _SalesOrder_stuff_TypeInfo from "./_SalesOrder_stuff_TypeInfo";

export class _SalesOrder_stuff extends CoEntity<long> {

    static _typeInfo: _SalesOrder_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _SalesOrder_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: int;
    subject: string;
    rawText?: string;
    formArguments?: string;
    properties?: JsonVariant;
    length: int;
    totalQuantity: BigDecimal;
    totalAmount: BigDecimal;

    customer?: Person;
    customerId?: int;

    form?: FormDef;
    formId?: int;

    phase?: SalesPhase;
    phaseId?: int;

    customerOrg?: Organization;
    customerOrgId?: int;

    previousOrder?: SalesOrder;
    previousOrderId?: long;

    op?: User;
    opId?: int;

    plan?: Plan;
    planId?: long;

    category?: SalesCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SalesOrder_stuff;
