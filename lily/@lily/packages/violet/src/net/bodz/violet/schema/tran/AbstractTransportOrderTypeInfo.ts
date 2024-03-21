import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT, LONG } from '@skeljs/core/src/lang/baseinfo';
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from '@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo';
import AbstractTransportOrderValidators from './AbstractTransportOrderValidators';
import Contact from '@lily/basic/src/net/bodz/lily/schema/contact/Contact';

export class AbstractTransportOrderTypeInfo extends CoMessageTypeInfo {

    readonly validators = new AbstractTransportOrderValidators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.tran.AbstractTransportOrder"; }
    get icon() { return "fa-image"; }
    get label() { return "Abstract Transport Order"; }
    get description() { return "with src/dst contacts"; }

    override preamble() {
        super.preamble();
        this.declare({
            src: property({ type: Contact.TYPE, nullable: false, icon: "far-tag" }),
            dst: property({ type: Contact.TYPE, nullable: false, icon: "far-tag" }),
        });
    }

    static readonly INSTANCE = new AbstractTransportOrderTypeInfo();

}

export default AbstractTransportOrderTypeInfo;