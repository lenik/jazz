import VApiLogValidators from "./VApiLogValidators";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class VApiLogTypeInfo extends _VApiLog_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VApiLog"; }
    get icon() { return "fa-tag"; }

    validators = new VApiLogValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default VApiLogTypeInfo;
