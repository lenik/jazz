import VApp from "./VApp";
import VAppValidators from "./VAppValidators";
import _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class VAppTypeInfo extends _VApp_stuff_TypeInfo {

    readonly validators = new VAppValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VApp"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new VApp();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VAppTypeInfo();

}

export default VAppTypeInfo;
