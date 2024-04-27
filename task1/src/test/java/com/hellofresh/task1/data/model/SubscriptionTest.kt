package com.hellofresh.task1.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class SubscriptionTest {

    @Test
    fun `test subscription model`() {
        val deliveryDay = "Monday"
        val subscription = Subscription(1, deliveryDay, true)
        assertEquals(1, subscription.id)
        assertEquals(deliveryDay, subscription.deliveryDay)
        assertEquals(true, subscription.isForFamily)
    }
}