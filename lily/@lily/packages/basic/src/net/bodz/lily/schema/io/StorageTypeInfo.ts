import StorageValidators from "./StorageValidators";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class StorageTypeInfo extends _Storage_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.io.Storage"; }
    get icon() { return "fa-tag"; }

    validators = new StorageValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default StorageTypeInfo;
