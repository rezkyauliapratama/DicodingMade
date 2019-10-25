package id.rezkyauliapratama.dicodingmade.favoriteapp.data

import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.local.MovieLocalDataSource

interface DataManager : MovieApiDataSource, MovieLocalDataSource