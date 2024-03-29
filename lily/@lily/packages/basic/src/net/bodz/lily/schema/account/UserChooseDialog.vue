<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Object from "../../../../../java/lang/Object";
import Person from "../contact/Person";
import Group from "./Group";
import User from "./User";
import { User } from "./User";
import UserType from "./UserType";

export const title = "Choose dialog for: User";
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
    "Object": Object.TYPE,
    "UserType": UserType.TYPE,
    "STRING": STRING,
    "INT": INT,
    "int": int.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Group": Group.TYPE,
    "User": User.TYPE,
    "Person": Person.TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="User.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="Object" data-field="id">Id</th>
        <th data-type="UserType" data-format="label" data-field="type">User type like system-user, normal-user, etc.</th>
        <th data-type="STRING" data-field="name" title="The user name (unique)">Name</th>
        <th data-type="STRING" data-field="label">Label</th>
        <th data-type="STRING" data-field="description">Description</th>
        <th data-type="STRING" data-field="icon">Icon</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="int" data-field="state">State</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="Group" data-format="label" data-field="primaryGroup">The primary user group, the default value of ownerGroup.</th>
        <th data-type="User" data-format="label" data-field="referer">The referer user (used for promotion)</th>
        <th data-type="Person" data-format="label" data-field="person">Person</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
