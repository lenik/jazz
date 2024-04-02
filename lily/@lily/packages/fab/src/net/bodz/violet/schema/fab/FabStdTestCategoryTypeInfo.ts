import FabStdTestCategory from "./FabStdTestCategory";
import FabStdTestCategoryValidators from "./FabStdTestCategoryValidators";
import _FabStdTestCategory_stuff_TypeInfo from "./_FabStdTestCategory_stuff_TypeInfo";

export class FabStdTestCategoryTypeInfo extends _FabStdTestCategory_stuff_TypeInfo {

    readonly validators = new FabStdTestCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTestCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabStdTestCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabStdTestCategoryTypeInfo();

}

export default FabStdTestCategoryTypeInfo;

export const FabStdTestCategory_TYPE = FabStdTestCategoryTypeInfo.INSTANCE;
