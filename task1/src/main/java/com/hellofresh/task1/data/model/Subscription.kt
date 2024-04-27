package com.hellofresh.task1.data.model

/*
    * This is a data class that represents a subscription.
    * @param id The id of the subscription.
    * @param deliveryDay The day of the week the subscription is delivered.
    * @param isForFamily Whether the subscription is for a family or not.
 */
data class Subscription(val id: Int, val deliveryDay: String, val isForFamily: Boolean)
