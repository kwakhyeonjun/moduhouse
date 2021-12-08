<template>
  <v-container>
    <v-data-table
      @input="enterSelect()"
      :headers="headers"
      :items="boards"
      item-key="hit"
      class="elevation-1"
      :sort-by.sync="sortBy"
      :header-props="{ sortIcon: null }"
    >
      <!--search ends here-->
      <template #item.articleno="{ value }">
        <router-link :to="`/board/free/detail/${value}`">{{
          value
        }}</router-link>
      </template>
      <template #item.hit="{ value }">
        {{ value }}
      </template>
    </v-data-table>

    <v-col><v-col md="8" /></v-col>
  </v-container>
</template>

<script>
import { listArticleFreeBoard } from "@/api/board";

export default {
  name: "Reported",
  methods: {},

  data() {
    return {
      sortBy: "hit",
      sortDesc: true,
      checked: "",
      search: "",
      page: "",
      headers: [
        {
          text: "no",
          align: "start",
          sortable: true,
          value: "articleno",
        },
        { text: "아이디", value: "userid" },
        { text: "제목", value: "subject" },
        { text: "조회수", value: "hit" },
      ],

      boards: [],
    };
  },
  created() {
    let param = {
      //      pg: 1,
      spp: 4,
    };
    listArticleFreeBoard(
      param,
      (response) => {
        this.boards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
