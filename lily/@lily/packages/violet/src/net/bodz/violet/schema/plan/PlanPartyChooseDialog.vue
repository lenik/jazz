<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { Organization_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import { PlanParty } from "./PlanParty";
import { Plan_TYPE } from "./PlanTypeInfo";

export const title = "Choose dialog for: Plan party";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "skel01-dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const typeMap = {
    "LONG": LONG,
    "Plan": Plan_TYPE,
    "Person": Person_TYPE,
    "Organization": Organization_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PlanParty.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="Plan" data-format="label" data-field="plan">Plan</th>
        <th data-type="Person" data-format="label" data-field="person">Person</th>
        <th data-type="Organization" data-format="label" data-field="org">Org</th>
        <th data-type="STRING" data-field="description">Description</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
