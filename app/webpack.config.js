/**
 * Created by simena on 12.02.2015.
 */
module.exports = {
  context: __dirname + "/app",
  entry: "./entry.js",
  output: {
    path: __dirname,
    publicPath: "dist/",
    filename: "bundle.js"
  },
  module: {
    loaders: [
      { test: /\.css$/, loader: "style!css" }
    ]
  }
};