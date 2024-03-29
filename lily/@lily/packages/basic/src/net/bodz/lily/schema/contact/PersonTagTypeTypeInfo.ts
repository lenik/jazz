import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import PersonTagTypeValidators from "./PersonTagTypeValidators";
import _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

export class PersonTagTypeTypeInfo extends _PersonTagType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.PersonTagType"; }
    get icon() { return "fa-tag"; }

    validators = new PersonTagTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonTagTypeTypeInfo;
