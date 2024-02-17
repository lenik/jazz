<script lang="ts">

import { onMounted } from "vue";

import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

export interface Props {
    modal?: boolean | string
}
</script>

<script setup lang="ts">
const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

%s(() => {
onMounted
});
</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="Person.TYPE" :modal="modal">
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="string" data-format="label" data-field="ownerUser">Owner User</th>
        <th data-type="string" data-format="label" data-field="ownerGroup">Owner Group</th>
        <th data-type="integer" data-field="accessMode">Access Mode</th>
        <th data-type="integer" data-field="acl">Acl</th>
        <th data-type="string" data-field="label">Label</th>
        <th data-type="string" data-field="description">Description</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="net.bodz.bas.repr.state.State" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="net.bodz.lily.schema.contact.PartyProperties" data-field="properties">Properties</th>
        <th data-type="string" data-format="label" data-field="category">Category</th>
        <th data-type="Date" data-field="birthday">Birthday</th>
        <th data-type="string" data-format="label" data-field="father">Father</th>
        <th data-type="string" data-format="label" data-field="mother">Mother</th>
        <th data-type="string" data-field="langTag">Lang Tag</th>
        <th data-type="java.time.ZoneId" data-field="timeZone">Time Zone</th>
        <th data-type="integer" data-field="roleCount">Role Count</th>
        <th data-type="boolean" data-field="customer">Customer</th>
        <th data-type="boolean" data-field="supplier">Supplier</th>
        <th data-type="boolean" data-field="employee">Employee</th>
        <th data-type="string" data-field="subject">Subject</th>
        <th data-type="integer" data-field="bankCount">Bank Count</th>
        <th data-type="string" data-field="gender">Gender</th>
        <th data-type="string" data-field="homeland">Homeland</th>
        <th data-type="string" data-field="passport">Passport</th>
        <th data-type="string" data-field="ssn">Ssn</th>
        <th data-type="string" data-field="dln">Dln</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
