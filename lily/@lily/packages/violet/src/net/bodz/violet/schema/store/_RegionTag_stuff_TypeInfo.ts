import CoTagTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _RegionTag_stuff_Validators from "./_RegionTag_stuff_Validators";

export class _RegionTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "regiontag";

    readonly validators = new _RegionTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.RegionTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _RegionTag_stuff_TypeInfo;

export const _RegionTag_stuff_TYPE = _RegionTag_stuff_TypeInfo.INSTANCE;
