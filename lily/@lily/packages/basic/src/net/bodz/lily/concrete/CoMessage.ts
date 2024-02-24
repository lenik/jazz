import { Moment } from 'moment';
import { integer } from '@skeljs/core/src/lang/type';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoMomentInterval from '@skeljs/dba/src/net/bodz/lily/concrete/CoMomentInterval';
import User from '../schema/account/User';
import FormDef from '../schema//meta/FormDef';
// import UserClickInfo from '../schema/account/UserClickInfo';

export abstract class CoMessage<Id> extends CoMomentInterval<Id> {
    static TYPE = new CoMessageTypeInfo();

    op: User
    subject: string
    rawText: string
    form: FormDef
    parameters: any
    clickInfo: any // UserClickInfo

    sentTime?: Moment
    receivedTime?: Moment

    constructor(o: any) {
        super(o);
    }
}

export default CoMessage;