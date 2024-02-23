import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import CategoryDefValidators from "./CategoryDefValidators";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

// Type Info

export class CategoryDefTypeInfo extends _CategoryDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.CategoryDef"
    icon = "fa-tag"
    label = "Category"

    static validators = new CategoryDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(CategoryDefTypeInfo.declaredProperty);
    }

}

export default CategoryDef;
