<template>
  <v-container style="max-width: 1200px; position: relative">
    <v-data-table
      :headers="headers"
      :items="boards"
      item-key="articleno"
      :search="search"
      :page.sync="page"
      :items-per-page="itemsPerPage"
      hide-default-footer
      @page-count="pageCount = $event"
    >
      <template v-slot:item.subject="{ item }">
        <div style="max-height: 16px; overflow: hidden">
          <a
            @click="
              $router.push(`/board/${target_URL}/detail/${item.articleno}`)
            "
            style="text-decoration: none; color: inherit"
          >
            {{ item.subject }}
          </a>
        </div>
      </template>
    </v-data-table>
    <div class="text-center pt-2">
      <v-pagination
        v-model="page"
        :length="pageCount"
        color="blue lighten-1"
      ></v-pagination>
    </div>
    <template>
      <v-row>
        <v-col md="9"></v-col>
        <v-text-field
          v-model="search"
          label="🔍게시글 번호, 아이디, 제목으로 검색"
        ></v-text-field>
      </v-row>
    </template>
  </v-container>
</template>

<script>
import {
  listArticleLocalBoard,
  listArticleFreeBoard,
  listArticleShareBoard,
  listArticleDataBoard,
} from "@/api/board";

export default {
  name: "BoardList",
  methods: {},
  props: { type: String },
  data() {
    return {
      target_URL: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 15,
      checked: "",
      search: "",
      headers: [
        { text: "번호", value: "articleno", width: "5%" },
        { text: "제목", value: "subject", width: "45%" },
        { text: "작성자", value: "userid", width: "7%" },
        { text: "조회수", value: "hit", width: "7%" },
        { text: "내용", value: "content", align: " d-none" },
      ],

      boards: [],
    };
  },
  created() {
    console.log(this.type);
    let param = {
      key: null,
      word: null,
    };
    if (this.type == "local") {
      //이미 parent에서 게시판 틀이 있기 때문에 게시판 이름은 필요 없음.
      this.target_URL = "local";
      listArticleLocalBoard(
        param,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else if (this.type == "free") {
      this.target_URL = "free";
      listArticleFreeBoard(
        param,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else if (this.type == "share") {
      this.target_URL = "share";
      listArticleShareBoard(
        param,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else if (this.type == "data") {
      this.target_URL = "data";
      listArticleDataBoard(
        param,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else if (this.type == "notice") {
      this.target_URL = "notice";
      //DB있다면 추가
    }
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
