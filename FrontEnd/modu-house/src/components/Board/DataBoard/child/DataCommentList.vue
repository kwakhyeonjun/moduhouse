<template>
  <div>
    <h3>댓글 수 {{ commentCnt }}</h3>
    <v-form>
      <v-container>
        <v-text-field
          style="width: 100%"
          label="댓글 입력"
          v-model="content"
          return-object
          append-icon="mdi-send"
          @click:append="createComment()"
        ></v-text-field>
      </v-container>
    </v-form>
    <!-- comment들 출력 -->
    <data-comment-list
      class="mt-1"
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
    ></data-comment-list>
  </div>
</template>

<script>
import DataCommentList from "./DataCommentListRow.vue";
import { listCommentDataBoard, writeCommentDataBoard } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  components: { DataCommentList },
  data() {
    return {
      content: "",
      articleno: 0,
      commentCnt: 0,
      comments: {},
    };
  },
  props: {
    currentno: Number,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    currentno() {
      this.getList();
    },
  },
  methods: {
    createComment() {
      writeCommentDataBoard(
        {
          articleno: this.currentno,
          userid: this.userInfo.userid,
          content: this.content,
          regtime: new Date().toString(),
        },
        ({ data }) => {
          if (data == "success") {
            this.getList();
            this.content = "";
          }
        },
        (err) => {
          console.log(err);
        }
      );
      this.comments.push([
        { userid: this.userInfo.userid },
        { content: this.content },
        { regtime: new Date().toString() },
      ]);
    },
    getList() {
      listCommentDataBoard(
        this.currentno,
        (response) => {
          this.comments = response.data;
          this.commentCnt = this.comments.length;
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>

<style></style>
