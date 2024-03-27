<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import IdEntity from "../../concrete/IdEntity";
import UserOtherId from "./UserOtherId";
import _UserOtherId_stuff from "./_UserOtherId_stuff";

export const title = "Editor view of: User other id";
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

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import UserChooseDialog from "./UserChooseDialog.vue";
import UserOtherIdTypeChooseDialog from "./UserOtherIdTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<UserOtherId>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = UserOtherId.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const userOtherIdTypeChooseDialog = ref<InstanceType<typeof UserOtherIdTypeChooseDialog>>();
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
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_UserOtherId_stuff.TYPE">
            <FieldRow :property="meta.beginTime" v-model="model.beginTime">
                <DateTime v-model="model.beginTime" />
            </FieldRow>
            <FieldRow :property="meta.endTime" v-model="model.endTime">
                <DateTime v-model="model.endTime" />
            </FieldRow>
            <FieldRow :property="meta.year" v-model="model.year">
                <input type="number" v-model="model.year" />
            </FieldRow>
            <FieldRow :property="meta.otherId" v-model="model.otherId">
                <input type="text" v-model="model.otherId" />
            </FieldRow>
            <FieldRow :property="meta.auth" v-model="model.auth">
                <JsonEditor v-model="model.auth" />
            </FieldRow>
            <FieldRow :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
            </FieldRow>
            <FieldRow :property="meta.type" v-model="model.type">
                <RefEditor :dialog="userOtherIdTypeChooseDialog" v-model="model.type" v-model:id="model.typeId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <UserOtherIdTypeChooseDialog ref="userOtherIdTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
