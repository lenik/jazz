<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import Group from "../account/Group";
import User from "../account/User";
import FormDef from "../meta/FormDef";
import VAppRequest from "./VAppRequest";

export const title = "Admin view of: V app request";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import VAppRequestEditor from "./VAppRequestEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = VAppRequest.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef.TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="STRING" data-field="code">Code</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="ZonedDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="ZonedDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="STRING" data-field="subject">Subject</th>
            <th data-type="User" data-format="label" data-field="op">Op</th>
            <th data-type="STRING" data-field="rawText">Raw Text</th>
            <th data-type="FormDef" data-format="label" data-field="form">Form</th>
            <th data-type="STRING" data-field="formArguments">Form Arguments</th>
            <th data-type="INT" data-field="dummy">Dummy</th>
        </template>
        <template #preview>
            <VAppRequestEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <VAppRequestEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
