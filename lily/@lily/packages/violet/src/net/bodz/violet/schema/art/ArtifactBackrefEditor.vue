<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int, long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import ArtifactBackref from "./ArtifactBackref";
import { _ArtifactBackref_stuff_TYPE } from "./_ArtifactBackref_stuff_TypeInfo";

export const title = "Editor view of: Artifact backref";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import ExternalSiteChooseDialog from "@lily/basic/src/net/bodz/lily/schema/inet/ExternalSiteChooseDialog.vue";

import ArtifactChooseDialog from "./ArtifactChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArtifactBackref>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArtifactBackref.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
const externalSiteChooseDialog = ref<InstanceType<typeof ExternalSiteChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArtifactBackref_stuff_TYPE">
            <FieldRow :property="meta.key" v-model="model.key">
                <input type="text" v-model="model.key" />
            </FieldRow>
            <FieldRow :property="meta.value" v-model="model.value">
                <input type="number" v-model="model.value" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
            <FieldRow :property="meta.site" v-model="model.site">
                <RefEditor :dialog="externalSiteChooseDialog" v-model="model.site" v-model:id="model.siteId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactChooseDialog ref="artifactChooseDialog" />
    <ExternalSiteChooseDialog ref="externalSiteChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
