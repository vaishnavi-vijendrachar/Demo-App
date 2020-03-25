package com.vaishnavi.telstratest

import com.vaishnavi.telstratest.ui.main.MainActivity
import org.junit.Assert
import org.junit.Test

class MainActivityTest {
    @Test
    fun fragment_created() {
        val activity = MainActivity()
        Assert.assertNotNull(activity)
    }
}
