<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import FabStdProcess from "./FabStdProcess";
import _FabStdProcess_stuff from "./_FabStdProcess_stuff";

export const title = "Editor view of: Fab std process";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import ArtifactModelChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelChooseDialog.vue";

import FabFnChooseDialog from "./FabFnChooseDialog.vue";
import FabStdTestChooseDialog from "./FabStdTestChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabStdProcess>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabStdProcess.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactModelChooseDialog = ref<InstanceType<typeof ArtifactModelChooseDialog>>();
const fabFnChooseDialog = ref<InstanceType<typeof FabFnChooseDialog>>();
const fabStdTestChooseDialog = ref<InstanceType<typeof FabStdTestChooseDialog>>();
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
        <FieldGroup :type="_FabStdProcess_stuff.TYPE">
            <FieldRow :property="meta.valid" v-model="model.valid">
                <input type="checkbox" v-model="model.valid" />
            </FieldRow>
            <FieldRow :property="meta.validSince" v-model="model.validSince">
                <DateTime v-model="model.validSince" />
            </FieldRow>
            <FieldRow :property="meta.validUntil" v-model="model.validUntil">
                <DateTime v-model="model.validUntil" />
            </FieldRow>
            <FieldRow :property="meta.duration" v-model="model.duration">
                <input type="number" v-model="model.duration" />
            </FieldRow>
            <FieldRow :property="meta.strict" v-model="model.strict">
                <input type="checkbox" v-model="model.strict" />
            </FieldRow>
            <FieldRow :property="meta.output" v-model="model.output">
                <RefEditor :dialog="artifactModelChooseDialog" v-model="model.output" v-model:id="model.outputId" />
            </FieldRow>
            <FieldRow :property="meta.function" v-model="model.function">
                <RefEditor :dialog="fabFnChooseDialog" v-model="model.function" v-model:id="model.functionId" />
            </FieldRow>
            <FieldRow :property="meta.test" v-model="model.test">
                <RefEditor :dialog="fabStdTestChooseDialog" v-model="model.test" v-model:id="model.testId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactModelChooseDialog ref="artifactModelChooseDialog" />
    <FabFnChooseDialog ref="fabFnChooseDialog" />
    <FabStdTestChooseDialog ref="fabStdTestChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
