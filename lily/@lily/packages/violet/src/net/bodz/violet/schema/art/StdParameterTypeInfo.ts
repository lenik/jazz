import StdParameter from "./StdParameter";
import StdParameterValidators from "./StdParameterValidators";
import _StdParameter_stuff_TypeInfo from "./_StdParameter_stuff_TypeInfo";

export class StdParameterTypeInfo extends _StdParameter_stuff_TypeInfo {

    readonly validators = new StdParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.StdParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new StdParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new StdParameterTypeInfo();

}

export default StdParameterTypeInfo;

export const StdParameter_TYPE = StdParameterTypeInfo.INSTANCE;
