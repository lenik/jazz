<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import CoNode from "@lily/basic/src/net/bodz/lily/concrete/CoNode";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import Region from "./Region";
import _Region_stuff from "./_Region_stuff";

export const title = "Editor view of: Region";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import ArtifactCategoryChooseDialog from "../art/ArtifactCategoryChooseDialog.vue";
import ArtifactChooseDialog from "../art/ArtifactChooseDialog.vue";
import RegionCategoryChooseDialog from "./RegionCategoryChooseDialog.vue";
import RegionChooseDialog from "./RegionChooseDialog.vue";
import RegionLevelChooseDialog from "./RegionLevelChooseDialog.vue";
import RegionTagChooseDialog from "./RegionTagChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Region>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Region.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const regionChooseDialog = ref<InstanceType<typeof RegionChooseDialog>>();
const regionLevelChooseDialog = ref<InstanceType<typeof RegionLevelChooseDialog>>();
const regionCategoryChooseDialog = ref<InstanceType<typeof RegionCategoryChooseDialog>>();
const regionTagChooseDialog = ref<InstanceType<typeof RegionTagChooseDialog>>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
const artifactCategoryChooseDialog = ref<InstanceType<typeof ArtifactCategoryChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="CoNode.TYPE">
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="regionChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_Region_stuff.TYPE">
            <FieldRow :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow :property="meta.path" v-model="model.path">
                <input type="text" v-model="model.path" />
            </FieldRow>
            <FieldRow :property="meta.height" v-model="model.height">
                <input type="number" v-model="model.height" />
            </FieldRow>
            <FieldRow :property="meta.proto" v-model="model.proto">
                <RefEditor :dialog="regionChooseDialog" v-model="model.proto" v-model:id="model.protoId" />
            </FieldRow>
            <FieldRow :property="meta.level" v-model="model.level">
                <RefEditor :dialog="regionLevelChooseDialog" v-model="model.level" v-model:id="model.levelId" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="regionCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.tag" v-model="model.tag">
                <RefEditor :dialog="regionTagChooseDialog" v-model="model.tag" v-model:id="model.tagId" />
            </FieldRow>
            <FieldRow :property="meta.material" v-model="model.material">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.material" v-model:id="model.materialId" />
            </FieldRow>
            <FieldRow :property="meta.forCat" v-model="model.forCat">
                <RefEditor :dialog="artifactCategoryChooseDialog" v-model="model.forCat" v-model:id="model.forCatId" />
            </FieldRow>
            <FieldRow :property="meta.forArt" v-model="model.forArt">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.forArt" v-model:id="model.forArtId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <RegionChooseDialog ref="regionChooseDialog" />
    <RegionLevelChooseDialog ref="regionLevelChooseDialog" />
    <RegionCategoryChooseDialog ref="regionCategoryChooseDialog" />
    <RegionTagChooseDialog ref="regionTagChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
    <ArtifactCategoryChooseDialog ref="artifactCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
