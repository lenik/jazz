import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ExternalSiteValidators from "./ExternalSiteValidators";
import _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class ExternalSiteTypeInfo extends _ExternalSite_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.inet.ExternalSite"; }
    get icon() { return "fa-tag"; }

    validators = new ExternalSiteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ExternalSiteTypeInfo;
