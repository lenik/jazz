import VApiCredit from "./VApiCredit";
import VApiCreditValidators from "./VApiCreditValidators";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class VApiCreditTypeInfo extends _VApiCredit_stuff_TypeInfo {

    readonly validators = new VApiCreditValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VApiCredit"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new VApiCredit();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new VApiCreditTypeInfo();

}

export default VApiCreditTypeInfo;
