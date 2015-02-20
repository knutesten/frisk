/**
 * Created by simena on 12.02.2015.
 */
var webpack = require("webpack");
var path = require("path");
var BowerWebpackPlugin = require('bower-webpack-plugin');
  
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
      { test: /\.css$/, loader: "style!css" },
      { test: /\.jpg$/, loader: "file" },
      { test: /\.less$/, loader: "style!css!less"},
      {test: /\.(woff|svg|ttf|eot)([\?]?.*)$/, loader: "file-loader?name=[name].[ext]"}
    ]
  },
  plugins: [
    new BowerWebpackPlugin({
      excludes: /.*\.less/
    }),
    new webpack.ProvidePlugin({
      $:      "jquery",
      jQuery: "jquery"
    }),
    new webpack.ResolverPlugin(
      new webpack.ResolverPlugin.DirectoryDescriptionFilePlugin("bower.json", ["main"])
    )
  ]
};