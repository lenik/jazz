<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";
import CoObject from "@lily/basic/src/net/bodz/lily/concrete/CoObject";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import StructRow from "@lily/basic/src/net/bodz/lily/concrete/StructRow";

import FabStdProcess from "./FabStdProcess";
import _FabStdProcess_stuff from "./_FabStdProcess_stuff";

export const title = "Editor view of: Fab std process";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import GroupChooseDialog from "@lily/basic/src/net/bodz/lily/schema/account/GroupChooseDialog.vue";
import UserChooseDialog from "@lily/basic/src/net/bodz/lily/schema/account/UserChooseDialog.vue";
import ArtifactModelChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelChooseDialog.vue";

import FabFnChooseDialog from "./FabFnChooseDialog.vue";
import FabStdTestChooseDialog from "./FabStdTestChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabStdProcess>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabStdProcess.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const artifactModelChooseDialog = ref<InstanceType<typeof ArtifactModelChooseDialog>>();
const fabFnChooseDialog = ref<InstanceType<typeof FabFnChooseDialog>>();
const fabStdTestChooseDialog = ref<InstanceType<typeof FabStdTestChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup :type="StructRow.TYPE">
            <FieldRow :property="meta.creationDate" v-model="model.creationDate">
                <DateTime v-model="model.creationDate" />
            </FieldRow>
            <FieldRow :property="meta.lastModified" v-model="model.lastModified">
                <DateTime v-model="model.lastModified" />
            </FieldRow>
            <FieldRow :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoObject.TYPE">
            <FieldRow :property="meta.accessMode" v-model="model.accessMode">
                <input type="number" v-model="model.accessMode" />
            </FieldRow>
            <FieldRow :property="meta.acl" v-model="model.acl">
                <input type="number" v-model="model.acl" />
            </FieldRow>
            <FieldRow :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow :property="meta.icon" v-model="model.icon">
                <input type="text" v-model="model.icon" />
            </FieldRow>
            <FieldRow :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
            <FieldRow :property="meta.ownerUser" v-model="model.ownerUser">
                <RefEditor :dialog="userChooseDialog" v-model="model.ownerUser" v-model:id="model.ownerUserId" />
            </FieldRow>
            <FieldRow :property="meta.ownerGroup" v-model="model.ownerGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroup" v-model:id="model.ownerGroupId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoImaged.TYPE">
            <FieldRow :property="meta.properties" v-model="model.properties">
                <JsonEditor v-model="model.properties" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabStdProcess_stuff.TYPE">
            <FieldRow :property="meta.valid" v-model="model.valid">
                <input type="checkbox" v-model="model.valid" />
            </FieldRow>
            <FieldRow :property="meta.validSince" v-model="model.validSince">
                <DateTime v-model="model.validSince" />
            </FieldRow>
            <FieldRow :property="meta.validUntil" v-model="model.validUntil">
                <DateTime v-model="model.validUntil" />
            </FieldRow>
            <FieldRow :property="meta.duration" v-model="model.duration">
                <input type="number" v-model="model.duration" />
            </FieldRow>
            <FieldRow :property="meta.strict" v-model="model.strict">
                <input type="checkbox" v-model="model.strict" />
            </FieldRow>
            <FieldRow :property="meta.output" v-model="model.output">
                <RefEditor :dialog="artifactModelChooseDialog" v-model="model.output" v-model:id="model.outputId" />
            </FieldRow>
            <FieldRow :property="meta.function" v-model="model.function">
                <RefEditor :dialog="fabFnChooseDialog" v-model="model.function" v-model:id="model.functionId" />
            </FieldRow>
            <FieldRow :property="meta.test" v-model="model.test">
                <RefEditor :dialog="fabStdTestChooseDialog" v-model="model.test" v-model:id="model.testId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <ArtifactModelChooseDialog ref="artifactModelChooseDialog" />
    <FabFnChooseDialog ref="fabFnChooseDialog" />
    <FabStdTestChooseDialog ref="fabStdTestChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
