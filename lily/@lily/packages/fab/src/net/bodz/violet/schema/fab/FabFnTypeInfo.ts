import FabFn from "./FabFn";
import FabFnValidators from "./FabFnValidators";
import _FabFn_stuff_TypeInfo from "./_FabFn_stuff_TypeInfo";

export class FabFnTypeInfo extends _FabFn_stuff_TypeInfo {

    readonly validators = new FabFnValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabFn"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabFn();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabFnTypeInfo();

}

export default FabFnTypeInfo;

export const FabFn_TYPE = FabFnTypeInfo.INSTANCE;
