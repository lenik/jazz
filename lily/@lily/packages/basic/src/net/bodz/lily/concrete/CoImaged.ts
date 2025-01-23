import { int } from 'skel01-core/src/lang/basetype';
import IdEntity from './IdEntity';
import Attachment from 'skel01-core/src/net/bodz/lily/entity/Attachment';
import CoImagedTypeInfo from './CoImagedTypeInfo';

export abstract class CoImaged<Id> extends IdEntity<Id> {

    static _typeInfo: CoImagedTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoImagedTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    properties: any
    files: any

    constructor(o: any) {
        super(o);
    }

    get images(): Attachment[] | undefined {
        let props = (this as any).files;
        return props?.images;
    }

    set images(val: Attachment[] | undefined) {
        let props = (this as any).files;
        if (val == null) {
            if (props != null)
                delete props.images;
        } else {
            if (props == null)
                (this as any).files = { images: val };
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

export default CoImaged;