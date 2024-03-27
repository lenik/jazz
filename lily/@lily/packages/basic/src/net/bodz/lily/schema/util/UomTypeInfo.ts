import Uom from "./Uom";
import UomValidators from "./UomValidators";
import _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class UomTypeInfo extends _Uom_stuff_TypeInfo {

    readonly validators = new UomValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.util.Uom"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Uom();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UomTypeInfo();

}

export default UomTypeInfo;
