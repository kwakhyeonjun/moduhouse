import { apiInstance } from "./index.js";

const api = apiInstance();

/* 
    free Board
*/
function listArticleFreeBoard(param, success, fail) {
  api.get(`/board/free`, { params: param }).then(success).catch(fail);
}

function writeArticleFreeBoard(article, success, fail) {
  api.post(`/board/free`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticleFreeBoard(articleno, success, fail) {
  api.get(`/board/free/${articleno}`).then(success).catch(fail);
}

function modifyArticleFreeBoard(article, success, fail) {
  api.put(`/board/free`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticleFreeBoard(articleno, success, fail) {
  api.delete(`/board/free/${articleno}`).then(success).catch(fail);
}

function writeCommentFreeBoard(param, success, fail) {
  api
    .post(`/board/free/comment`, JSON.stringify(param))
    .then(success)
    .catch(fail);
}

function listCommentFreeBoard(articleno, success, fail) {
  api.get(`/board/free/comment/${articleno}`).then(success).catch(fail);
}

function deleteCommentFreeBoard(commentno, success, fail) {
  api.delete(`/board/free/comment/${commentno}`).then(success).catch(fail);
}

/* 
    data Board
*/
function listArticleDataBoard(param, success, fail) {
  api.get(`/board/data/`, { params: param }).then(success).catch(fail);
}

function writeArticleDataBoard(article, success, fail) {
  api.post(`/board/data`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticleDataBoard(articleno, success, fail) {
  api.get(`/board/data/${articleno}`).then(success).catch(fail);
}

function modifyArticleDataBoard(article, success, fail) {
  api.put(`/board/data`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticleDataBoard(articleno, success, fail) {
  api.delete(`/board/data/${articleno}`).then(success).catch(fail);
}

function writeCommentDataBoard(param, success, fail) {
  api
    .post(`/board/data/comment`, JSON.stringify(param))
    .then(success)
    .catch(fail);
}

function listCommentDataBoard(articleno, success, fail) {
  api.get(`/board/data/comment/${articleno}`).then(success).catch(fail);
}

function deleteCommentDataBoard(commentno, success, fail) {
  api.delete(`/board/data/comment/${commentno}`).then(success).catch(fail);
}

/* 
    Share Board
*/
function listArticleShareBoard(param, success, fail) {
  api.get(`/board/share`, { params: param }).then(success).catch(fail);
}

function writeArticleShareBoard(article, success, fail) {
  api.post(`/board/share`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticleShareBoard(articleno, success, fail) {
  api.get(`/board/share/${articleno}`).then(success).catch(fail);
}

function modifyArticleShareBoard(article, success, fail) {
  api.put(`/board/share`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticleShareBoard(articleno, success, fail) {
  api.delete(`/board/share/${articleno}`).then(success).catch(fail);
}

function writeCommentShareBoard(param, success, fail) {
  api
    .post(`/board/share/comment`, JSON.stringify(param))
    .then(success)
    .catch(fail);
}

function listCommentShareBoard(articleno, success, fail) {
  api.get(`/board/share/comment/${articleno}`).then(success).catch(fail);
}

function deleteCommentShareBoard(commentno, success, fail) {
  api.delete(`/board/share/comment/${commentno}`).then(success).catch(fail);
}

/* 
    AreaBoard
*/
function listArticleLocalBoard(param, success, fail) {
  api.get(`/board/area`, { params: param }).then(success).catch(fail);
}

function writeArticleLocalBoard(article, success, fail) {
  api.post(`/board/area`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticleLocalBoard(articleno, success, fail) {
  api.get(`/board/area/${articleno}`).then(success).catch(fail);
}

function modifyArticleLocalBoard(article, success, fail) {
  api.put(`/board/area`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticleLocalBoard(articleno, success, fail) {
  api.delete(`/board/area/${articleno}`).then(success).catch(fail);
}

function writeCommentLocalBoard(param, success, fail) {
  api
    .post(`/board/area/comment`, JSON.stringify(param))
    .then(success)
    .catch(fail);
}

function listCommentLocalBoard(articleno, success, fail) {
  api.get(`/board/area/comment/${articleno}`).then(success).catch(fail);
}

function deleteCommentLocalBoard(commentno, success, fail) {
  api.delete(`/board/area/comment/${commentno}`).then(success).catch(fail);
}

export {
  listArticleFreeBoard,
  writeArticleFreeBoard,
  getArticleFreeBoard,
  modifyArticleFreeBoard,
  deleteArticleFreeBoard,
  writeCommentFreeBoard,
  listCommentFreeBoard,
  deleteCommentFreeBoard,
  listArticleDataBoard,
  writeArticleDataBoard,
  getArticleDataBoard,
  modifyArticleDataBoard,
  deleteArticleDataBoard,
  writeCommentDataBoard,
  listCommentDataBoard,
  deleteCommentDataBoard,
  listArticleShareBoard,
  writeArticleShareBoard,
  getArticleShareBoard,
  modifyArticleShareBoard,
  deleteArticleShareBoard,
  writeCommentShareBoard,
  listCommentShareBoard,
  deleteCommentShareBoard,
  listArticleLocalBoard,
  writeArticleLocalBoard,
  getArticleLocalBoard,
  modifyArticleLocalBoard,
  deleteArticleLocalBoard,
  writeCommentLocalBoard,
  listCommentLocalBoard,
  deleteCommentLocalBoard,
};
