package io.github.hurshi.sample_daggerandroid.beans

data class MainActivityBean(val desc: String) {
    override fun toString(): String {
        return "MainActivityBean ${hashCode()} $desc"
    }
}