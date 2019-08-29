package id.rezkyauliapratama.fhome.data

import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSource

interface DataManager : MovieMockDataSource, MovieApiDataSource