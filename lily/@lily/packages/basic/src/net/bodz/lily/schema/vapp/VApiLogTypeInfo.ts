import VApiLogValidators from "./VApiLogValidators";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class VApiLogTypeInfo extends _VApiLog_stuff_TypeInfo {

    readonly validators = new VApiLogValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VApiLog"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VApiLogTypeInfo();

}

export default VApiLogTypeInfo;
