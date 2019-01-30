package io.github.hurshi.sample_daggerandroid.beans

data class SecFragmentBean(val desc: String) {
    override fun toString(): String {
        return "SecFragmentBean ${hashCode()} $desc"
    }
}