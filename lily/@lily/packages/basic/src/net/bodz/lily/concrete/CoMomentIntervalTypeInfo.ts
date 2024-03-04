import { Moment } from "moment-timezone";
import { EntityPropertyMap, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoMomentIntervalValidators from './CoMomentIntervalValidators';

export class CoMomentIntervalTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoMomentInterval"; }
    get icon() { return "fa-calendar-alt"; }
    get label() { return "Moment Interval"; }
    get description() { return "A moment interval refers to a specific period of time or duration. It can vary depending on the context in which it is used. For example, in music theory, a moment interval may refer to the distance between two notes or chords. In physics, it may refer to a brief moment in time during an event or process. Overall, a moment interval is simply a measure of time that is being referenced for a particular purpose."; }

    validators = new CoMomentIntervalValidators(this);

    declaredProperty: EntityPropertyMap = {
        beginTime: property({ type: "Moment", icon: "far-clock" }),
        endTime: property({ type: "Moment", icon: "far-clock" }),
        year: property({ type: "number", icon: "far-calendar" }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoMomentIntervalTypeInfo;