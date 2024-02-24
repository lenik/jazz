<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";

import User from "./User";

export const title = "Admin view of: User";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import UserEditor from "./UserEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = User.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="type">User type like system-user, normal-user, etc.</th>
            <th data-type="string" data-field="name">The user name (unique)</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="any" data-field="properties">Properties</th>
            <th data-type="string" data-format="label" data-field="primaryGroup">The primary user group, the default value of ownerGroup.</th>
            <th data-type="string" data-format="label" data-field="referer">The referer user (used for promotion)</th>
            <th data-type="string" data-format="label" data-field="person">Person</th>
        </template>
        <template #preview>
            <UserEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <UserEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
