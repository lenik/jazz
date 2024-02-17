<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import OrgUnitChooseDialog from "./OrgUnitChooseDialog.vue";
import OrganizationChooseDialog from "./OrganizationChooseDialog.vue";
import PersonChooseDialog from "./PersonChooseDialog.vue";
import type { PersonRole } from "./PersonRole";

export interface Props {
}
</script>

<script setup lang="ts">
defineOptions({
    inheritAttrs: false
});

const model = defineModel<%s>();Person

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

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup decl="net.bodz.lily.concrete.StructRow">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoObject">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.contact._PersonRole_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.role" v-model="model.role">
                <input type="text" v-model="model.role" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.org" v-model="model.org">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.orgId" v-model:id="model.orgId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.orgUnit" v-model="model.orgUnit">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.orgUnitId" v-model:id="model.orgUnitId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.personId" v-model:id="model.personId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.contact.PersonRole">
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
