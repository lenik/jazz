import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import CategoryDefValidators from "./CategoryDefValidators";
import _CategoryDef_stuff_Type from "./_CategoryDef_stuff_Type";

// Type Info

export class CategoryDefType extends _CategoryDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.CategoryDef"
    icon = "fa-tag"
    label = "Category"

    static validators = new CategoryDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(CategoryDefType.declaredProperty);
    }

}

export default CategoryDef;
