import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoEventTypeInfo from './CoEventTypeInfo';
import CoMessageValidators from './CoMessageValidators';
import User from '../schema/account/User';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { MAP, STRING } from '@skeljs/core/src/lang/baseinfo';
import ZonedDateTime from '@skeljs/core/src/lang/time/ZonedDateTime';
import FormDef from '../schema/meta/FormDef';
import ParameterDef from '../schema/meta/ParameterDef';

export class CoMessageTypeInfo extends CoEventTypeInfo {

    readonly validators = new CoMessageValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoMessage"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Concrete Message"; }
    get description() { return "Mail like message, includes timing, rich text content."; }

    override preamble() {
        super.preamble();
        this.declare({
            op: property({ type: User.TYPE, icon: "far-user", }),
            subject: property({ type: STRING, icon: "far-quote", }),
            rawText: property({ type: STRING, icon: "far-text", }),
            form: property({ type: FormDef.TYPE, icon: "fas-align-left", }),
            parameters: property({ type: MAP(ParameterDef.TYPE, STRING), icon: "far-toggle-on", }),
            // clickInfo: property({ type: UserClickInfo, icon: "far-mouse", }),
            sentTime: property({ type: ZonedDateTime.TYPE, icon: "far-clock", }),
            receivedTime: property({ type: ZonedDateTime.TYPE, icon: "far-clock", }),
        });
    }

}

export default CoMessageTypeInfo;