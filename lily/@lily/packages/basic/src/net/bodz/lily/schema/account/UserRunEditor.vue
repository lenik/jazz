<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { InetAddress, int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import UserRun from "./UserRun";
import { _UserRun_stuff_TYPE } from "./_UserRun_stuff_TypeInfo";

export const title = "Editor view of: User run";
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

import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import UserChooseDialog from "./UserChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<UserRun>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = UserRun.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
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
        <FieldGroup :type="_UserRun_stuff_TYPE">
            <FieldRow :property="meta.properties" v-model="model.properties">
                <JsonEditor v-model="model.properties" />
            </FieldRow>
            <FieldRow :property="meta.score" v-model="model.score">
                <input type="number" v-model="model.score" />
            </FieldRow>
            <FieldRow :property="meta.lastLoginTime" v-model="model.lastLoginTime">
                <DateTime v-model="model.lastLoginTime" />
            </FieldRow>
            <FieldRow :property="meta.lastLoginIP" v-model="model.lastLoginIP">
                <input type="text" v-model="model.lastLoginIP" />
            </FieldRow>
            <FieldRow :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
