import { Moment } from 'moment-timezone';
import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from '../../src/net/bodz/lily/concrete/IdEntity';
import PartyTypeInfo from './PartyTypeInfo'
import Contact from './Contact';
import Attachment from '@skeljs/core/src/net/bodz/lily/entity/Attachment';

export abstract class Party extends IdEntity<int> {

    static readonly TYPE = new PartyTypeInfo();

    category?: any
    birthday?: Moment

    locale: string
    timeZoneId: string

    peer: boolean = false
    custoemr: boolean = false
    supplier: boolean = false

    tags: string[]

    subject?: string
    contacts: Contact[]

    bank?: string
    account?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }

    get images(): Attachment[] | undefined {
        let props = (this as any).properties;
        return props?.images;
    }

    set images(val: Attachment[] | undefined) {
        let props = (this as any).properties;
        if (val == null) {
            if (props != null)
                delete props.images;
        } else {
            if (props == null)
                (this as any).properties = { images: val };
            else
                props.images = val;
        }
    }

    get image(): Attachment | undefined {
        if (this.images == null)
            return undefined;
        else
            return this.images[0];
    }

    set image(val: Attachment | undefined) {
        if (val == null) {
            if (this.images != null)
                this.images.splice(0, 1);
        } else {
            if (this.images == null)
                this.images = [val];
            else
                this.images[0] = val;
        }
    }

}