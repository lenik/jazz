<script lang="ts">

import { onMounted, ref } from "vue";

import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import type { UserOtherId } from "./UserOtherId";
import UserOtherIdEditor from "./UserOtherIdEditor.vue";

export interface Props {
}
</script>

<script setup lang="ts">

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = UserOtherId.TYPE;
const selection = ref<any>({});
onMounted(() => {
});

</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="net.bodz.bas.repr.state.State" data-field="state">State</th>
            <th data-type="Moment" data-field="creationDate">Creation Date</th>
            <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="Moment" data-field="beginTime">Begin Time</th>
            <th data-type="Moment" data-field="endTime">End Time</th>
            <th data-type="integer" data-field="year">Year</th>
            <th data-type="net.bodz.bas.site.json.JsonMap" data-field="properties">Properties</th>
            <th data-type="string" data-format="label" data-field="user">The declaring user</th>
            <th data-type="string" data-format="label" data-field="type">Type of Open ID</th>
            <th data-type="string" data-field="otherId">The identity data</th>
            <th data-type="java.lang.Object" data-field="auth">The authentication data</th>
        </template>
        <template #preview>
            <UserOtherIdEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <UserOtherIdEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {}
}
</style>
