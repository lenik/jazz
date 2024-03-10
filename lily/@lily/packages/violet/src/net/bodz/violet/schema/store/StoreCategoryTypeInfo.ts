import StoreCategoryValidators from "./StoreCategoryValidators";
import _StoreCategory_stuff_TypeInfo from "./_StoreCategory_stuff_TypeInfo";

export class StoreCategoryTypeInfo extends _StoreCategory_stuff_TypeInfo {

    readonly validators = new StoreCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StoreCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new StoreCategoryTypeInfo();

}

export default StoreCategoryTypeInfo;
