package com.ifechukwu.moveapp.data

data class SearchItem(
    val name: String,
    val number: String,
    val location: Pair<String, String>
) {
    companion object {
        fun searchItems(): List<SearchItem> {
            return listOf(
                SearchItem(
                    name = "Summer linen jacket",
                    number = "#NEJ2006758585994",
                    location = Pair("Barcelona", "Paris")
                ),
                SearchItem(
                    name = "Tappered-fit jeans AW",
                    number = "#NEJ7566758585494",
                    location = Pair("Colombia", "Paris")
                ),
                SearchItem(
                    name = "Slim fit jeans",
                    number = "#NEJ4578238585494",
                    location = Pair("Bogota", "Dhaka")
                ),
                SearchItem(
                    name = "Macbook pro M2",
                    number = "#NEJ7566758565424",
                    location = Pair("Paris", "Morocco")
                ),
                SearchItem(
                    name = "Office setup desk",
                    number = "#NEJ7890758585494",
                    location = Pair("France", "Germany")
                )
            )
        }
    }
}