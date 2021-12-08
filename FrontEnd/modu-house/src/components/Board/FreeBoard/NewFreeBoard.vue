<template>
  <v-app>
    <h2>
      자유게시판
      <v-btn class="mx-2" plain @click="moveCreate()">
        <v-icon dark> mdi-plus </v-icon>
      </v-btn>
    </h2>
    <v-divider></v-divider>
    <!-- local board list 를 불러온다 -->
    <board-list-util type="free"></board-list-util>
  </v-app>
</template>

<script>
import BoardListUtil from "@/components/Board/BoardList/BoardListUtil.vue";

export default {
  name: "NewFreeBoard",
  props: ["type"],
  components: {
    BoardListUtil,
  },
  methods: {
    moveCreate() {
      this.$router.push({ name: "FreeBoardCreate" });
    },
  },
};
</script>

<style></style>
