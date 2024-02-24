<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";

import PostParameterType from "./PostParameterType";

export const title = "Admin view of: Post parameter type";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PostParameterTypeEditor from "./PostParameterTypeEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PostParameterType.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="string" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="integer" data-field="accessMode">Access Mode</th>
            <th data-type="integer" data-field="acl">Acl</th>
            <th data-type="string" data-field="name">Name</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="integer" data-field="dummy">Dummy</th>
        </template>
        <template #preview>
            <PostParameterTypeEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PostParameterTypeEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
