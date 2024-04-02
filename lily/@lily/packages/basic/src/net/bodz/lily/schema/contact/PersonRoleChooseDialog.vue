<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { OrgUnit_TYPE } from "./OrgUnitTypeInfo";
import { Organization_TYPE } from "./OrganizationTypeInfo";
import { PersonRole } from "./PersonRole";
import { Person_TYPE } from "./PersonTypeInfo";

export const title = "Choose dialog for: Person role";
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
    "Organization": Organization_TYPE,
    "OrgUnit": OrgUnit_TYPE,
    "Person": Person_TYPE,
    "STRING": STRING,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PersonRole.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="Organization" data-format="label" data-field="org">Org</th>
        <th data-type="OrgUnit" data-format="label" data-field="orgUnit">Org Unit</th>
        <th data-type="Person" data-format="label" data-field="person">Person</th>
        <th data-type="STRING" data-field="role">Role</th>
        <th data-type="STRING" data-field="description">Description</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
