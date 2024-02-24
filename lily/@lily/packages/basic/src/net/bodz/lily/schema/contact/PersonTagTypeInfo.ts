import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonTagValidators from "./PersonTagValidators";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class PersonTagTypeInfo extends _PersonTag_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PersonTag"
    icon = "fa-tag"

    validators = new PersonTagValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonTagTypeInfo;
