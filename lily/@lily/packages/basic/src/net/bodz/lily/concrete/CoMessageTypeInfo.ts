import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMomentIntervalTypeInfo from './CoMomentIntervalTypeInfo';
import CoMessageValidators from './CoMessageValidators';
import User from '../schema/account/User';
import FormDef from '../schema/meta/FormDef';

export class CoMessageTypeInfo extends CoMomentIntervalTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoMessage"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Concrete Message"; }
    get description() { return "Mail like message, includes timing, rich text content."; }

    validators = new CoMessageValidators(this);

    declaredProperty: EntityPropertyMap = {
        op: property({ type: User, icon: "far-user", }),
        subject: property({ type: "string", icon: "far-quote", }),
        rawText: property({ type: "string", icon: "far-text", }),
        form: property({ type: FormDef, icon: "fas-align-left", }),
        parameters: property({ type: "any", icon: "far-toggle-on", }),
        // clickInfo: property({ type: UserClickInfo, icon: "far-mouse", }),
        sentTime: property({ type: "Moment", icon: "far-clock", }),
        receivedTime: property({ type: "Moment", icon: "far-clock", }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoMessageTypeInfo;