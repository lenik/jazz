import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonValidators from "./PersonValidators";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class PersonTypeInfo extends _Person_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.Person"
    icon = "fa-tag"

    validators = new PersonValidators(this);

    declaredProperty: EntityPropertyMap = {
        hello: property({ type: "string", validator: this.validators.validateHello }),
        peers: property({ type: "string[]", validator: this.validators.validatePeers }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonTypeInfo;
