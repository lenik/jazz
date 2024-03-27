import VApi from "./VApi";
import VApiValidators from "./VApiValidators";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class VApiTypeInfo extends _VApi_stuff_TypeInfo {

    readonly validators = new VApiValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VApi"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new VApi();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VApiTypeInfo();

}

export default VApiTypeInfo;
