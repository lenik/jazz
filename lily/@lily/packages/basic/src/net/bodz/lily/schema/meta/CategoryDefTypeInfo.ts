import CategoryDefValidators from "./CategoryDefValidators";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class CategoryDefTypeInfo extends _CategoryDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.CategoryDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Category"; }

    validators = new CategoryDefValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default CategoryDefTypeInfo;
