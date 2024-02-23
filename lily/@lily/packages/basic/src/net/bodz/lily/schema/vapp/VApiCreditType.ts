import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VApiCreditValidators from "./VApiCreditValidators";
import _VApiCredit_stuff_Type from "./_VApiCredit_stuff_Type";

// Type Info

export class VApiCreditType extends _VApiCredit_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VApiCredit"
    icon = "fa-tag"

    static validators = new VApiCreditValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiCreditType.declaredProperty);
    }

}

export default VApiCredit;
