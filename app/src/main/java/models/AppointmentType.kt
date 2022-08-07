package models

enum class AppointmentType(val type:Int) {
    FIX(1),
    MEASUREMENT(2),
    OFFER(3),
    OTHER(4)
}