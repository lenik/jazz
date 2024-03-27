import RegionCategory from "./RegionCategory";
import RegionCategoryValidators from "./RegionCategoryValidators";
import _RegionCategory_stuff_TypeInfo from "./_RegionCategory_stuff_TypeInfo";

export class RegionCategoryTypeInfo extends _RegionCategory_stuff_TypeInfo {

    readonly validators = new RegionCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.RegionCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new RegionCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new RegionCategoryTypeInfo();

}

export default RegionCategoryTypeInfo;
