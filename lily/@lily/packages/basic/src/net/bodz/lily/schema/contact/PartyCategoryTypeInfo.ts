import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PartyCategoryValidators from "./PartyCategoryValidators";
import _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

export class PartyCategoryTypeInfo extends _PartyCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PartyCategory"
    icon = "fa-tag"

    validators = new PartyCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PartyCategoryTypeInfo;
