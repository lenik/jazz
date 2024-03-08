import VAppRequestValidators from "./VAppRequestValidators";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class VAppRequestTypeInfo extends _VAppRequest_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VAppRequest"; }
    get icon() { return "fa-tag"; }

    validators = new VAppRequestValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default VAppRequestTypeInfo;
