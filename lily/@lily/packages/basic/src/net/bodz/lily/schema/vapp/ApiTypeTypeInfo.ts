import ApiType from "./ApiType";
import ApiTypeValidators from "./ApiTypeValidators";
import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class ApiTypeTypeInfo extends _ApiType_stuff_TypeInfo {

    readonly validators = new ApiTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.ApiType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ApiType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ApiTypeTypeInfo();

}

export default ApiTypeTypeInfo;

export const ApiType_TYPE = ApiTypeTypeInfo.INSTANCE;
