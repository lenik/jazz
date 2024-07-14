import UomTypeInfo from "./UomTypeInfo";
import _UomRow_stuff_Validators from "./_UomRow_stuff_Validators";

export class _UomRow_stuff_TypeInfo extends UomTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "uom";

    readonly validators = new _UomRow_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.util.UomRow"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new _UomRow_stuff_TypeInfo();

}

export default _UomRow_stuff_TypeInfo;

export const _UomRow_stuff_TYPE = _UomRow_stuff_TypeInfo.INSTANCE;
