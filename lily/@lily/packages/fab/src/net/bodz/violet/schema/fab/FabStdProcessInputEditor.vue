<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabStdProcessInput from "./FabStdProcessInput";
import { _FabStdProcessInput_stuff_TYPE } from "./_FabStdProcessInput_stuff_TypeInfo";

export const title = "Editor view of: Fab std process input";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import ArtifactChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactChooseDialog.vue";
import ArtifactModelChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelChooseDialog.vue";

import FabStdProcessChooseDialog from "./FabStdProcessChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabStdProcessInput>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabStdProcessInput.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabStdProcessChooseDialog = ref<InstanceType<typeof FabStdProcessChooseDialog>>();
const artifactModelChooseDialog = ref<InstanceType<typeof ArtifactModelChooseDialog>>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabStdProcessInput_stuff_TYPE">
            <FieldRow :property="meta.quantity" v-model="model.quantity">
                <input type="number" v-model="model.quantity" />
            </FieldRow>
            <FieldRow :property="meta.process" v-model="model.process">
                <RefEditor :dialog="fabStdProcessChooseDialog" v-model="model.process" v-model:id="model.processId" />
            </FieldRow>
            <FieldRow :property="meta.model" v-model="model.model">
                <RefEditor :dialog="artifactModelChooseDialog" v-model="model.model" v-model:id="model.modelId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabStdProcessChooseDialog ref="fabStdProcessChooseDialog" />
    <ArtifactModelChooseDialog ref="artifactModelChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
