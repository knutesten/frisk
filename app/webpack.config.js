/**
 * Created by simena on 12.02.2015.
 */
var webpack = require("webpack");
var path = require("path");
  
module.exports = {
  context: __dirname,
  entry: "./entry.js",
  output: {
    path: path.join(__dirname, "dist"),
    publicPath: "dist/",
    filename: "bundle.js"
  },
  module: {
    loaders: [
      { test: /\.css$/, loader: "style!css" }
    ]
  },
  plugins: [
    new webpack.ProvidePlugin({
      jQuery: "jquery",
      $: "jquery"
    })
  ]
};