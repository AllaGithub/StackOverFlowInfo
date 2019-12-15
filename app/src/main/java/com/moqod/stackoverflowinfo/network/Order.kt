package com.moqod.stackoverflowinfo.network

enum class Order {

    DESC {
        override fun toString(): String {
            return "desc"
        }
    },
    ASC {
        override fun toString(): String {
            return "asc"
        }
    }
}