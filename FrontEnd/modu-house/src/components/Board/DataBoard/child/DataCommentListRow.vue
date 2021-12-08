<template>
  <v-card class="mx-auto">
    <v-container id="input-usage" fluid>
      <v-row>
        <v-col cols="12">
          <v-input
            :messages="[`${comment.regtime}  |  ${comment.userid}`]"
            prepend-icon="mdi-comment"
          >
            {{ comment.content }}
          </v-input>
        </v-col>
      </v-row>
    </v-container>

    <v-card-actions>
      <v-btn
        text
        v-if="this.comment.userid === userInfo.userid"
        @click="deleteComment"
        >삭제
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
import { deleteCommentDataBoard } from "@/api/board";

const memberStore = "memberStore";

export default {
  name: "DataCommentListRow",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    comment: Object,
  },
  methods: {
    deleteComment() {
      deleteCommentDataBoard(
        this.comment.commentno,
        ({ data }) => {
          if (data == "success") {
            this.$router.go(this.$router.currentRoute);
          }
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
