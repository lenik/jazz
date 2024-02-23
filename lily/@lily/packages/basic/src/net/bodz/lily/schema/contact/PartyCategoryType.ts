import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PartyCategoryValidators from "./PartyCategoryValidators";
import _PartyCategory_stuff_Type from "./_PartyCategory_stuff_Type";

// Type Info

export class PartyCategoryType extends _PartyCategory_stuff_Type {

    name = "net.bodz.lily.schema.contact.PartyCategory"
    icon = "fa-tag"

    static validators = new PartyCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PartyCategoryType.declaredProperty);
    }

}

export default PartyCategory;
