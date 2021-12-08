<template>
  <v-app>
    <v-list>
      <v-list-item-title><h2>👨‍👦‍👦지역게시판</h2></v-list-item-title>
      <v-divider></v-divider>

      <v-container>
        <board-write-form type="create" />
      </v-container>
    </v-list>
  </v-app>
</template>
<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";

export default {
  name: "BoardUpdate",
  components: {
    BoardWriteForm,
  },
};
</script>

<style></style>
