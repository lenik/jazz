import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoCategoryTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoCategoryTypeInfo";

import _ShopItemCategory_stuff_Validators from "./_ShopItemCategory_stuff_Validators";

export class _ShopItemCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "shopitemcat";

    static readonly FIELD_NAME = "name";

    static readonly N_NAME = 30;

    readonly validators = new _ShopItemCategory_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.shop.ShopItemCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        });
    }

}

export default _ShopItemCategory_stuff_TypeInfo;

export const _ShopItemCategory_stuff_TYPE = _ShopItemCategory_stuff_TypeInfo.INSTANCE;
