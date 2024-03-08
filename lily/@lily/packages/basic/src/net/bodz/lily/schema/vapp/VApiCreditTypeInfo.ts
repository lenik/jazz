import VApiCreditValidators from "./VApiCreditValidators";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class VApiCreditTypeInfo extends _VApiCredit_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VApiCredit"; }
    get icon() { return "fa-tag"; }

    validators = new VApiCreditValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default VApiCreditTypeInfo;
