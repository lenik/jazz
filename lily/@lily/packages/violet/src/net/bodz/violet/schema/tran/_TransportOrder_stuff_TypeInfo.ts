import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";

import { SalesOrder_TYPE } from "../shop/SalesOrderTypeInfo";
import { StoreOrder_TYPE } from "../store/StoreOrderTypeInfo";
import AbstractTransportOrderTypeInfo from "./AbstractTransportOrderTypeInfo";
import { TransportCategory_TYPE } from "./TransportCategoryTypeInfo";
import { TransportOrder_TYPE } from "./TransportOrderTypeInfo";
import { TransportPhase_TYPE } from "./TransportPhaseTypeInfo";
import _TransportOrder_stuff_Validators from "./_TransportOrder_stuff_Validators";

export class _TransportOrder_stuff_TypeInfo extends AbstractTransportOrderTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "tranodr";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_PREV_ID = "prev";
    static readonly FIELD_SALES_ORDER_ID = "saleodr";
    static readonly FIELD_STOREODR_ID = "storeodr";
    static readonly FIELD_SHIPPER_ID = "shipper";
    static readonly FIELD_SHIPCOST = "shipcost";
    static readonly FIELD_LENGTH = "length";
    static readonly FIELD_TOTAL_QUANTITY = "sum_qty";
    static readonly FIELD_TOTAL_AMOUNT = "sum_amount";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PHASE_ID = 10;
    static readonly N_PREV_ID = 19;
    static readonly N_SALES_ORDER_ID = 19;
    static readonly N_STOREODR_ID = 19;
    static readonly N_SHIPPER_ID = 10;
    static readonly N_SHIPCOST = 20;
    static readonly N_LENGTH = 10;
    static readonly N_TOTAL_QUANTITY = 20;
    static readonly N_TOTAL_AMOUNT = 20;

    readonly validators = new _TransportOrder_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportOrder"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            shipcost: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateShipcost }),
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            totalQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalQuantity }),
            totalAmount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalAmount }),

            prev: property({ type: this, validator: this.validators.validatePrev }),
            prevId: property({ type: LONG, precision: 19 }),

            shipper: property({ type: Organization_TYPE, validator: this.validators.validateShipper }),
            shipperId: property({ type: INT, precision: 10 }),

            category: property({ type: TransportCategory_TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),

            salesOrder: property({ type: SalesOrder_TYPE, validator: this.validators.validateSalesOrder }),
            salesOrderId: property({ type: LONG, precision: 19 }),

            phase: property({ type: TransportPhase_TYPE, nullable: false, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, nullable: false, precision: 10 }),

            storeodr: property({ type: StoreOrder_TYPE, validator: this.validators.validateStoreodr }),
            storeodrId: property({ type: LONG, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TransportOrder_stuff_TypeInfo();

}

export default _TransportOrder_stuff_TypeInfo;

export const _TransportOrder_stuff_TYPE = _TransportOrder_stuff_TypeInfo.INSTANCE;
