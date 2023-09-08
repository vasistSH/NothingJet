package com.vasist.nothingjet

data class ApiResponse(val results: List<Result>)

data class Result(val name: Name, val email: String, val picture: Picture)

data class Name(val first: String, val last: String)

data class Picture(val large: String)

