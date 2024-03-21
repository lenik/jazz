import { int } from '@skeljs/core/src/lang/basetype';
import CoEvent from './CoEvent';
import Attachment from '@skeljs/core/src/net/bodz/lily/entity/Attachment';
import CoImagedEventTypeInfo from './CoImagedEventTypeInfo';

export abstract class CoImagedEvent<Id> extends CoEvent<Id> {

    static readonly TYPE = CoImagedEventTypeInfo.INSTANCE;

    properties: any

    constructor(o: any) {
        super(o);
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

export default CoImagedEvent;