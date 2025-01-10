<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import PersonRole from "./PersonRole";
import { _PersonRole_stuff_TYPE } from "./_PersonRole_stuff_TypeInfo";

export const title = "Editor view of: Person role";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import OrgUnitChooseDialog from "./OrgUnitChooseDialog.vue";
import OrganizationChooseDialog from "./OrganizationChooseDialog.vue";
import PersonChooseDialog from "./PersonChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<PersonRole>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = PersonRole.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
const orgUnitChooseDialog = ref<InstanceType<typeof OrgUnitChooseDialog>>();
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
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_PersonRole_stuff_TYPE">
            <FieldRow :property="meta.role" v-model="model.role">
                <input type="text" v-model="model.role" />
            </FieldRow>
            <FieldRow :property="meta.org" v-model="model.org">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.org" v-model:id="model.orgId" />
            </FieldRow>
            <FieldRow :property="meta.orgUnit" v-model="model.orgUnit">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.orgUnit" v-model:id="model.orgUnitId" />
            </FieldRow>
            <FieldRow :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.person" v-model:id="model.personId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <OrgUnitChooseDialog ref="orgUnitChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
