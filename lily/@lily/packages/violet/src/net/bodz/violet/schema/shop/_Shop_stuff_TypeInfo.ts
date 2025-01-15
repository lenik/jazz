import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";
import { Organization_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import _Shop_stuff_Validators from "./_Shop_stuff_Validators";

export class _Shop_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "shop";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_SUPPLIER_ORG_ID = "supplierorg";
    static readonly FIELD_SUPPLIER_ID = "supplier";
    static readonly FIELD_HYDM = "hydm";

    static readonly N_CODE = 30;
    static readonly N_SUPPLIER_ORG_ID = 10;
    static readonly N_SUPPLIER_ID = 10;
    static readonly N_HYDM = 10;

    readonly validators = new _Shop_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.shop.Shop"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            hydm: property({ type: INT, precision: 10, validator: this.validators.validateHydm }),

            supplier: property({ type: Person_TYPE, validator: this.validators.validateSupplier }),
            supplierId: property({ type: INT, precision: 10 }),

            supplierOrg: property({ type: Organization_TYPE, validator: this.validators.validateSupplierOrg }),
            supplierOrgId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Shop_stuff_TypeInfo();

}

export default _Shop_stuff_TypeInfo;

export const _Shop_stuff_TYPE = _Shop_stuff_TypeInfo.INSTANCE;
