package com.whoolister.restassuredstarter

import com.whoolister.restassuredstarter.models.booking.BookingIdResponse
import com.whoolister.restassuredstarter.models.booking.BookingResponse
import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldNotBeBlank
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GetBookingTests {
    private val token = "abc123"

    @Nested
    inner class GetBookingIdTests {
        @Test
        fun getBookingIds() {
            val bookingIds: Array<BookingIdResponse>? = Given {
                port(3001)
            } When {
                get("/booking")
            } Then {
                statusCode(200)
            } Extract {
                `as`(Array<BookingIdResponse>::class.java)
            }

            withClue("Booking IDs should be present") {
                bookingIds shouldNotBeNull {
                    this shouldHaveAtLeastSize 1
                }
            }
        }
    }

    @Nested
    inner class GetBookingTests {
        @Test
        fun getBooking() {
            val booking: BookingResponse? = Given {
                port(3001)
            } When {
                get("/booking/1")
            } Then {
                statusCode(200)
            } Extract {
                `as`(BookingResponse::class.java)
            }

            withClue("Booking by ID '1' should be present") {
                booking shouldNotBeNull {
                    firstname.shouldNotBeBlank()
                    lastname.shouldNotBeBlank()
                    totalprice shouldBeGreaterThan 0
                    if (bookingdates?.checkin != null && bookingdates.checkout != null) {
                        bookingdates.checkin shouldBeBefore bookingdates.checkout
                    }
                    additionalneeds?.shouldNotBeBlank()
                }
            }
        }
    }
}