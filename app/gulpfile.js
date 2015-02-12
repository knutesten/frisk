/**
 * Created by simena on 12.02.2015.
 */
  
  
var gulp = require("gulp");
var gutil = require("gulp-util");
var webpack = require("webpack");
var WebpackDevServer = require("webpack-dev-server");
var webpackConfig = require("./webpack.config.js");

var gulp = require('gulp');
var webpack = require('gulp-webpack');
gulp.task("webpack", function() {
  return gulp.src('./entry.js')
    .pipe(webpack(webpackConfig))
    .pipe(gulp.dest('dist/'));
});

gulp.task("webpack-dev-server", function(callback) {
  // Start a webpack-dev-server
  var compiler = webpack(webpackConfig);

  new WebpackDevServer(compiler, {
    publicPath: "/" + compiler.publicPath,
    stats: {
      colors: true
    }
  }).listen(8085, "localhost", function(err) {
      if(err) throw new gutil.PluginError("webpack-dev-server", err);
      // Server listening
      gutil.log("[webpack-dev-server]", "http://localhost:8085/webpack-dev-server/index.html");

      // keep the server alive or continue?
      callback();
    });
});