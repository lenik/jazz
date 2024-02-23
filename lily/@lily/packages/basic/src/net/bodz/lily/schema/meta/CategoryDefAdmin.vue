<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";

import CategoryDef from "./CategoryDef";

export const title = "Admin view of: Category def";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import CategoryDefEditor from "./CategoryDefEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = CategoryDef.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-field="code">Code</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="Moment" data-field="creationDate">Creation Date</th>
            <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="string" data-format="label" data-field="schema">Schema</th>
            <th data-type="string" data-format="label" data-field="parent">Parent</th>
            <th data-type="integer" data-field="depth">Depth</th>
            <th data-type="any" data-field="properties">Properties</th>
            <th data-type="integer" data-field="refCount">Ref Count</th>
        </template>
        <template #preview>
            <CategoryDefEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <CategoryDefEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
