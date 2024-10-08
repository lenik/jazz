import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT, LIST, UNDEFINED } from '@skeljs/core/src/lang/baseinfo';
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoImagedEventValidators from './CoImagedEventValidators';
import Attachment from '@skeljs/core/src/net/bodz/lily/entity/Attachment';
import CoEventTypeInfo from './CoEventTypeInfo';

export class CoImagedEventTypeInfo extends CoEventTypeInfo {

    readonly idType: TypeInfo<any>;
    readonly validators = new CoImagedEventValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoImagedEvent"; }
    get icon() { return "fa-image"; }
    get label() { return "Concrete Moment-Interval With Image"; }
    get description() { return "A moment-interval entity with optional one or many images."; }

    override preamble() {
        super.preamble();
        this.declare({
            images: property({ type: LIST(Attachment.TYPE), nullable: true, icon: "far-images" }),
            image: property({ type: Attachment.TYPE, nullable: true, icon: "far-image" }),
        });
    }

    static readonly INSTANCE = new CoImagedEventTypeInfo(UNDEFINED);

}

export default CoImagedEventTypeInfo;
export const CoImagedEvent_TYPE = CoImagedEventTypeInfo.INSTANCE;
