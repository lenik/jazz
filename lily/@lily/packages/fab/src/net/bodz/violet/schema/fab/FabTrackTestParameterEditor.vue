<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import FabTrackTestParameter from "./FabTrackTestParameter";
import _FabTrackTestParameter_stuff from "./_FabTrackTestParameter_stuff";

export const title = "Editor view of: Fab track test parameter";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import FabStdTestParameterChooseDialog from "./FabStdTestParameterChooseDialog.vue";
import FabStdTesterChooseDialog from "./FabStdTesterChooseDialog.vue";
import FabTrackTestChooseDialog from "./FabTrackTestChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabTrackTestParameter>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabTrackTestParameter.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabTrackTestChooseDialog = ref<InstanceType<typeof FabTrackTestChooseDialog>>();
const fabStdTestParameterChooseDialog = ref<InstanceType<typeof FabStdTestParameterChooseDialog>>();
const fabStdTesterChooseDialog = ref<InstanceType<typeof FabStdTesterChooseDialog>>();
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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabTrackTestParameter_stuff.TYPE">
            <FieldRow :property="meta.actual" v-model="model.actual">
                <input type="text" v-model="model.actual" />
            </FieldRow>
            <FieldRow :property="meta.valid" v-model="model.valid">
                <input type="checkbox" v-model="model.valid" />
            </FieldRow>
            <FieldRow :property="meta.test" v-model="model.test">
                <RefEditor :dialog="fabTrackTestChooseDialog" v-model="model.test" v-model:id="model.testId" />
            </FieldRow>
            <FieldRow :property="meta.parameter" v-model="model.parameter">
                <RefEditor :dialog="fabStdTestParameterChooseDialog" v-model="model.parameter" v-model:id="model.parameterId" />
            </FieldRow>
            <FieldRow :property="meta.tester" v-model="model.tester">
                <RefEditor :dialog="fabStdTesterChooseDialog" v-model="model.tester" v-model:id="model.testerId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabTrackTestChooseDialog ref="fabTrackTestChooseDialog" />
    <FabStdTestParameterChooseDialog ref="fabStdTestParameterChooseDialog" />
    <FabStdTesterChooseDialog ref="fabStdTesterChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
