<template>
  <v-row justify="space-around">
    <v-container height="100px"></v-container>

    <v-col cols="20">
      <v-form ref="form">
        <v-text-field
          single-line
          outlined
          v-model="article.subject"
          ref="subject"
          id="subject"
          label="제목"
          text-aligned="center"
          value=""
          height="50px"
          style="width: 100%"
        ></v-text-field>

        <!-- v model 대신 :initValue-"text'로-->
        <editor
          height="600px"
          max-width="600px"
          id="content"
          label="내용"
          outlined
          :options="options"
          :initialValue="text"
          ref="editor"
          v-model="content"
          initialEditType="wysiwyg"
        ></editor>
      </v-form>

      <v-btn
        plain
        @click="onSubmit"
        style="float: left; margin-right: 10px"
        :class="hover ? 'text--red' : 'text--primary'"
        >완료</v-btn
      >

      <v-btn plain @click="onReset" style="float: left">취소</v-btn>
      <v-list-item-title></v-list-item-title>
    </v-col>
  </v-row>
</template>

<script>
import {
  writeArticleDataBoard,
  getArticleDataBoard,
  modifyArticleDataBoard,
} from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "BoardWriteForm",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      options: {
        language: "ko",
      },
      text: "", //얘가 v-model 대신 쓰는애임!
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      getArticleDataBoard(
        this.$route.params.articleno,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      // this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        this.$refs.editor.invoke("getMarkdown") == "" &&
        ((msg = "내용 입력해주세요"), (err = false));
      if (!err) alert(msg);
      else this.type === "create" ? this.registArticle() : this.updateArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "DataBoard" });
    },
    registArticle() {
      writeArticleDataBoard(
        {
          userid: this.userInfo.userid,
          subject: this.article.subject,
          content: this.$refs.editor.invoke("getHTML"),
          boardtype: "data",
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateArticle() {
      modifyArticleDataBoard(
        {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.$refs.editor.invoke("getHTML"),
          boardtype: "data",
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "DataBoard" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "DataBoard" });
    },
  },
};
</script>

<style></style>
