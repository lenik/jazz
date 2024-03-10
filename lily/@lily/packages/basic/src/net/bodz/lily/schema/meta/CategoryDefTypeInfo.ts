import CategoryDefValidators from "./CategoryDefValidators";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class CategoryDefTypeInfo extends _CategoryDef_stuff_TypeInfo {

    readonly validators = new CategoryDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.CategoryDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Category"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CategoryDefTypeInfo();

}

export default CategoryDefTypeInfo;
