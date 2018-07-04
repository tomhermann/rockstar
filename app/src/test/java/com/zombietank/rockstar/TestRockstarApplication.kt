package com.zombietank.rockstar

class TestRockstarApplication : RockstarApplication() {

    override fun isInAnalyzerProcess(): Boolean {
        return false
    }

    override fun installLeakCanary() {}
}
