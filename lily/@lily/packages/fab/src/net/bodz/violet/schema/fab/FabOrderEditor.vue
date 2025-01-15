<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabOrder from "./FabOrder";
import { _FabOrder_stuff_TYPE } from "./_FabOrder_stuff_TypeInfo";

export const title = "Editor view of: Fab order";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoMessageFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import OrganizationChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";
import PersonChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";
import PlanChooseDialog from "lily-violet/src/net/bodz/violet/schema/plan/PlanChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabOrder>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabOrder.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const planChooseDialog = ref<InstanceType<typeof PlanChooseDialog>>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
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
        <FieldGroup :type="_FabOrder_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.nitem" v-model="model.nitem">
                <input type="number" v-model="model.nitem" />
            </FieldRow>
            <FieldRow :property="meta.totalQuantity" v-model="model.totalQuantity">
                <input type="number" v-model="model.totalQuantity" />
            </FieldRow>
            <FieldRow :property="meta.totalAmount" v-model="model.totalAmount">
                <input type="number" v-model="model.totalAmount" />
            </FieldRow>
            <FieldRow :property="meta.taskCount" v-model="model.taskCount">
                <input type="number" v-model="model.taskCount" />
            </FieldRow>
            <FieldRow :property="meta.processCount" v-model="model.processCount">
                <input type="number" v-model="model.processCount" />
            </FieldRow>
            <FieldRow :property="meta.trackCount" v-model="model.trackCount">
                <input type="number" v-model="model.trackCount" />
            </FieldRow>
            <FieldRow :property="meta.plan" v-model="model.plan">
                <RefEditor :dialog="planChooseDialog" v-model="model.plan" v-model:id="model.planId" />
            </FieldRow>
            <FieldRow :property="meta.customOrg" v-model="model.customOrg">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.customOrg" v-model:id="model.customOrgId" />
            </FieldRow>
            <FieldRow :property="meta.custom" v-model="model.custom">
                <RefEditor :dialog="personChooseDialog" v-model="model.custom" v-model:id="model.customId" />
            </FieldRow>
            <FieldRow :property="meta.clerk" v-model="model.clerk">
                <RefEditor :dialog="personChooseDialog" v-model="model.clerk" v-model:id="model.clerkId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <PlanChooseDialog ref="planChooseDialog" />
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
