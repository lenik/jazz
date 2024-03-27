import OffStoreItem from "./OffStoreItem";
import OffStoreItemValidators from "./OffStoreItemValidators";
import _OffStoreItem_stuff_TypeInfo from "./_OffStoreItem_stuff_TypeInfo";

export class OffStoreItemTypeInfo extends _OffStoreItem_stuff_TypeInfo {

    readonly validators = new OffStoreItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.OffStoreItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new OffStoreItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new OffStoreItemTypeInfo();

}

export default OffStoreItemTypeInfo;
