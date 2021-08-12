var gulp = require("gulp");
var ts = require("gulp-typescript");
var tsProject = ts.createProject("tsconfig.json");
const webpack = require('webpack');
const WebpackDevServer = require("webpack-dev-server");

/*gulp.task("default", function () {
  return tsProject.src().pipe(ts(tsProject)).js.pipe(gulp.dest("dist"));
});*/

gulp.task("default", function(callback) {
	const config = require('./webpack.dev.config.js');
	config.entry["application"].splice(0, 0,
		`webpack-dev-server/client?http://localhost:8080`,
		"webpack/hot/dev-server"
	);
	config.devtool = "inline-source-map";
	config.plugins.splice(0, 0, new webpack.HotModuleReplacementPlugin());
	var compiler = webpack(config);
	new WebpackDevServer(compiler, {
		stats: {
			colors: true
		},
		index: '',
		inline: true,
		publicPath: config.output.publicPath,
		hot: true,
		hotOnly: true,
	// If we want it to be accessible not just from localhost, use 0.0.0.0 as the second parameter
	}).listen(8080, "localhost", function(err) {
	//}).listen(4232, "0.0.0.0", function(err) {
		if(err) throw new PluginError("webpack-dev-server", err);
	});
});