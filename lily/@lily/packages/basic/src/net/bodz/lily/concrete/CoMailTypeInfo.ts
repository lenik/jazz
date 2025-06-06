import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoMailValidators from './CoMailValidators';
import { ARRAY, BOOLEAN, LONG } from 'skel01-core/src/lang/baseinfo';
import User from '../schema/account/User';

export class CoMailTypeInfo extends CoMessageTypeInfo {

    readonly validators = new CoMailValidators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.concrete.CoMail"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Concrete Mail"; }
    get description() { return "Mail like message, includes recipients, timing, rich text content."; }

    override preamble() {
        super.preamble();
        this.declare({
            recipient: property({ type: User.TYPE, icon: "far-user", }),
            recipients: property({ type: ARRAY(User.TYPE), icon: "far - users", }),
            bcc: property({ type: ARRAY(User.TYPE), icon: "far-users", }),
            read: property({ type: BOOLEAN, icon: "far-eye", }),
        });
    }

    static readonly INSTANCE = new CoMailTypeInfo();

}

export default CoMailTypeInfo;
export const CoMail_TYPE = CoMailTypeInfo.INSTANCE;
