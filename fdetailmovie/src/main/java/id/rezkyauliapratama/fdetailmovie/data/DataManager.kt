package id.rezkyauliapratama.fdetailmovie.data

import id.rezkyauliapratama.fdetailmovie.data.source.api.DetailContentApiDataSource
import id.rezkyauliapratama.fdetailmovie.data.source.local.DetailContentLocalDataSource

interface DataManager: DetailContentApiDataSource, DetailContentLocalDataSource