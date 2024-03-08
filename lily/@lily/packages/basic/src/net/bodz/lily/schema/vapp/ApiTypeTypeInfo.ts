import ApiTypeValidators from "./ApiTypeValidators";
import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class ApiTypeTypeInfo extends _ApiType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.ApiType"; }
    get icon() { return "fa-tag"; }

    validators = new ApiTypeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ApiTypeTypeInfo;
