import CoTagTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _ShopTag_stuff_Validators from "./_ShopTag_stuff_Validators";

export class _ShopTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "shoptag";

    readonly validators = new _ShopTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _ShopTag_stuff_TypeInfo;

export const _ShopTag_stuff_TYPE = _ShopTag_stuff_TypeInfo.INSTANCE;
