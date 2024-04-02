import FabTrackParty from "./FabTrackParty";
import FabTrackPartyValidators from "./FabTrackPartyValidators";
import _FabTrackParty_stuff_TypeInfo from "./_FabTrackParty_stuff_TypeInfo";

export class FabTrackPartyTypeInfo extends _FabTrackParty_stuff_TypeInfo {

    readonly validators = new FabTrackPartyValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackParty"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabTrackParty();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabTrackPartyTypeInfo();

}

export default FabTrackPartyTypeInfo;

export const FabTrackParty_TYPE = FabTrackPartyTypeInfo.INSTANCE;
