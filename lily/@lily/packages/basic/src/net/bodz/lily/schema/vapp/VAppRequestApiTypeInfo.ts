import VAppRequestApiValidators from "./VAppRequestApiValidators";
import _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class VAppRequestApiTypeInfo extends _VAppRequestApi_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VAppRequestApi"; }
    get icon() { return "fa-tag"; }

    validators = new VAppRequestApiValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default VAppRequestApiTypeInfo;
