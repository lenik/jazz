<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";

import VApiCredit from "./VApiCredit";

export const title = "Admin view of: V api credit";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import VApiCreditEditor from "./VApiCreditEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = VApiCredit.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="Moment" data-field="creationDate">Creation Date</th>
            <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="string" data-format="label" data-field="app">App</th>
            <th data-type="string" data-format="label" data-field="api">Api</th>
            <th data-type="BigInteger" data-field="credit">Credit</th>
        </template>
        <template #preview>
            <VApiCreditEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <VApiCreditEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
