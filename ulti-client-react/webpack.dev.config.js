const webpack = require('webpack');
const externalLibraries = require("./webpack.externalLibraries.config");
const SpeedMeasurePlugin = require("speed-measure-webpack-plugin");

const smp = new SpeedMeasurePlugin();

module.exports = smp.wrap({
    mode: "development",
    // A bemeneti JS fájl
    entry: {
        application: [
            "/src/index.tsx",
        ]
    },
    // A kimenet elérési útja
    output: {
        path: __dirname + '/public/',
        publicPath: '/public/',
        filename: "[name].js",
    },

    module: {
        // A loaderek adott típusú fájlokat alakítanak át más típusú fájlokká
        // Például SCSS-ből CSS-t, TypeScriptből JavaScriptet
        // A loaderek sorrendje fordított, mindig a legutolsó loader fut le először
        rules: [
            // CSS-fájlok (.css)
            { test: /\.css$/, use: ["style-loader", "css-loader"] },
            // SASS-fájlok (.scss)
            { test: /\.scss$/, use: ["style-loader", "css-loader", "sass-loader"] },
            // JS-fájlok (.js)
            // { test: /\.js$/, loader: "source-map-loader" },
            // TypeScript-fájlok (.ts, .tsx)
            {
                test: /\.tsx?$/,
                use: [
                    "ts-loader"
                ]
            },
            // WebFontok (.woff, .woff2)
            {
                test: /\.(woff|woff2)$/,
                use: {
                    loader: 'url-loader',
                    options: {
                        name: 'fonts/[hash].[ext]',
                        limit: 5000,
                        mimetype: 'application/font-woff'
                    }
                }
            },
            // További webfontok (.ttf, .eot, .svg)
            {
                test: /\.(ttf|eot|svg|jpg|png)$/,
                use: {
                    loader: 'file-loader',
                    options: {
                        name: 'fonts/[hash].[ext]'
                    }
                }
            }
        ]
    },

    resolve: {
        extensions: [".tsx", ".ts", ".jsx", ".json", ".js"],
        alias: externalLibraries.aliases
    },

    plugins: [
        // Ignore all locale files of moment.js
        new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),
        new webpack.ProvidePlugin({
            "$": "jquery",
            "jQuery": "jquery",
            "window.jQuery": "jquery",
            "window.$": "jquery",
        }),
        new webpack.DefinePlugin({
            "process.env": "{}",
            global: {}
        })
        // new ExtractTextPlugin('[name].css')
    ],
});