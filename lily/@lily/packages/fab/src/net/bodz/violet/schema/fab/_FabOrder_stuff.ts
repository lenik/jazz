import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import CoMessage from "lily-basic/src/net/bodz/lily/concrete/CoMessage";
import type Organization from "lily-basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "lily-basic/src/net/bodz/lily/schema/contact/Person";
import type Plan from "lily-violet/src/net/bodz/violet/schema/plan/Plan";

import _FabOrder_stuff_TypeInfo from "./_FabOrder_stuff_TypeInfo";

export class _FabOrder_stuff extends CoMessage<long> {

    static _typeInfo: _FabOrder_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabOrder_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    nitem: int;
    totalQuantity: BigDecimal;
    totalAmount: BigDecimal;
    taskCount?: int;
    processCount?: int;
    trackCount?: int;

    clerk?: Person;
    clerkId?: int;

    plan?: Plan;
    planId?: long;

    customOrg?: Organization;
    customOrgId?: int;

    custom?: Person;
    customId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabOrder_stuff;
