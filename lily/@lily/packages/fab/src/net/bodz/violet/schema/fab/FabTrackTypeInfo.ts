import FabTrack from "./FabTrack";
import FabTrackValidators from "./FabTrackValidators";
import _FabTrack_stuff_TypeInfo from "./_FabTrack_stuff_TypeInfo";

export class FabTrackTypeInfo extends _FabTrack_stuff_TypeInfo {

    readonly validators = new FabTrackValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrack"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabTrack();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabTrackTypeInfo();

}

export default FabTrackTypeInfo;
