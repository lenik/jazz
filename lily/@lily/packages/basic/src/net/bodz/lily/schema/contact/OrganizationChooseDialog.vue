<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { SQL_DATE } from "@skeljs/core/src/lang/time";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Group from "../account/Group";
import User from "../account/User";
import { Organization } from "./Organization";
import PartyCategory from "./PartyCategory";

export const title = "Choose dialog for: Organization";
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

const typeMap = {
    "INT": INT,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "PartyCategory": PartyCategory.TYPE,
    "SQL_DATE": SQL_DATE,
    "BOOLEAN": BOOLEAN,
};

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="Organization.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
        <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
        <th data-type="INT" data-field="accessMode">Access Mode</th>
        <th data-type="INT" data-field="acl">Acl</th>
        <th data-type="STRING" data-field="label">Label</th>
        <th data-type="STRING" data-field="description">Description</th>
        <th data-type="STRING" data-field="icon">Icon</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="PartyCategory" data-format="label" data-field="category">Category</th>
        <th data-type="SQL_DATE" data-field="birthday">Birthday</th>
        <th data-type="STRING" data-field="langTag">Lang Tag</th>
        <th data-type="STRING" data-field="timeZone">Time Zone</th>
        <th data-type="INT" data-field="roleCount">Role Count</th>
        <th data-type="BOOLEAN" data-field="supplier">Supplier</th>
        <th data-type="BOOLEAN" data-field="customer">Customer</th>
        <th data-type="STRING" data-field="subject">Subject</th>
        <th data-type="INT" data-field="bankCount">Bank Count</th>
        <th data-type="INT" data-field="size">Size</th>
        <th data-type="STRING" data-field="taxId">Tax Id</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
