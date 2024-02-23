import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PartyCategoryValidators from "./PartyCategoryValidators";
import _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

// Type Info

export class PartyCategoryTypeInfo extends _PartyCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PartyCategory"
    icon = "fa-tag"

    static validators = new PartyCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PartyCategoryTypeInfo.declaredProperty);
    }

}

export default PartyCategory;
