import IdEntityValidators from './IdEntityValidators';
import CoImagedTypeInfo from './CoImagedTypeInfo';
import Attachment from 'skel01-core/src/net/bodz/lily/entity/Attachment';

export class CoImagedValidators extends IdEntityValidators {

    constructor(type: CoImagedTypeInfo) {
        super(type);
    }

    validateProperties(val: any) {
        if (val == null) return;
        this.validateImages(val.images);
    }

    validateImages(val: Attachment[]) {
        if (val == null) return;
        for (let item of val)
            this.validateImage(item);
    }

    validateImage(val: Attachment) {
        if (val.name != null) {
            if (val.name.includes('/'))
                throw new Error("name can't contains slash(/).");
        }
    }

    validateVideos(val: Attachment[]) {
        if (val == null) return;
        for (let item of val)
            this.validateVideo(item);
    }

    validateVideo(val: Attachment) {
        if (val.name != null) {
            if (val.name.includes('/'))
                throw new Error("name can't contains slash(/).");
        }
    }

}

export default CoImagedValidators;