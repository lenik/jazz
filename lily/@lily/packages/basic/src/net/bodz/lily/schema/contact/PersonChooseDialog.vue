<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { char, integer } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { ZoneId } from "../../../../../java/time/ZoneId";
import { Person } from "./Person";

export const title = "Choose dialog for: Person";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

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

onMounted(() => {
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
        <th data-type="string" data-field="icon">Icon</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="string" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="any" data-field="properties">Properties</th>
        <th data-type="string" data-format="label" data-field="category">Category</th>
        <th data-type="Date" data-field="birthday">Birthday</th>
        <th data-type="string" data-format="label" data-field="father">Father</th>
        <th data-type="string" data-format="label" data-field="mother">Mother</th>
        <th data-type="string" data-field="langTag">Lang Tag</th>
        <th data-type="ZoneId" data-field="timeZone">Time Zone</th>
        <th data-type="integer" data-field="roleCount">Role Count</th>
        <th data-type="boolean" data-field="customer">Customer</th>
        <th data-type="boolean" data-field="supplier">Supplier</th>
        <th data-type="boolean" data-field="employee">Employee</th>
        <th data-type="string" data-field="subject">Subject</th>
        <th data-type="integer" data-field="bankCount">Bank Count</th>
        <th data-type="char" data-field="gender">Gender</th>
        <th data-type="string" data-field="pronoun">Pronoun</th>
        <th data-type="string" data-field="sexualOrientation">Sexual Orientation</th>
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
