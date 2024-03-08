import VApiValidators from "./VApiValidators";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class VApiTypeInfo extends _VApi_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VApi"; }
    get icon() { return "fa-tag"; }

    validators = new VApiValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default VApiTypeInfo;
