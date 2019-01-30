package io.github.hurshi.sample_daggerandroid.beans

data class SecActivityBean(val desc: String) {
    override fun toString(): String {
        return "SecActivityBean ${hashCode()} $desc"
    }
}