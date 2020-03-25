package com.vaishnavi.telstratest

import com.vaishnavi.telstratest.ui.main.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Test

class MainActivityTest {
    @Test
    fun activity_created() {
        val activity = MainActivity()
        assertNotNull(activity)
    }
}
