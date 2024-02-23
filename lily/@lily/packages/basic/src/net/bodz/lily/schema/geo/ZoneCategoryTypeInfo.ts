import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ZoneCategoryValidators from "./ZoneCategoryValidators";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

// Type Info

export class ZoneCategoryTypeInfo extends _ZoneCategory_stuff_TypeInfo {

    name = "net.bodz.lily.schema.geo.ZoneCategory"
    icon = "fa-tag"

    static validators = new ZoneCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ZoneCategoryTypeInfo.declaredProperty);
    }

}

export default ZoneCategory;
