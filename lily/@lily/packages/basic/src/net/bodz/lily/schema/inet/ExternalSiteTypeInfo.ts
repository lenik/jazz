import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ExternalSiteValidators from "./ExternalSiteValidators";
import _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

// Type Info

export class ExternalSiteTypeInfo extends _ExternalSite_stuff_TypeInfo {

    name = "net.bodz.lily.schema.inet.ExternalSite"
    icon = "fa-tag"

    static validators = new ExternalSiteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ExternalSiteTypeInfo.declaredProperty);
    }

}

export default ExternalSite;
