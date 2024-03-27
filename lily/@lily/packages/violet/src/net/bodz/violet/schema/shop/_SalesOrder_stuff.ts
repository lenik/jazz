import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type Plan from "../plan/Plan";
import type SalesCategory from "./SalesCategory";
import type SalesOrder from "./SalesOrder";
import type SalesPhase from "./SalesPhase";
import _SalesOrder_stuff_TypeInfo from "./_SalesOrder_stuff_TypeInfo";

export class _SalesOrder_stuff extends CoMessage<long> {

    static _typeInfo: _SalesOrder_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _SalesOrder_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    length: int;
    totalQuantity: BigDecimal;
    totalAmount: BigDecimal;

    customer?: Person;
    customerId?: int;

    phase?: SalesPhase;
    phaseId?: int;

    customerOrg?: Organization;
    customerOrgId?: int;

    previousOrder?: SalesOrder;
    previousOrderId?: long;

    plan?: Plan;
    planId?: long;

    category?: SalesCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SalesOrder_stuff;
