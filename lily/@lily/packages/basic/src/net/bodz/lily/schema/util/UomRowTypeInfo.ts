import UomRow from "./UomRow";
import UomRowValidators from "./UomRowValidators";
import _UomRow_stuff_TypeInfo from "./_UomRow_stuff_TypeInfo";

export class UomRowTypeInfo extends _UomRow_stuff_TypeInfo {

    readonly validators = new UomRowValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.util.UomRow"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new UomRow();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UomRowTypeInfo();

}

export default UomRowTypeInfo;

export const UomRow_TYPE = UomRowTypeInfo.INSTANCE;
