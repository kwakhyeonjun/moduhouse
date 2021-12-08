<template>
  <v-container>
    <h2 class="mainboard_title" align="left">
      {{ board }}
      <v-btn class="moreBtn" :to="{ path: '/board/' + target_URL }" text>
        더보기</v-btn
      >
    </h2>
    <v-data-table
      :headers="headers"
      :items="boards"
      item-key="articleno"
      dense
      no-data-text="작성된 게시글이 없습니다."
      hide-default-footer
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
  name: "RecentBoardListUtil",

  methods: {},
  props: { type: String },
  data() {
    return {
      target_URL: "",
      spp: 5,
      checked: "",
      itemsPerPage: 5,
      board: "",
      headers: [
        {
          text: "번호",
          value: "articleno",
          width: "1px",
          sortable: false,
          align: "center",
        },
        {
          text: "제목",
          value: "subject",
          width: "60%",
          sortable: false,
          align: "center",
        },
        {
          text: "조회수",
          value: "hit",
          width: "10px",
          sortable: false,
          align: "center",
        },
      ],

      boards: [],
    };
  },
  created() {
    console.log(this.type);
    let param = {
      key: null,
      word: null,
      spp: 4,
    };
    if (this.type == "local") {
      //이미 parent에서 게시판 틀이 있기 때문에 게시판 이름은 필요 없음.
      this.target_URL = "local";
      this.board = "지역 게시판";
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
      this.board = "자유 게시판";
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
      this.board = "공유 게시판";
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
      this.board = "자료 게시판";
      listArticleDataBoard(
        param,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
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
