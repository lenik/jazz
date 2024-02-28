import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ZoneCategoryValidators from "./ZoneCategoryValidators";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class ZoneCategoryTypeInfo extends _ZoneCategory_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.geo.ZoneCategory"; }
    get icon() { return "fa-tag"; }

    validators = new ZoneCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ZoneCategoryTypeInfo;
