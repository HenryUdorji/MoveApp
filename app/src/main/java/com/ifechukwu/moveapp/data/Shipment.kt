package com.ifechukwu.moveapp.data

data class Shipment(
    val status: Status,
    val title: String,
    val subtitle: String,
    val amount: String,
    val date: String
) {
    companion object {
        fun shipmentHistory(): List<Shipment> {
            return listOf(
                Shipment(
                    status = Status.InProgress,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$1400 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.InProgress,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$1200 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.InProgress,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Pending,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Pending,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Cancelled,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Cancelled,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$1400 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Completed,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$1200 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Completed,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Completed,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Completed,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                ),
                Shipment(
                    status = Status.Completed,
                    title = "Arriving today!",
                    subtitle = "Your delivery #NEJ2005631233332 from Atlanta, is arriving today!",
                    amount = "$650 USD",
                    date = "Sep 20, 2023"
                )
            )
        }
    }
}

enum class Status(val value: String) {
    All("All"),
    Completed("Completed"),
    InProgress("In Progress"),
    Pending("Pending Order"),
    Cancelled("Cancelled")
}