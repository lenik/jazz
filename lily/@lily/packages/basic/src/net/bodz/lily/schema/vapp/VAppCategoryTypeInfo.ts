import VAppCategory from "./VAppCategory";
import VAppCategoryValidators from "./VAppCategoryValidators";
import _VAppCategory_stuff_TypeInfo from "./_VAppCategory_stuff_TypeInfo";

export class VAppCategoryTypeInfo extends _VAppCategory_stuff_TypeInfo {

    readonly validators = new VAppCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VAppCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new VAppCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VAppCategoryTypeInfo();

}

export default VAppCategoryTypeInfo;

export const VAppCategory_TYPE = VAppCategoryTypeInfo.INSTANCE;
