import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ZoneCategoryValidators from "./ZoneCategoryValidators";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class ZoneCategoryTypeInfo extends _ZoneCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.geo.ZoneCategory"
    icon = "fa-tag"

    validators = new ZoneCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ZoneCategoryTypeInfo;
