import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import CategoryDefValidators from "./CategoryDefValidators";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class CategoryDefTypeInfo extends _CategoryDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.CategoryDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Category"; }

    validators = new CategoryDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CategoryDefTypeInfo;
