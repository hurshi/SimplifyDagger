package io.github.hurshi.sample_daggerandroid.beans

class MainFragmentBean(private var desc: String?) {
    override fun toString(): String {
        return "MainFragmentBean ${hashCode()} $desc"
    }
}
