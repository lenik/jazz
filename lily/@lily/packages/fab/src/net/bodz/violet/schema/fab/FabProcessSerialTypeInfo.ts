import FabProcessSerial from "./FabProcessSerial";
import FabProcessSerialValidators from "./FabProcessSerialValidators";
import _FabProcessSerial_stuff_TypeInfo from "./_FabProcessSerial_stuff_TypeInfo";

export class FabProcessSerialTypeInfo extends _FabProcessSerial_stuff_TypeInfo {

    readonly validators = new FabProcessSerialValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabProcessSerial"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabProcessSerial();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabProcessSerialTypeInfo();

}

export default FabProcessSerialTypeInfo;

export const FabProcessSerial_TYPE = FabProcessSerialTypeInfo.INSTANCE;
