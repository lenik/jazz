<script lang="ts">
import { onMounted, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import CoObject from "../../concrete/CoObject";
import PersonRole from "./PersonRole";
import _PersonRole_stuff from "./_PersonRole_stuff";

export const title = "Editor view of: Person role";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

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
        <FieldGroup :type="CoObject.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_PersonRole_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.role" v-model="model.role">
                <input type="text" v-model="model.role" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.org" v-model="model.org">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.org" v-model:id="model.orgId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.orgUnit" v-model="model.orgUnit">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.orgUnit" v-model:id="model.orgUnitId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.person" v-model="model.person">
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
