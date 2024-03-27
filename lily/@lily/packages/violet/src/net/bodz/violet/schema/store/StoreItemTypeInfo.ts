import StoreItem from "./StoreItem";
import StoreItemValidators from "./StoreItemValidators";
import _StoreItem_stuff_TypeInfo from "./_StoreItem_stuff_TypeInfo";

export class StoreItemTypeInfo extends _StoreItem_stuff_TypeInfo {

    readonly validators = new StoreItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StoreItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new StoreItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new StoreItemTypeInfo();

}

export default StoreItemTypeInfo;
