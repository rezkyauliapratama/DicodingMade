package id.rezkyauliapratama.dicodingmade.data

import id.rezkyauliapratama.dicodingmade.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.dicodingmade.data.source.local.MovieLocalDataSource

interface DataManager : MovieApiDataSource, MovieLocalDataSource