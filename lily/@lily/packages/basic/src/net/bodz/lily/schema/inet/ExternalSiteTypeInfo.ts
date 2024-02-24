import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ExternalSiteValidators from "./ExternalSiteValidators";
import _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class ExternalSiteTypeInfo extends _ExternalSite_stuff_TypeInfo {

    name = "net.bodz.lily.schema.inet.ExternalSite"
    icon = "fa-tag"

    validators = new ExternalSiteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ExternalSiteTypeInfo;
