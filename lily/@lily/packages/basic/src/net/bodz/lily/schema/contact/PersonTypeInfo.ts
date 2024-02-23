import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import PersonValidators from "./PersonValidators";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

// Type Info

export class PersonTypeInfo extends _Person_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.Person"
    icon = "fa-tag"

    static validators = new PersonValidators();

    static declaredProperty: EntityPropertyMap = {
        hello: property({ type: "string", validator: this.validators.validateHello }),
        peers: property({ type: "List", validator: this.validators.validatePeers }),
    }

    constructor() {
        super();
        this.declare(PersonTypeInfo.declaredProperty);
    }

}

export default Person;
