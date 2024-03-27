<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import Course from "./Course";
import TestQuestion from "./TestQuestion";

export const title = "Admin view of: Test question";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import TestQuestionEditor from "./TestQuestionEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = TestQuestion.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef.TYPE,
    "Course": Course.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="STRING" data-field="subject">Subject</th>
            <th data-type="User" data-format="label" data-field="op">Op</th>
            <th data-type="STRING" data-field="rawText">Raw Text</th>
            <th data-type="FormDef" data-format="label" data-field="form">Form</th>
            <th data-type="STRING" data-field="formArguments">Form Arguments</th>
            <th data-type="Course" data-format="label" data-field="course">Course</th>
            <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="INT" data-field="favCount">Fav Count</th>
            <th data-type="INT" data-field="voteCount">Vote Count</th>
            <th data-type="INT" data-field="hateCount">Hate Count</th>
            <th data-type="INT" data-field="pos">Pos</th>
            <th data-type="STRING" data-field="answer">Answer</th>
        </template>
        <template #preview>
            <TestQuestionEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <TestQuestionEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
