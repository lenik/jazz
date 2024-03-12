import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT, LIST } from '@skeljs/core/src/lang/baseinfo';
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoImagedMomentIntervalValidators from './CoImagedMomentIntervalValidators';
import Attachment from '@skeljs/core/src/net/bodz/lily/entity/Attachment';
import CoMomentIntervalTypeInfo from './CoMomentIntervalTypeInfo';

export class CoImagedMomentIntervalTypeInfo extends CoMomentIntervalTypeInfo {

    readonly idType: TypeInfo<any>;
    readonly validators = new CoImagedMomentIntervalValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoImagedMomentInterval"; }
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

}

export default CoImagedMomentIntervalTypeInfo;