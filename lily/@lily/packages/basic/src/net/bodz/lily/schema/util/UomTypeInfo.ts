import UomValidators from "./UomValidators";
import _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class UomTypeInfo extends _Uom_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.util.Uom"; }
    get icon() { return "fa-tag"; }

    validators = new UomValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default UomTypeInfo;
