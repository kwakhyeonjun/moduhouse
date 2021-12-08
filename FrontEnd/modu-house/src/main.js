import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/i18n/ko-kr";
import "highlight.js/styles/github.css";

import { Editor, Viewer } from "@toast-ui/vue-editor";

Vue.config.productionTip = false;
Vue.component("editor", Editor);
Vue.component("viewer", Viewer);
new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
