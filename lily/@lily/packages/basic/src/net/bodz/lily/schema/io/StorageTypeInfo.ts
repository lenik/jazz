import StorageValidators from "./StorageValidators";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class StorageTypeInfo extends _Storage_stuff_TypeInfo {

    readonly validators = new StorageValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.io.Storage"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new StorageTypeInfo();

}

export default StorageTypeInfo;
