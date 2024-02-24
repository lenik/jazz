import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonTagTypeValidators from "./PersonTagTypeValidators";
import _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

export class PersonTagTypeTypeInfo extends _PersonTagType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    validators = new PersonTagTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonTagTypeTypeInfo;
