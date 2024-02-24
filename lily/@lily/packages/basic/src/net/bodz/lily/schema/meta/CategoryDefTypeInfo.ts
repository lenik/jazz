import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import CategoryDefValidators from "./CategoryDefValidators";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class CategoryDefTypeInfo extends _CategoryDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.CategoryDef"
    icon = "fa-tag"
    label = "Category"

    validators = new CategoryDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CategoryDefTypeInfo;
