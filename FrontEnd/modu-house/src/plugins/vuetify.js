import Vue from "vue";
import Vuetify from "vuetify/lib/framework";
import "material-design-icons-iconfont/dist/material-design-icons.css"; // Ensure you are using css-loader

Vue.use(Vuetify);
//$ npm install @mdi/font -D
//npm install material-design-icons-iconfont -D
export default new Vuetify({
  icons: {
    iconfont: "md",
  },
});
