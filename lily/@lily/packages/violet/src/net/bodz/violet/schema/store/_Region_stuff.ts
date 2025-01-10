import type { int } from "skel01-core/src/lang/basetype";
import CoNode from "@lily/basic/src/net/bodz/lily/concrete/CoNode";

import type Artifact from "../art/Artifact";
import type ArtifactCategory from "../art/ArtifactCategory";
import type Region from "./Region";
import type RegionCategory from "./RegionCategory";
import type RegionLevel from "./RegionLevel";
import type RegionTag from "./RegionTag";

export class _Region_stuff<this_t> extends CoNode<this_t, int> {

    code?: string;
    path: string;
    height: int;

    forCat?: ArtifactCategory;
    forCatId?: int;

    proto?: Region;
    protoId?: int;

    tag?: RegionTag;
    tagId?: int;

    category: RegionCategory;
    categoryId: int;

    level: RegionLevel;
    levelId: int;

    artifact?: Artifact;
    artifactId?: int;

    material?: Artifact;
    materialId?: int;

    forArt?: Artifact;
    forArtId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Region_stuff;
