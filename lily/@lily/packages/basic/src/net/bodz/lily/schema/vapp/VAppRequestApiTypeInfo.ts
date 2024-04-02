import VAppRequestApi from "./VAppRequestApi";
import VAppRequestApiValidators from "./VAppRequestApiValidators";
import _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class VAppRequestApiTypeInfo extends _VAppRequestApi_stuff_TypeInfo {

    readonly validators = new VAppRequestApiValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VAppRequestApi"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new VAppRequestApi();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VAppRequestApiTypeInfo();

}

export default VAppRequestApiTypeInfo;

export const VAppRequestApi_TYPE = VAppRequestApiTypeInfo.INSTANCE;
