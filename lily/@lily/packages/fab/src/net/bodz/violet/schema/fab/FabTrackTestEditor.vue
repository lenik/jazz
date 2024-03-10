<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import StructRow from "@lily/basic/src/net/bodz/lily/concrete/StructRow";

import FabTrackTest from "./FabTrackTest";
import _FabTrackTest_stuff from "./_FabTrackTest_stuff";

export const title = "Editor view of: Fab track test";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import FabStdTestChooseDialog from "./FabStdTestChooseDialog.vue";
import FabTrackChooseDialog from "./FabTrackChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabTrackTest>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabTrackTest.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabTrackChooseDialog = ref<InstanceType<typeof FabTrackChooseDialog>>();
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
        <FieldGroup :type="StructRow.TYPE">
            <FieldRow :property="meta.creationDate" v-model="model.creationDate">
                <DateTime v-model="model.creationDate" />
            </FieldRow>
            <FieldRow :property="meta.lastModified" v-model="model.lastModified">
                <DateTime v-model="model.lastModified" />
            </FieldRow>
            <FieldRow :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabTrackTest_stuff.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow :property="meta.valid" v-model="model.valid">
                <input type="checkbox" v-model="model.valid" />
            </FieldRow>
            <FieldRow :property="meta.track" v-model="model.track">
                <RefEditor :dialog="fabTrackChooseDialog" v-model="model.track" v-model:id="model.trackId" />
            </FieldRow>
            <FieldRow :property="meta.standard" v-model="model.standard">
                <RefEditor :dialog="fabStdTestChooseDialog" v-model="model.standard" v-model:id="model.standardId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabTrackChooseDialog ref="fabTrackChooseDialog" />
    <FabStdTestChooseDialog ref="fabStdTestChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
