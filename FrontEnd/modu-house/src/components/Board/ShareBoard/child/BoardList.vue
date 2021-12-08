<template>
  <v-container>
    <h2 class="mainboard_title" align="left">
      {{ title }}
      <v-btn class="moreBtn" :to="{ path: '/board/' + target_URL }" text>
        더보기</v-btn
      >
    </h2>
    <v-simple-table dense fixed-header height="160px">
      <template v-slot:default>
        <tbody>
          <board-list-row
            v-for="(board, index) in boards"
            :key="index"
            v-bind="board"
            type="main"
          ></board-list-row>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import BoardListRow from "./BoardListRow.vue";
import { listArticleShareBoard } from "@/api/board";

export default {
  data() {
    return {
      target_URL: "",
      title: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 5,
      headers: [
        {
          text: "shareboard",
          align: "start",
          sortable: true,
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
  components: {
    BoardListRow,
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "free") {
      this.title = "자유 게시판";
      this.target_URL = "free";
      this.getList();
    } else if (this.type === "local") {
      this.title = "지역 게시판";
      this.target_URL = "local";
      this.getList();
    } else if (this.type === "data") {
      this.title = "자료 게시판";
      this.target_URL = "data";
      this.getList();
    } else {
      this.title = "공유 게시판";
      this.target_URL = "share";
      this.getList();
    }

    this.getList();
  },

  methods: {
    moveCreate() {
      this.$router.push({ name: "ShareBoardCreate" });
    },
    getList() {
      let param = {
        pg: 1,
        spp: 5,
        key: null,
        word: null,
      };
      listArticleShareBoard(
        param,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
.moreBtn {
  float: right;
  margin-right: 10px;
}
.bodywrap {
  margin-top: 10px;
}
.mainboard_title {
  border-bottom: 1px solid #d4d4d4;
  padding-bottom: 15px;
  text-align: left;
}
.b_list {
  border-bottom: none;
}
</style>
