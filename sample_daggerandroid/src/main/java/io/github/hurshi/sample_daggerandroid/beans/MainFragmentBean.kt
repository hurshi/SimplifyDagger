package io.github.hurshi.sample_daggerandroid.beans

data class MainFragmentBean(val desc: String) {
    override fun toString(): String {
        return "MainFragmentBean ${hashCode()} $desc"
    }
}