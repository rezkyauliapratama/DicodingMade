package id.rezkyauliapratama.fhome.data

import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.fhome.data.source.local.MovieLocalDataSource

interface DataManager : MovieApiDataSource, MovieLocalDataSource