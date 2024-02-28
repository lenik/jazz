import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import List from "../../../../../java/util/List";
import PersonValidators from "./PersonValidators";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class PersonTypeInfo extends _Person_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Person"; }
    get icon() { return "fa-tag"; }

    validators = new PersonValidators(this);

    declaredProperty: EntityPropertyMap = {
        hello: property({ type: "string", validator: this.validators.validateHello }),
        peers: property({ type: "List<string>", validator: this.validators.validatePeers }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonTypeInfo;
