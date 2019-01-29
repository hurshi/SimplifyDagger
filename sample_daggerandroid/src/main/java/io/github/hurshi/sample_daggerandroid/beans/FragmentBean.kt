package io.github.hurshi.sample_daggerandroid.beans

data class FragmentBean(val desc: String) {
    override fun toString(): String {
        return "FragmentBean ${hashCode()} $desc"
    }
}