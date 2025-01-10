<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { double, int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import Plan from "./Plan";
import { _Plan_stuff_TYPE } from "./_Plan_stuff_TypeInfo";

export const title = "Editor view of: Plan";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoMessageFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import PlanCategoryChooseDialog from "./PlanCategoryChooseDialog.vue";
import PlanPhaseChooseDialog from "./PlanPhaseChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Plan>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Plan.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const planCategoryChooseDialog = ref<InstanceType<typeof PlanCategoryChooseDialog>>();
const planPhaseChooseDialog = ref<InstanceType<typeof PlanPhaseChooseDialog>>();
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
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoMessageFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Plan_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.readCount" v-model="model.readCount">
                <input type="number" v-model="model.readCount" />
            </FieldRow>
            <FieldRow :property="meta.voteCount" v-model="model.voteCount">
                <input type="number" v-model="model.voteCount" />
            </FieldRow>
            <FieldRow :property="meta.nlike" v-model="model.nlike">
                <input type="number" v-model="model.nlike" />
            </FieldRow>
            <FieldRow :property="meta.value" v-model="model.value">
                <input type="number" v-model="model.value" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="planCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.phase" v-model="model.phase">
                <RefEditor :dialog="planPhaseChooseDialog" v-model="model.phase" v-model:id="model.phaseId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <PlanCategoryChooseDialog ref="planCategoryChooseDialog" />
    <PlanPhaseChooseDialog ref="planPhaseChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
