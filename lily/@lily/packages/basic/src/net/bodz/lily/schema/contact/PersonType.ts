import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import PersonValidators from "./PersonValidators";
import _Person_stuff_Type from "./_Person_stuff_Type";

// Type Info

export class PersonType extends _Person_stuff_Type {

    name = "net.bodz.lily.schema.contact.Person"
    icon = "fa-tag"

    static validators = new PersonValidators();

    static declaredProperty: EntityPropertyMap = {
        hello: property({ type: "string", validator: this.validators.validateHello }),
        peers: property({ type: "List", validator: this.validators.validatePeers }),
    }

    constructor() {
        super();
        this.declare(PersonType.declaredProperty);
    }

}

export default Person;
