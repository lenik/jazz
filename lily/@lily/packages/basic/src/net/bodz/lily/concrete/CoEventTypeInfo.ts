import { property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoEventValidators from './CoEventValidators';
import TypeInfo from "skel01-core/src/lang/TypeInfo";
import { INT, UNDEFINED } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";

export class CoEventTypeInfo extends IdEntityTypeInfo {

    readonly validators = new CoEventValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoEvent"; }
    get icon() { return "fa-calendar-alt"; }
    get label() { return "Moment Interval"; }
    get description() { return "A moment interval refers to a specific period of time or duration. It can vary depending on the context in which it is used. For example, in music theory, a moment interval may refer to the distance between two notes or chords. In physics, it may refer to a brief moment in time during an event or process. Overall, a moment interval is simply a measure of time that is being referenced for a particular purpose."; }

    override preamble() {
        super.preamble();
        this.declare({
            beginTime: property({ type: OffsetDateTime.TYPE, icon: "far-clock" }),
            endTime: property({ type: OffsetDateTime.TYPE, icon: "far-clock" }),
            year: property({ type: INT, icon: "far-calendar" }),
        });
    }

    static readonly INSTANCE = new CoEventTypeInfo(UNDEFINED);

}

export default CoEventTypeInfo;
export const CoEvent_TYPE = CoEventTypeInfo.INSTANCE;
