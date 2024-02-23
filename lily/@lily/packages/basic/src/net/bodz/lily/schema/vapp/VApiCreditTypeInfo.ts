import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VApiCreditValidators from "./VApiCreditValidators";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

// Type Info

export class VApiCreditTypeInfo extends _VApiCredit_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VApiCredit"
    icon = "fa-tag"

    static validators = new VApiCreditValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiCreditTypeInfo.declaredProperty);
    }

}

export default VApiCredit;
