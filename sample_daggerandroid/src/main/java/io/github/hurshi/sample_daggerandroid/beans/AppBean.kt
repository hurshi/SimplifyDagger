package io.github.hurshi.sample_daggerandroid.beans

data class AppBean(val desc: String) {
    override fun toString(): String {
        return "AppBean ${hashCode()} $desc"
    }
}