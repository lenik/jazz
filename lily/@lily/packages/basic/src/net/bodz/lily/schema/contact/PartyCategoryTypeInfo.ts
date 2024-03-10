import PartyCategoryValidators from "./PartyCategoryValidators";
import _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

export class PartyCategoryTypeInfo extends _PartyCategory_stuff_TypeInfo {

    readonly validators = new PartyCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PartyCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PartyCategoryTypeInfo();

}

export default PartyCategoryTypeInfo;
