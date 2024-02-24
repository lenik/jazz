<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer, long } from "@skeljs/core/src/lang/type";

import VApi from "./VApi";

export const title = "Admin view of: V api";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import VApiEditor from "./VApiEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = VApi.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="long" data-field="id">Id</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="any" data-field="properties">Properties</th>
            <th data-type="string" data-format="label" data-field="app">App</th>
            <th data-type="string" data-format="label" data-field="api">Api</th>
            <th data-type="string" data-field="callback">Callback</th>
        </template>
        <template #preview>
            <VApiEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <VApiEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
