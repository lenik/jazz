import FabTrackTestValidators from "./FabTrackTestValidators";
import _FabTrackTest_stuff_TypeInfo from "./_FabTrackTest_stuff_TypeInfo";

export class FabTrackTestTypeInfo extends _FabTrackTest_stuff_TypeInfo {

    readonly validators = new FabTrackTestValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackTest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabTrackTestTypeInfo();

}

export default FabTrackTestTypeInfo;
