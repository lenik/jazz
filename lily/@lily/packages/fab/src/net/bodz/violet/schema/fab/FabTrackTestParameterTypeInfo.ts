import FabTrackTestParameter from "./FabTrackTestParameter";
import FabTrackTestParameterValidators from "./FabTrackTestParameterValidators";
import _FabTrackTestParameter_stuff_TypeInfo from "./_FabTrackTestParameter_stuff_TypeInfo";

export class FabTrackTestParameterTypeInfo extends _FabTrackTestParameter_stuff_TypeInfo {

    readonly validators = new FabTrackTestParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackTestParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabTrackTestParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabTrackTestParameterTypeInfo();

}

export default FabTrackTestParameterTypeInfo;
