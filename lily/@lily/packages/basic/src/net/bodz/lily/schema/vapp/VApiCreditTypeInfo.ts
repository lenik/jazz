import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VApiCreditValidators from "./VApiCreditValidators";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class VApiCreditTypeInfo extends _VApiCredit_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VApiCredit"
    icon = "fa-tag"

    validators = new VApiCreditValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VApiCreditTypeInfo;
