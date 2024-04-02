<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import OrgUnit from "./OrgUnit";
import { _OrgUnit_stuff_TYPE } from "./_OrgUnit_stuff_TypeInfo";

export const title = "Editor view of: Org unit";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import OrgUnitChooseDialog from "./OrgUnitChooseDialog.vue";
import OrganizationChooseDialog from "./OrganizationChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<OrgUnit>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = OrgUnit.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
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
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_OrgUnit_stuff_TYPE">
            <FieldRow :property="meta.depth" v-model="model.depth">
                <input type="number" v-model="model.depth" />
            </FieldRow>
            <FieldRow :property="meta.org" v-model="model.org">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.org" v-model:id="model.orgId" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <OrgUnitChooseDialog ref="orgUnitChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
