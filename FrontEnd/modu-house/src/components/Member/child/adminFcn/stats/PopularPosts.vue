<template>
  <v-container>
    <v-data-table
      @input="enterSelect()"
      :headers="headers"
      :items="boards"
      item-key="articleno"
      class="elevation-1"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
      :header-props="{ sortIcon: null }"
    >
      <!--search ends here-->

      <template v-slot:item.subject="{ item }">
        <div style="max-height: 16px; overflow: hidden">
          <a
            @click="$router.push(`/board/free/detail/${item.articleno}`)"
            style="text-decoration: none; color: inherit"
          >
            {{ item.subject }}
          </a>
        </div>
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
  name: "Best",
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
      spp: 10,
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
