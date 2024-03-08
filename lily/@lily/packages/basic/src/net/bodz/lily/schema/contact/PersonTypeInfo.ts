import { LIST, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import PersonValidators from "./PersonValidators";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class PersonTypeInfo extends _Person_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Person"; }
    get icon() { return "fa-tag"; }

    validators = new PersonValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            hello: property({ type: STRING, validator: this.validators.validateHello }),
            peers: property({ type: LIST(STRING), validator: this.validators.validatePeers }),
        });
    }

    constructor() {
        super();
    }

}

export default PersonTypeInfo;
