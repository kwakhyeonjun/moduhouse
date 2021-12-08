<template>
  <v-app>
    <v-list>
      <v-list-item-title><h2>👨‍👦‍👦자유게시판</h2></v-list-item-title>
      <v-divider></v-divider>
      <v-row justify="space-around">
        <v-col cols="3" />
        <v-col cols="6">
          <v-container>
            <v-btn plain style="float: left" @click="listArticle">목록</v-btn>

            <v-btn plain style="float: right" @click="removeArticle"
              >글삭제</v-btn
            >
            <v-btn
              plain
              style="float: right; margin-right: 10px"
              @click="moveModifyArticle"
              >글수정</v-btn
            >
          </v-container>
        </v-col>
        <v-col cols="3" />
      </v-row>
      <v-row>
        <v-col cols="3" />
        <v-col cols="6">
          <v-container>
            <v-card class="mx-auto info_card" max-width="100%">
              <v-card-text align="center">
                <div>커뮤니티 > 자유 게시판</div>
                <br />
                <div class="gettitle">{{ article.subject }}</div>
                <br />
                <div>{{ article.userid }}</div>
                <div>
                  <span>{{ article.regtime }}</span>
                </div>
                <div>
                  <span
                    ><v-icon class="material-icons-outlined">
                      visibility
                    </v-icon>
                    {{ article.hit }}</span
                  >
                </div>
                <br />
                <hr />
                <br />
                <div v-html="message"></div>
              </v-card-text>
            </v-card>
          </v-container>
          <v-container>
            <comment-list :currentno="this.article.articleno" />
          </v-container>
        </v-col>
        <v-col cols="3" />
      </v-row>
    </v-list>
  </v-app>
</template>
<script>
import { getArticleFreeBoard, deleteArticleFreeBoard } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";
import CommentList from "./child/FreeCommentList.vue";

export default {
  components: {
    CommentList,
  },
  data() {
    return {
      article: {},
      type: "",
      markdown: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content) {
        console.log(this.article.content);
        return this.article.content.split("\n").join("<br>");
        // return marked(this.article.content);
      }
      return "";
    },
  },
  created() {
    getArticleFreeBoard(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "FreeBoard" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "FreeBoardUpdate",
        params: { no: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticleFreeBoard(this.article.articleno, () => {
          this.$router.push({ name: "FreeBoard" });
        });
      }
    },
  },
};
</script>

<style>
.info_card {
  border-bottom: 1px solid #d4d4d4;
  margin-bottom: 10px;
}
.gettitle {
  font-size: 30px;
}
hr {
  color: #d4d4d4;
}
</style>
