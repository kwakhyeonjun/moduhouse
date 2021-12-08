<template>
  <v-app>
    <v-list>
      <h2>
        자유 게시판
        <v-btn class="mx-2" plain @click="moveCreate()">
          <v-icon dark> mdi-plus </v-icon>
        </v-btn>
      </h2>
      <v-divider></v-divider>
      <v-row>
        <v-col cols="3"></v-col>
        <v-col cols="6">
          <v-simple-table fixed-header height="800px">
            <template v-slot:default>
              <thead>
                <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>조회수</th>
                </tr>
              </thead>
              <tbody>
                <board-list-row
                  v-for="(board, index) in boards"
                  :key="index"
                  v-bind="board"
                  type="detail"
                ></board-list-row>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
        <v-col cols="3"></v-col>
      </v-row>
    </v-list>
  </v-app>
</template>

<script>
import BoardListRow from "./child/BoardListRow.vue";
import { listArticleFreeBoard } from "@/api/board";

export default {
  components: {
    BoardListRow,
  },
  data() {
    return {
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        {
          text: "freeboard",
          align: "center",
          sortable: false,
          value: "name",
        },
        { text: "articleNo", value: "articleno" },
        { text: "Subject", value: "subject" },
        { text: "UserId", value: "userid" },
        { text: "Hit", value: "hit" },
      ],
      boards: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
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

  methods: {
    moveCreate() {
      this.$router.push({ name: "FreeBoardCreate" });
    },
  },
};
</script>

<style></style>
