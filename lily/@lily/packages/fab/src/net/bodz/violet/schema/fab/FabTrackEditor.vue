<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabTrack from "./FabTrack";
import { _FabTrack_stuff_TYPE } from "./_FabTrack_stuff_TypeInfo";

export const title = "Editor view of: Fab track";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import OrgUnitChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnitChooseDialog.vue";

import FabProcessChooseDialog from "./FabProcessChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabTrack>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabTrack.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabProcessChooseDialog = ref<InstanceType<typeof FabProcessChooseDialog>>();
const orgUnitChooseDialog = ref<InstanceType<typeof OrgUnitChooseDialog>>();
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
        <FieldGroup :type="_FabTrack_stuff_TYPE">
            <FieldRow :property="meta.since" v-model="model.since">
                <DateTime v-model="model.since" />
            </FieldRow>
            <FieldRow :property="meta.deadline" v-model="model.deadline">
                <DateTime v-model="model.deadline" />
            </FieldRow>
            <FieldRow :property="meta.plannedQuantity" v-model="model.plannedQuantity">
                <input type="number" v-model="model.plannedQuantity" />
            </FieldRow>
            <FieldRow :property="meta.actualQuantity" v-model="model.actualQuantity">
                <input type="number" v-model="model.actualQuantity" />
            </FieldRow>
            <FieldRow :property="meta.validQuantity" v-model="model.validQuantity">
                <input type="number" v-model="model.validQuantity" />
            </FieldRow>
            <FieldRow :property="meta.process" v-model="model.process">
                <RefEditor :dialog="fabProcessChooseDialog" v-model="model.process" v-model:id="model.processId" />
            </FieldRow>
            <FieldRow :property="meta.orgUnit" v-model="model.orgUnit">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.orgUnit" v-model:id="model.orgUnitId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabProcessChooseDialog ref="fabProcessChooseDialog" />
    <OrgUnitChooseDialog ref="orgUnitChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
