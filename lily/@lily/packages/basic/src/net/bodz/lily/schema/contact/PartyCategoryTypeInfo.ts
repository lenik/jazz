import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PartyCategoryValidators from "./PartyCategoryValidators";
import _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

export class PartyCategoryTypeInfo extends _PartyCategory_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.PartyCategory"; }
    get icon() { return "fa-tag"; }

    validators = new PartyCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PartyCategoryTypeInfo;
