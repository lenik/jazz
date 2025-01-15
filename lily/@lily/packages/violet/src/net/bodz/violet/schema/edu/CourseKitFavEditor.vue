<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import CourseKitFav from "./CourseKitFav";
import { _CourseKitFav_stuff_TYPE } from "./_CourseKitFav_stuff_TypeInfo";

export const title = "Editor view of: Course kit fav";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import FavRecordFieldGroup from "lily-basic/src/net/bodz/lily/concrete/FavRecordFieldGroup.vue";

import CourseKitChooseDialog from "./CourseKitChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<CourseKitFav>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = CourseKitFav.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const courseKitChooseDialog = ref<InstanceType<typeof CourseKitChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FavRecordFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_CourseKitFav_stuff_TYPE">
            <FieldRow :property="meta.coursekit" v-model="model.coursekit">
                <RefEditor :dialog="courseKitChooseDialog" v-model="model.coursekit" v-model:id="model.coursekitId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <CourseKitChooseDialog ref="courseKitChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
