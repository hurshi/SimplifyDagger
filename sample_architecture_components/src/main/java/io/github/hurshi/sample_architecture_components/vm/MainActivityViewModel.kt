package io.github.hurshi.sample_architecture_components.vm

import androidx.lifecycle.ViewModel
import io.github.hurshi.sample_architecture_components.entity.DaoSessionSimulate
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
        private val daoSessionSimulate: DaoSessionSimulate) : ViewModel() {

    fun getDaoSessionSimulate(): DaoSessionSimulate {
        return daoSessionSimulate
    }

}