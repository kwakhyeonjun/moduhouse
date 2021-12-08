<template>
  <v-app>
    <v-list>
      <v-list-item-title><h2>커뮤니티</h2></v-list-item-title>
      <v-divider></v-divider>
      <v-row>
        <v-col cols="2"></v-col>
        <!-- -
          <free-board-list type="free" />
        <v-col cols="4"><local-board-list type="local" /></v-col>
        <v-col cols="4"><data-board-list type="data" /></v-col>
        <v-col cols="4"><share-board-list type="share" /></v-col>
        -->
        <v-col cols="4">
          <community-board-list-util type="free"></community-board-list-util>
        </v-col>

        <v-col cols="4">
          <community-board-list-util type="local"> </community-board-list-util>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>

      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="4">
          <community-board-list-util type="data"></community-board-list-util>
        </v-col>
        <v-col cols="4">
          <community-board-list-util type="share"></community-board-list-util>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-list>
  </v-app>
</template>

<script>
/*
import FreeBoardList from "./FreeBoard/child/BoardList.vue";
import DataBoardList from "./DataBoard/child/BoardList.vue";
import LocalBoardList from "./LocalBoard/child/BoardList.vue";
import ShareBoardList from "./ShareBoard/child/BoardList.vue";*/
import CommunityBoardListUtil from "@/components/Board/BoardList/CommunityBoardListUtil.vue";

import {
  listArticleFreeBoard,
  listArticleDataBoard,
  listArticleLocalBoard,
  listArticleShareBoard,
} from "@/api/board";

export default {
  components: {
    /*
    FreeBoardList,
    DataBoardList,
    LocalBoardList,
    ShareBoardList,*/
    CommunityBoardListUtil,
  },
  data() {
    return {
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        {
          text: "freeboard",
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
    listArticleDataBoard(
      param,
      (response) => {
        this.boards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    listArticleLocalBoard(
      param,
      (response) => {
        this.boards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
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

  methods: {
    moveCreate() {
      // TODO
      this.$router.push({ name: "FreeBoardCreate" });
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
}
.b_list {
  border-bottom: none;
}
</style>
