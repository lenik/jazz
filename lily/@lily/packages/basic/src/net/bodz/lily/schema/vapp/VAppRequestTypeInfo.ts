import VAppRequestValidators from "./VAppRequestValidators";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class VAppRequestTypeInfo extends _VAppRequest_stuff_TypeInfo {

    readonly validators = new VAppRequestValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VAppRequest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VAppRequestTypeInfo();

}

export default VAppRequestTypeInfo;
