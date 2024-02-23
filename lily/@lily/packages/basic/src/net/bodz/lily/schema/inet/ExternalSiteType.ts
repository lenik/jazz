import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ExternalSiteValidators from "./ExternalSiteValidators";
import _ExternalSite_stuff_Type from "./_ExternalSite_stuff_Type";

// Type Info

export class ExternalSiteType extends _ExternalSite_stuff_Type {

    name = "net.bodz.lily.schema.inet.ExternalSite"
    icon = "fa-tag"

    static validators = new ExternalSiteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ExternalSiteType.declaredProperty);
    }

}

export default ExternalSite;
