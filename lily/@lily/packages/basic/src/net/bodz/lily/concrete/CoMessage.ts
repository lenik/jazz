import { Moment } from "moment-timezone";
import { int } from '@skeljs/core/src/lang/basetype';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoMomentInterval from './CoMomentInterval';
import User from '../schema/account/User';
import FormDef from '../schema//meta/FormDef';
// import UserClickInfo from '../schema/account/UserClickInfo';

export abstract class CoMessage<Id> extends CoMomentInterval<Id> {
    // static readonly TYPE = new CoMessageTypeInfo();

    op: User
    subject: string
    rawText: string
    form: FormDef
    parameters: any // map
    clickInfo: any // UserClickInfo

    sentTime?: Moment
    receivedTime?: Moment

    constructor(o: any) {
        super(o);
    }
}

export default CoMessage;