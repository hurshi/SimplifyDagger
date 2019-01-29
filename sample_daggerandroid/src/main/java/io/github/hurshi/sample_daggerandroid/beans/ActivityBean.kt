package io.github.hurshi.sample_daggerandroid.beans

data class ActivityBean(val desc: String) {
    override fun toString(): String {
        return "ActivityBean ${hashCode()} $desc"
    }
}