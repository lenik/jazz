<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import TestApply from "./TestApply";
import _TestApply_stuff from "./_TestApply_stuff";

export const title = "Editor view of: Test apply";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import PersonChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";

import TestPaperChooseDialog from "./TestPaperChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TestApply>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TestApply.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const testPaperChooseDialog = ref<InstanceType<typeof TestPaperChooseDialog>>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
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
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_TestApply_stuff.TYPE">
            <FieldRow :property="meta.score" v-model="model.score">
                <input type="number" v-model="model.score" />
            </FieldRow>
            <FieldRow :property="meta.paper" v-model="model.paper">
                <RefEditor :dialog="testPaperChooseDialog" v-model="model.paper" v-model:id="model.paperId" />
            </FieldRow>
            <FieldRow :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.person" v-model:id="model.personId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <TestPaperChooseDialog ref="testPaperChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
